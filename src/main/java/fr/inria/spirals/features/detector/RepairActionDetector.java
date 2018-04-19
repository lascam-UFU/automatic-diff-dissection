package fr.inria.spirals.features.detector;

import fr.inria.spirals.entities.RepairActions;
import fr.inria.spirals.features.spoon.CtElementAnalyzer;
import fr.inria.spirals.features.spoon.SpoonHelper;
import gumtree.spoon.diff.operations.DeleteOperation;
import gumtree.spoon.diff.operations.InsertOperation;
import gumtree.spoon.diff.operations.Operation;
import gumtree.spoon.diff.operations.UpdateOperation;
import spoon.reflect.declaration.CtElement;

/**
 * Created by fermadeiral
 */
public class RepairActionDetector extends AbstractEditScriptBasedDetector {

    private RepairActions repairActions;

    public RepairActionDetector() {
        super();
        this.repairActions = new RepairActions();
    }

    @Override
    RepairActions detect() {
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
                    this.detectRepairActions(srcNode, CtElementAnalyzer.ACTION_TYPE.UPDATE);
                    SpoonHelper.printUpdateOperation(srcNode, dstNode, (UpdateOperation) operation);
                }
            }
        }
        return this.repairActions;
    }

    private void detectRepairActions(CtElement e, CtElementAnalyzer.ACTION_TYPE actionType) {
        new CtElementAnalyzer(e).analyze(repairActions, actionType);
    }

    public RepairActions getRepairActions() {
        return this.repairActions;
    }

}
