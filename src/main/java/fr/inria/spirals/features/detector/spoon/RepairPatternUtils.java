package fr.inria.spirals.features.detector.spoon;

import spoon.reflect.code.CtArrayAccess;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.visitor.filter.TypeFilter;

import java.util.List;

/**
 * Created by fermadeiral
 */
public class RepairPatternUtils {

    public static boolean isNewVariable(CtVariable variable) {
        if (variable.getMetadata("new") != null) {
            return true;
        }
        return false;
    }

    public static boolean isNewBinaryOperator(CtBinaryOperator binaryOperator) {
        if (binaryOperator.getMetadata("new") != null && (Boolean) binaryOperator.getMetadata("new")) {
            return true;
        }
        return false;
    }

    public static boolean isNewConditionInBinaryOperator(CtBinaryOperator binaryOperator) {
        if (binaryOperator.getRightHandOperand().getMetadata("isMoved") == null ||
                binaryOperator.getLeftHandOperand().getMetadata("isMoved") == null) {
            return true;
        }
        return false;
    }

    public static CtVariable getVariableFromReferenceExpression(CtElement referenceExpression) {
        if (referenceExpression instanceof CtVariableAccess) {
            return ((CtVariableAccess) referenceExpression).getVariable().getDeclaration();
        }
        if (referenceExpression instanceof CtArrayAccess) {
            List<CtVariableAccess> variableAccessList = ((CtArrayAccess) referenceExpression).getTarget().getElements(new TypeFilter<>(CtVariableAccess.class));
            if (variableAccessList.size() > 0) {
                return variableAccessList.get(0).getVariable().getDeclaration();
            }
        }
        if (referenceExpression instanceof CtInvocation) {
            List<CtVariableAccess> variableAccessList = ((CtInvocation) referenceExpression).getTarget().getElements(new TypeFilter<>(CtVariableAccess.class));
            if (variableAccessList.size() > 0) {
                return variableAccessList.get(0).getVariable().getDeclaration();
            }
        }
        return null;
    }

}
