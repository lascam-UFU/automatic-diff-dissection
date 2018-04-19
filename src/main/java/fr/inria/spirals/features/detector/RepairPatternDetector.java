package fr.inria.spirals.features.detector;

import fr.inria.spirals.entities.RepairPatterns;
import fr.inria.spirals.features.pattern.PatternMatcher;
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
public class RepairPatternDetector extends AbstractEditScriptBasedDetector {

    private RepairPatterns repairPatterns;

    public RepairPatternDetector() {
        super();
        this.repairPatterns = new RepairPatterns();
    }

    @Override
    RepairPatterns detect() {
        for (int i = 0; i < editScript.getRootOperations().size(); i++) {
            Operation operation = editScript.getRootOperations().get(i);
            CtElement srcNode = operation.getSrcNode();
            if (operation instanceof InsertOperation || operation instanceof DeleteOperation) {
                this.detectRepairPatterns(srcNode, operation instanceof DeleteOperation?
                        CtElementAnalyzer.ACTION_TYPE.DELETE:
                        CtElementAnalyzer.ACTION_TYPE.ADD);
                SpoonHelper.printInsertOrDeleteOperation(srcNode.getFactory().getEnvironment(), srcNode, operation);
            } else {
                if (operation instanceof UpdateOperation) {
                    CtElement dstNode = operation.getDstNode();
                    this.detectRepairPatterns(srcNode, CtElementAnalyzer.ACTION_TYPE.UPDATE);
                    SpoonHelper.printUpdateOperation(srcNode, dstNode, (UpdateOperation) operation);
                }
            }
        }
        return this.repairPatterns;
    }

    private void detectRepairPatterns(CtElement e, CtElementAnalyzer.ACTION_TYPE actionType) {
        new PatternMatcher(e).analyze(this.repairPatterns, actionType);
    }

    public RepairPatterns getRepairPatterns() {
        return this.repairPatterns;
    }

}
