package fr.inria.spirals.features.detector.spoon;

import fr.inria.spirals.features.detector.spoon.filter.ReturnInsideConditionalFilter;
import fr.inria.spirals.features.detector.spoon.filter.ThrowInsideConditionalFilter;
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

    public static boolean isNewUnaryOperator(CtUnaryOperator unaryOperator) {
        if (unaryOperator.getMetadata("new") != null && (Boolean) unaryOperator.getMetadata("new")) {
            return true;
        }
        return false;
    }

    public static boolean isMovedCondition(CtElement binaryOperator) {
        if (!(binaryOperator instanceof CtBinaryOperator)) {
            return false;
        }
        return (((CtBinaryOperator) binaryOperator).getLeftHandOperand().getMetadata("isMoved") != null &&
                ((CtBinaryOperator) binaryOperator).getRightHandOperand().getMetadata("isMoved")  != null);
    }

    public static boolean isNewConditionInBinaryOperator(CtBinaryOperator binaryOperator) {
        if (binaryOperator.getRightHandOperand().getMetadata("isMoved") == null ||
                binaryOperator.getLeftHandOperand().getMetadata("isMoved") == null) {
            return true;
        }
        return false;
    }

    public static boolean wasConditionChangedInIf(CtIf ctIf) {
        boolean wasConditionChanged = false;
        List<CtBinaryOperator> binaryOperatorList = ctIf.getCondition().getElements(new TypeFilter<>(CtBinaryOperator.class));
        for (CtBinaryOperator ctBinaryOperator : binaryOperatorList) {
            if (RepairPatternUtils.isNewBinaryOperator(ctBinaryOperator)) {
                wasConditionChanged = true;
            }
        }
        List<CtUnaryOperator> unaryOperatorList = ctIf.getCondition().getElements(new TypeFilter<>(CtUnaryOperator.class));
        for (CtUnaryOperator ctUnaryOperator : unaryOperatorList) {
            if (RepairPatternUtils.isNewUnaryOperator(ctUnaryOperator)) {
                wasConditionChanged = true;
            }
        }
        return wasConditionChanged;
    }

    public static boolean isNewStatement(CtStatement statement) {
        if (statement.getMetadata("new") != null) {
            return true;
        }
        return false;
    }

    public static boolean isMovedStatement(CtStatement statement) {
        if (statement.getMetadata("isMoved") != null) {
            return true;
        }
        return false;
    }

    public static boolean isMovingSrcStatement(CtStatement statement) {
        if (isMovedStatement(statement) && statement.getMetadata("movingSrc") != null) {
            return true;
        }
        return false;
    }

    public static boolean isMovingDstStatement(CtStatement statement) {
        if (isMovedStatement(statement) && statement.getMetadata("movingDst") != null) {
            return true;
        }
        return false;
    }

    public static boolean isNewIf(CtIf ctIf) {
        if (ctIf.getMetadata("new") != null) {
            List<CtBinaryOperator> binaryOperatorList = ctIf.getCondition().getElements(new TypeFilter<>(CtBinaryOperator.class));
            for (CtBinaryOperator ctBinaryOperator : binaryOperatorList) {
                if (!isNewBinaryOperator(ctBinaryOperator)) {
                    return false;
                }
            }
            List<CtUnaryOperator> unaryOperatorList = ctIf.getCondition().getElements(new TypeFilter<>(CtUnaryOperator.class));
            for (CtUnaryOperator ctUnaryOperator : unaryOperatorList) {
                if (!isNewUnaryOperator(ctUnaryOperator)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isNewWhile(CtWhile ctWhile) {
        if (ctWhile.getMetadata("new") != null) {
            List<CtBinaryOperator> binaryOperatorList = ctWhile.getLoopingExpression().getElements(new TypeFilter<>(CtBinaryOperator.class));
            for (CtBinaryOperator ctBinaryOperator : binaryOperatorList) {
                if (!isNewBinaryOperator(ctBinaryOperator)) {
                    return false;
                }
            }
            List<CtUnaryOperator> unaryOperatorList = ctWhile.getLoopingExpression().getElements(new TypeFilter<>(CtUnaryOperator.class));
            for (CtUnaryOperator ctUnaryOperator : unaryOperatorList) {
                if (!isNewUnaryOperator(ctUnaryOperator)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isNewFor(CtFor ctFor) {
        if (ctFor.getMetadata("new") != null) {
            List<CtBinaryOperator> binaryOperatorList = ctFor.getExpression().getElements(new TypeFilter<>(CtBinaryOperator.class));
            for (CtBinaryOperator ctBinaryOperator : binaryOperatorList) {
                if (!isNewBinaryOperator(ctBinaryOperator)) {
                    return false;
                }
            }
            List<CtUnaryOperator> unaryOperatorList = ctFor.getExpression().getElements(new TypeFilter<>(CtUnaryOperator.class));
            for (CtUnaryOperator ctUnaryOperator : unaryOperatorList) {
                if (!isNewUnaryOperator(ctUnaryOperator)) {
                    return false;
                }
            }
            if (!isThereOnlyNewStatementsInStatementList(ctFor.getForInit())) {
                return false;
            }
            if (!isThereOnlyNewStatementsInStatementList(ctFor.getForUpdate())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean isNewForEach(CtForEach ctForEach) {
        if (ctForEach.getMetadata("new") != null) {
            if (ctForEach.getVariable().getMetadata("new") == null) {
                return false;
            }
            if (ctForEach.getExpression().getMetadata("new") == null) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean isThereOnlyNewStatementsInStatementList(List<CtStatement> statements) {
        for (CtStatement statement : statements) {
            if (!isNewStatement(statement)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isThereOldStatementInStatementList(List<CtStatement> statements) {
        for (CtStatement statement : statements) {
            if (!RepairPatternUtils.isNewStatement(statement)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isThereOnlyNewAndMovedStatementsInStatementList(List<CtStatement> statements) {
        for (CtStatement statement : statements) {
            if (!isNewStatement(statement)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isThereOnlyRemovedAndMovedAwayStatementsInRemovedIf(CtIf ctIf) {
        CtBlock thenBlock = ctIf.getThenStatement();
        for (CtStatement statement : thenBlock.getStatements()) {
            if (isNewStatement(statement)) {
                continue;
            }
            if (isMovingSrcStatement(statement)) {
                continue;
            }
            return false;
        }
        return true;
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

    public static boolean isThereReturnInIfOrCase(CtElement ctElement) {
        List<CtReturn> returnList = ctElement.getElements(new ReturnInsideConditionalFilter(ctElement));
        return (returnList.size() > 0) ? true : false;
    }

    public static boolean isThereThrowInIfOrCase(CtElement ctElement) {
        List<CtThrow> throwList = ctElement.getElements(new ThrowInsideConditionalFilter(ctElement));
        return (throwList.size() > 0) ? true : false;
    }

    public static boolean isThereOnlyNewCatch(List<CtCatch> catchList) {
        for (CtCatch ctCatch : catchList) {
            if (!(ctCatch.getMetadata("new") != null)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isThereChangesInChildren(CtElement ctElement) {
        boolean isThereChanges = false;
        List<CtElement> children = ctElement.getElements(new TypeFilter<>(CtElement.class));
        for (CtElement child : children) {
            if (child.getMetadata("new") != null) {
                isThereChanges = true;
            }
        }
        return isThereChanges;
    }

}
