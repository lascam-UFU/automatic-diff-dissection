package fr.inria.spirals.features.detector.repairpatterns;

import fr.inria.spirals.entities.RepairPatterns;
import fr.inria.spirals.features.detector.EditScriptBasedDetector;
import fr.inria.spirals.features.detector.spoon.CtElementAnalyzer;
import fr.inria.spirals.features.detector.spoon.SpoonHelper;
import fr.inria.spirals.main.Config;
import gumtree.spoon.diff.operations.DeleteOperation;
import gumtree.spoon.diff.operations.InsertOperation;
import gumtree.spoon.diff.operations.Operation;
import gumtree.spoon.diff.operations.UpdateOperation;
import spoon.reflect.declaration.CtElement;

/**
 * Created by fermadeiral
 */
public class RepairPatternDetector extends EditScriptBasedDetector {

    private RepairPatterns repairPatterns;

    public RepairPatternDetector(Config config) {
        super(config);
        this.repairPatterns = new RepairPatterns();
    }

    @Override
    public RepairPatterns analyze() {
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
        //new PatternMatcher(e).analyze(this.repairPatterns, actionType);
        new MissingNullCheckPatternDetector(e, actionType).process(this.repairPatterns);
    }

    public RepairPatterns getRepairPatterns() {
        return this.repairPatterns;
    }

}
