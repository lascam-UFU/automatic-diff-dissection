package add.features.detector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.gumtreediff.tree.ITree;

import add.features.FeatureAnalyzer;
import add.features.detector.repairpatterns.MappingAnalysis;
import add.features.detector.spoon.SpoonHelper;
import add.features.diffanalyzer.JGitBasedDiffAnalyzer;
import add.main.Config;
import gumtree.spoon.AstComparator;
import gumtree.spoon.diff.Diff;
import gumtree.spoon.diff.operations.DeleteOperation;
import gumtree.spoon.diff.operations.InsertOperation;
import gumtree.spoon.diff.operations.MoveOperation;
import gumtree.spoon.diff.operations.Operation;
import gumtree.spoon.diff.operations.UpdateOperation;
import spoon.Launcher;
import spoon.reflect.declaration.CtElement;

/**
 * Created by tdurieux
 */
public abstract class EditScriptBasedDetector extends FeatureAnalyzer {

    protected Diff editScript;

    public EditScriptBasedDetector(Config config, Diff editScript) {
        super(config);
        if (editScript == null) {
            this.editScript = extractEditScript();
        } else {
            this.editScript = editScript;
        }
    }

    public EditScriptBasedDetector(Config config) {
        this(config, null);
    }

    private Diff extractEditScript() {
        new AstComparator();
        System.setProperty("gumtree.match.gt.minh", "1");
        System.setProperty("gumtree.match.bu.sim", "0.5");

        JGitBasedDiffAnalyzer jgitDiffAnalyzer = new JGitBasedDiffAnalyzer(this.config.getDiffPath());

        Map<String, List<String>> originalFiles = jgitDiffAnalyzer
                .getOriginalFiles(this.config.getBuggySourceDirectoryPath());
        Map<String, List<String>> patchedFiles = jgitDiffAnalyzer
                .getPatchedFiles(this.config.getBuggySourceDirectoryPath());

        Launcher oldSpoon = SpoonHelper.initSpoon(originalFiles);
        Launcher newSpoon = SpoonHelper.initSpoon(patchedFiles);

        Diff editScript = SpoonHelper.getAstDiff(oldSpoon, newSpoon);
        this.preprocessEditScript(editScript);

        return editScript;
    }

    public static void preprocessEditScript(Diff editScript) {
        List<Operation> operations = new ArrayList<>();
        operations.addAll(editScript.getAllOperations());
        operations.addAll(editScript.getRootOperations());
        for (int i = 0; i < operations.size(); i++) {
            Operation operation = operations.get(i);

            CtElement srcNode = operation.getSrcNode();
            CtElement dstNode = operation.getDstNode();

            if (editScript.getRootOperations().contains(operation)) {
                srcNode.putMetadata("root", true);
                if (dstNode != null)
                    dstNode.putMetadata("root", true);
            }

            if (operation instanceof MoveOperation) {

                srcNode.putMetadata("isMoved", true);
                srcNode.putMetadata("movingSrc", true);
                dstNode.putMetadata("isMoved", true);
                dstNode.putMetadata("movingDst", true);

                setTreesLeftRight(editScript, operation, srcNode, dstNode);

            } else {
                if (srcNode != null) {
                    srcNode.putMetadata("new", true);
                }
                if (dstNode != null) {
                    dstNode.putMetadata("new", true);
                }
                if (operation instanceof DeleteOperation) {
                    if (operation.getSrcNode() != null) {
                        operation.getSrcNode().putMetadata("delete", true);
                    }
                    if (operation.getDstNode() != null) {
                        operation.getDstNode().putMetadata("delete", true);
                    }
                    srcNode.putMetadata("tree", operation.getAction().getNode());
                }
                if (operation instanceof UpdateOperation) {
                    if (operation.getSrcNode() != null) {
                        operation.getSrcNode().putMetadata("update", true);
                    }
                    if (operation.getDstNode() != null) {
                        operation.getDstNode().putMetadata("update", true);
                    }

                    setTreesLeftRight(editScript, operation, srcNode, dstNode);
                }

                if (srcNode != null && operation instanceof InsertOperation && operation.getAction() != null) {
                    srcNode.putMetadata("tree", operation.getAction().getNode());
                }
            }
        }
    }

    /**
     * The operation has the node in source. We find in the mapping the destination.
     * 
     * @param editScript
     * @param operation
     * @param srcNode
     * @param dstNode
     */
    public static void setTreesLeftRight(Diff editScript, Operation operation, CtElement srcNode, CtElement dstNode) {
        srcNode.putMetadata("tree", operation.getAction().getNode());
        ITree rightnode = MappingAnalysis.getRightFromLeftNodeMapped(editScript, operation.getAction().getNode());
        if (editScript != null) {
            dstNode.putMetadata("tree", rightnode);
        } else {
            System.err.println("Error:  node not mapped on operation " + operation.getClass().getName());
        }
    }

    public Diff getEditScript() {
        return editScript;
    }

}
