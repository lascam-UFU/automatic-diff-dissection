package add.features.detector.repairpatterns;

import add.entities.RepairPatterns;
import add.features.detector.spoon.RepairPatternUtils;
import gumtree.spoon.diff.operations.DeleteOperation;
import gumtree.spoon.diff.operations.InsertOperation;
import gumtree.spoon.diff.operations.Operation;
import gumtree.spoon.diff.operations.UpdateOperation;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtVariable;

import java.util.List;

/**
 * Created by tdurieux
 */
public class ConstantChangeDetector extends AbstractPatternDetector {

    public ConstantChangeDetector(List<Operation> operations) {
        super(operations);
    }

    private static boolean parentHasMetadata(CtElement element, String metadata) {
        CtElement parent = element.getParent();
        while (parent != null) {
            if (parent.getMetadata(metadata) != null) {
                return true;
            }
            if (parent instanceof CtExpression) {
                parent = parent.getParent();
            } else {
                break;
            }
        }
        return false;
    }
    @Override
    public void detect(RepairPatterns repairPatterns) {
        for (int i = 0; i < operations.size(); i++) {
            Operation operation = operations.get(i);
            if ((operation instanceof UpdateOperation)) {
                CtElement srcNode = operation.getSrcNode();
                CtElement dstNode = operation.getDstNode();
                if (parentHasMetadata(operation.getSrcNode(), "new") || parentHasMetadata(operation.getSrcNode(), "isMoved")) {
                    continue;
                }
                if (dstNode instanceof CtVariableAccess) {
                    CtVariable variable = ((CtVariableAccess)dstNode).getVariable().getDeclaration();
                    if (variable != null) {
                        if (variable.getMetadata("new")!= null) {
                            continue;
                        }
                    }
                }
                if (srcNode instanceof CtLiteral) {
                    repairPatterns.incrementFeatureCounter("constChange");
                }
                if (srcNode instanceof CtVariableAccess &&
                        RepairPatternUtils.isConstantVariableAccess((CtVariableAccess) srcNode)) {
                    repairPatterns.incrementFeatureCounter("constChange");
                }
                if (srcNode instanceof CtTypeAccess &&
                        RepairPatternUtils.isConstantTypeAccess((CtTypeAccess) srcNode)) {
                    repairPatterns.incrementFeatureCounter("constChange");
                }
            } else {
                if (operation instanceof DeleteOperation && operation.getSrcNode() instanceof CtLiteral) {
                    CtLiteral ctLiteral = (CtLiteral) operation.getSrcNode();
                    // try to search a replacement for the literal
                    for (int j = 0; j < operations.size(); j++) {
                        Operation operation2 = operations.get(j);
                        if (operation2 instanceof InsertOperation) {
                            CtElement ctElement = operation2.getSrcNode();
                            boolean isConstantVariable = false;
                            if ((ctElement instanceof CtVariableAccess && RepairPatternUtils.isConstantVariableAccess((CtVariableAccess) ctElement)) ||
                                    (ctElement instanceof CtTypeAccess && RepairPatternUtils.isConstantTypeAccess((CtTypeAccess) ctElement))) {
                                isConstantVariable = true;
                            }
                            if (((InsertOperation) operation2).getParent() == ctLiteral.getParent() && isConstantVariable) {
                                repairPatterns.incrementFeatureCounter("constChange");
                            }
                        }
                    }
                }
            }
        }
    }

}
