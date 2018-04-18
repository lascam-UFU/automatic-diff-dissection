package fr.inria.spirals.features.extractor;

import fr.inria.spirals.entities.RepairActions;
import fr.inria.spirals.entities.RepairPatterns;
import fr.inria.spirals.features.analyzer.DiffAnalyzer;
import fr.inria.spirals.features.pattern.PatternMatcher;
import fr.inria.spirals.features.spoon.CtElementAnalyzer;
import fr.inria.spirals.features.spoon.SpoonHelper;
import gumtree.spoon.AstComparator;
import gumtree.spoon.diff.Diff;
import gumtree.spoon.diff.operations.*;
import spoon.Launcher;
import spoon.reflect.code.CtStatement;
import spoon.reflect.code.CtStatementList;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.path.CtRole;

import java.util.List;
import java.util.Map;

/**
 * Created by tdurieux
 */
public class AstExtractor extends AbstractExtractor {

    public AstExtractor(String oldSourcePath, String diffPath) {
        super(oldSourcePath, diffPath);
    }

    public RepairActions extract() {
        new AstComparator();
        System.setProperty("gumtree.match.gt.minh", "2");
        System.setProperty("gumtree.match.bu.sim", "0.30");

        DiffAnalyzer diffAnalyzer = new DiffAnalyzer(diffPath);

        Map<String, List<String>> originalFiles = diffAnalyzer.getOriginalFiles(buggySourcePath);
        Map<String, List<String>> patchedFiles = diffAnalyzer.getPatchedFiles(buggySourcePath);

        Launcher oldSpoon = SpoonHelper.initSpoon(originalFiles);
        Launcher newSpoon = SpoonHelper.initSpoon(patchedFiles);

        Diff editScript = SpoonHelper.getAstDiff(oldSpoon, newSpoon);

        RepairActions repairActions = analyzeEditScript(editScript);

        return repairActions;
    }

    private RepairActions analyzeEditScript(Diff editScript) {
        final RepairActions repairActions = new RepairActions();
        final RepairPatterns patterns = new RepairPatterns();
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

        for (int i = 0; i < editScript.getRootOperations().size(); i++) {
            Operation operation = editScript.getRootOperations().get(i);
            CtElement srcNode = operation.getSrcNode();
            if (operation instanceof InsertOperation || operation instanceof DeleteOperation) {
                this.getRepairActions(srcNode, repairActions, operation instanceof DeleteOperation?
                        CtElementAnalyzer.ACTION_TYPE.DELETE:
                        CtElementAnalyzer.ACTION_TYPE.ADD);
                this.getPatterns(srcNode, patterns, operation instanceof DeleteOperation?
                        CtElementAnalyzer.ACTION_TYPE.DELETE:
                        CtElementAnalyzer.ACTION_TYPE.ADD);
                SpoonHelper.printInsertOrDeleteOperation(srcNode.getFactory().getEnvironment(), srcNode, operation);
            } else {
                if (operation instanceof UpdateOperation) {
                    CtElement dstNode = operation.getDstNode();
                    this.getRepairActions(srcNode, repairActions, CtElementAnalyzer.ACTION_TYPE.UPDATE);
                    this.getPatterns(srcNode, patterns, CtElementAnalyzer.ACTION_TYPE.UPDATE);
                    SpoonHelper.printUpdateOperation(srcNode, dstNode, (UpdateOperation) operation);
                }
            }
        }

        System.out.println(patterns);
        System.out.println(repairActions);
        return repairActions;
    }

    private void getRepairActions(CtElement e, final RepairActions repairActions, CtElementAnalyzer.ACTION_TYPE actionType) {
        new CtElementAnalyzer(e).analyze(repairActions, actionType);
    }

    private void getPatterns(CtElement e, final RepairPatterns patterns, CtElementAnalyzer.ACTION_TYPE actionType) {
        new PatternMatcher(e).analyze(patterns, actionType);
    }

}
