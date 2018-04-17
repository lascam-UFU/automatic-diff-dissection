package fr.inria.spirals.features.extractor;

import fr.inria.spirals.entities.RepairActions;
import fr.inria.spirals.features.analyzer.DiffAnalyzer;
import fr.inria.spirals.features.spoon.CtElementAnalyzer;
import fr.inria.spirals.features.spoon.SpoonHelper;
import gumtree.spoon.diff.Diff;
import gumtree.spoon.diff.operations.*;
import spoon.Launcher;
import spoon.reflect.declaration.CtElement;

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
        for (int i = 0; i < editScript.getAllOperations().size(); i++) {
            Operation operation = editScript.getAllOperations().get(i);
            if (operation instanceof MoveOperation) {
                operation.getSrcNode().putMetadata("isMoved", true);
                operation.getDstNode().putMetadata("isMoved", true);
            }
        }

        for (int i = 0; i < editScript.getRootOperations().size(); i++) {
            Operation operation = editScript.getRootOperations().get(i);
            CtElement srcNode = operation.getSrcNode();
            if (operation instanceof InsertOperation || operation instanceof DeleteOperation) {
                this.getRepairActions(srcNode, repairActions);
                SpoonHelper.printInsertOrDeleteOperation(srcNode.getFactory().getEnvironment(), srcNode, operation);
            } else {
                if (operation instanceof UpdateOperation) {
                    CtElement dstNode = operation.getDstNode();
                    this.getRepairActions(srcNode, repairActions);
                    SpoonHelper.printUpdateOperation(srcNode, dstNode, (UpdateOperation) operation);
                }
            }
        }

        return repairActions;
    }

    private void getRepairActions(CtElement e, final RepairActions repairActions) {
        new CtElementAnalyzer(e).analyze(repairActions);
    }

}
