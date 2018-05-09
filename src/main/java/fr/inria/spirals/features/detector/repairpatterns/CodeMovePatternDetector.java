package fr.inria.spirals.features.detector.repairpatterns;

import fr.inria.spirals.entities.RepairPatterns;
import gumtree.spoon.diff.operations.MoveOperation;
import gumtree.spoon.diff.operations.Operation;
import gumtree.spoon.diff.operations.UpdateOperation;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.code.CtTypeAccess;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.declaration.CtElement;

import java.util.List;

/**
 * Created by fermadeiral
 */
public class CodeMovePatternDetector extends AbstractPatternDetector {


    public CodeMovePatternDetector(List<Operation> operations) {
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
