package fr.inria.spirals.features.detector.repairpatterns;

import fr.inria.spirals.entities.RepairPatterns;
import gumtree.spoon.diff.operations.MoveOperation;
import gumtree.spoon.diff.operations.Operation;

import java.util.List;

/**
 * Created by tdurieux
 */
public class CodeMovingPatternDetector extends AbstractPatternDetector {

    public CodeMovingPatternDetector(List<Operation> operations) {
        super(operations);
    }

    @Override
    public void detect(RepairPatterns repairPatterns) {
        for (int i = 0; i < operations.size(); i++) {
            Operation operation = operations.get(i);
            if (!(operation instanceof MoveOperation)) {
                continue;
            }
            if (operation.getDstNode().getParent().getMetadata("new") == null &&
                    operation.getSrcNode().getParent().getMetadata("new") == null) {
                repairPatterns.incrementFeatureCounter("codeMove");
            }
        }
    }

}
