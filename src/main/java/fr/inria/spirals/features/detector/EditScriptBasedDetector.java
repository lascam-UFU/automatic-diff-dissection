package fr.inria.spirals.features.detector;

import fr.inria.spirals.features.FeatureAnalyzer;
import fr.inria.spirals.features.detector.spoon.SpoonHelper;
import fr.inria.spirals.features.diffanalyzer.JGitBasedDiffAnalyzer;
import fr.inria.spirals.main.Config;
import gumtree.spoon.AstComparator;
import gumtree.spoon.diff.Diff;
import gumtree.spoon.diff.operations.MoveOperation;
import gumtree.spoon.diff.operations.Operation;
import spoon.Launcher;
import spoon.reflect.code.CtStatement;
import spoon.reflect.code.CtStatementList;
import spoon.reflect.path.CtRole;

import java.util.List;
import java.util.Map;

/**
 * Created by tdurieux
 */
public abstract class EditScriptBasedDetector extends FeatureAnalyzer {

    protected Diff editScript;

    public EditScriptBasedDetector(Config config) {
        super(config);
        this.editScript = this.extractEditScript();
    }

    private Diff extractEditScript() {
        new AstComparator();
        System.setProperty("gumtree.match.gt.minh", "2");
        System.setProperty("gumtree.match.bu.sim", "0.30");

        JGitBasedDiffAnalyzer jgitDiffAnalyzer = new JGitBasedDiffAnalyzer(this.config.getDiffPath());

        Map<String, List<String>> originalFiles = jgitDiffAnalyzer.getOriginalFiles(this.config.getBuggySourceDirectoryPath());
        Map<String, List<String>> patchedFiles = jgitDiffAnalyzer.getPatchedFiles(this.config.getBuggySourceDirectoryPath());

        Launcher oldSpoon = SpoonHelper.initSpoon(originalFiles);
        Launcher newSpoon = SpoonHelper.initSpoon(patchedFiles);

        Diff editScript = SpoonHelper.getAstDiff(oldSpoon, newSpoon);
        this.preprocessEditScript(editScript);

        return editScript;
    }

    private void preprocessEditScript(Diff editScript) {
        for (int i = 0; i < editScript.getAllOperations().size(); i++) {
            Operation operation = editScript.getAllOperations().get(i);
            if (operation instanceof MoveOperation) {
                if (operation.getDstNode().getRoleInParent() == CtRole.STATEMENT) {
                    operation.getDstNode().getParent(CtStatementList.class).removeStatement((CtStatement) operation.getDstNode());
                }
                if (operation.getSrcNode().getRoleInParent() == CtRole.STATEMENT) {
                    operation.getSrcNode().getParent(CtStatementList.class).removeStatement((CtStatement) operation.getSrcNode());
                }
                operation.getSrcNode().putMetadata("isMoved", true);
                operation.getDstNode().putMetadata("isMoved", true);
            } else {
                if (operation.getSrcNode() != null) {
                    operation.getSrcNode().putMetadata("new", true);
                }
                if (operation.getDstNode() != null) {
                    operation.getDstNode().putMetadata("new", true);
                }
            }
        }
    }

}
