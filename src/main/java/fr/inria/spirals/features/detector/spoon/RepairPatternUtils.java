package fr.inria.spirals.features.detector.spoon;

import gumtree.spoon.diff.operations.MoveOperation;
import gumtree.spoon.diff.operations.Operation;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.visitor.filter.TypeFilter;

import java.util.ArrayList;
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

    public static boolean isNewStatement(CtStatement statement) {
        if (statement.getMetadata("new") != null) {
            return true;
        }
        return false;
    }

    public static boolean isThereOldStatementInBlock(CtBlock block) {
        for (CtStatement statement : block.getStatements()) {
            if (!RepairPatternUtils.isNewStatement(statement)) {
                return true;
            }
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

    public static List<Operation> getOperationsWithoutMoveOperation(List<Operation> operations) {
        List<Operation> operationsWithoutMoveOperation = new ArrayList<>();
        for (Operation operation : operations) {
            if (!(operation instanceof MoveOperation)) {
                operationsWithoutMoveOperation.add(operation);
            }
        }
        return operationsWithoutMoveOperation;
    }

    public static int getNumberOfNewStatements(List<CtStatement> statements) {
        int newStatements = 0;
        for (CtStatement statement : statements) {
            if (RepairPatternUtils.isNewStatement(statement)) {
                newStatements++;
            }
        }
        return newStatements;
    }

    public static boolean areAllOperationsAtTheSamePosition(List<Operation> operations) {
        boolean allSamePosition = true;
        int position = operations.get(0).getSrcNode().getPosition().getLine();
        for (int i = 1; i < operations.size(); i++) {
            if (operations.get(i).getSrcNode().getPosition().getLine() != position) {
                allSamePosition = false;
                break;
            }
        }
        return allSamePosition;
    }

}
