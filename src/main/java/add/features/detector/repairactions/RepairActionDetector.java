package add.features.detector.repairactions;

import add.entities.RepairActions;
import add.features.detector.EditScriptBasedDetector;
import add.features.detector.spoon.SpoonHelper;
import add.main.Config;
import gumtree.spoon.diff.Diff;
import gumtree.spoon.diff.operations.DeleteOperation;
import gumtree.spoon.diff.operations.InsertOperation;
import gumtree.spoon.diff.operations.Operation;
import gumtree.spoon.diff.operations.UpdateOperation;
import spoon.reflect.declaration.CtElement;

/**
 * Created by fermadeiral
 */
public class RepairActionDetector extends EditScriptBasedDetector {

    private RepairActions repairActions;

    public RepairActionDetector(Config config, Diff editScript) {
        super(config, editScript);
        this.repairActions = new RepairActions();
    }

    public RepairActionDetector(Config config) {
        super(config);
        this.repairActions = new RepairActions();
    }

    @Override
    public RepairActions analyze() {
        for (int i = 0; i < editScript.getRootOperations().size(); i++) {
            Operation operation = editScript.getRootOperations().get(i);
            CtElement srcNode = operation.getSrcNode();
            if (operation instanceof InsertOperation || operation instanceof DeleteOperation) {
                this.detectRepairActions(srcNode, operation instanceof DeleteOperation?
                        CtElementAnalyzer.ACTION_TYPE.DELETE:
                        CtElementAnalyzer.ACTION_TYPE.ADD);
                SpoonHelper.printInsertOrDeleteOperation(srcNode.getFactory().getEnvironment(), srcNode, operation);
            } else {
                if (operation instanceof UpdateOperation) {
                    CtElement dstNode = operation.getDstNode();
                    this.detectRepairActionsInUpdate(srcNode, dstNode);
                    SpoonHelper.printUpdateOperation(srcNode, dstNode, (UpdateOperation) operation);
                }
            }
        }
        return this.repairActions;
    }

    private void detectRepairActions(CtElement e, CtElementAnalyzer.ACTION_TYPE actionType) {
        new CtElementAnalyzer(e).analyze(repairActions, actionType);
    }
    private void detectRepairActionsInUpdate(CtElement e, CtElement dst) {
        new CtElementAnalyzer(e,dst).analyze(repairActions, CtElementAnalyzer.ACTION_TYPE.UPDATE);
    }

    public RepairActions getRepairActions() {
        return this.repairActions;
    }

}
