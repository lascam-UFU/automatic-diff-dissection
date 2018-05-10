package fr.inria.spirals.features.detector.repairpatterns;

import fr.inria.spirals.entities.RepairPatterns;
import gumtree.spoon.diff.operations.Operation;
import gumtree.spoon.diff.operations.UpdateOperation;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.code.CtTypeAccess;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.declaration.CtElement;

import java.util.List;

/**
 * Created by tdurieux
 */
public class ConstantChangePatternDetector extends AbstractPatternDetector {

    public ConstantChangePatternDetector(List<Operation> operations) {
        super(operations);
    }

    @Override
    public void detect(RepairPatterns repairPatterns) {
        for (int i = 0; i < operations.size(); i++) {
            Operation operation = operations.get(i);
            if (!(operation instanceof UpdateOperation)) {
                continue;
            }
            CtElement srcNode = operation.getSrcNode();
            if (operation.getSrcNode().getParent().getMetadata("new") != null ||
                    operation.getSrcNode().getParent().getMetadata("isMoved") != null) {
                continue;
            }
            if (srcNode instanceof CtLiteral) {
                repairPatterns.incrementFeatureCounter("constChange");
            }
            if (srcNode instanceof CtVariableAccess) {
                String simpleName = ((CtVariableAccess) srcNode).getVariable().getSimpleName();
                if (simpleName.toUpperCase().equals(simpleName)) {
                    repairPatterns.incrementFeatureCounter("constChange");
                }
            }
            if (srcNode instanceof CtTypeAccess) {
                String simpleName = ((CtTypeAccess) srcNode).getAccessedType().getSimpleName();
                if (simpleName.toUpperCase().equals(simpleName)) {
                    repairPatterns.incrementFeatureCounter("constChange");
                }
            }
        }
    }

}
