package add.features.detector.spoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.github.gumtreediff.tree.ITree;

import add.features.detector.repairpatterns.MappingAnalysis;
import add.features.detector.spoon.filter.ReturnInsideConditionalFilter;
import add.features.detector.spoon.filter.ThrowInsideConditionalFilter;
import gumtree.spoon.builder.SpoonGumTreeBuilder;
import gumtree.spoon.diff.Diff;
import gumtree.spoon.diff.operations.MoveOperation;
import gumtree.spoon.diff.operations.Operation;
import spoon.reflect.code.CtArrayAccess;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtCatch;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtFor;
import spoon.reflect.code.CtForEach;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtReturn;
import spoon.reflect.code.CtStatement;
import spoon.reflect.code.CtThisAccess;
import spoon.reflect.code.CtThrow;
import spoon.reflect.code.CtTypeAccess;
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.code.CtWhile;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.filter.LineFilter;
import spoon.reflect.visitor.filter.TypeFilter;

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

    public static boolean isDeletedBinaryOperator(CtBinaryOperator binaryOperator) {
        if (binaryOperator.getMetadata("delete") != null && (Boolean) binaryOperator.getMetadata("delete")) {
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

    public static boolean isDeletedUnaryOperator(CtUnaryOperator unaryOperator) {
        if (unaryOperator.getMetadata("delete") != null && (Boolean) unaryOperator.getMetadata("delete")) {
            return true;
        }
        return false;
    }

    public static boolean isMovedCondition(CtElement binaryOperator) {
        if (!(binaryOperator instanceof CtBinaryOperator)) {
            return false;
        }
        return (((CtBinaryOperator) binaryOperator).getLeftHandOperand().getMetadata("isMoved") != null
                && ((CtBinaryOperator) binaryOperator).getRightHandOperand().getMetadata("isMoved") != null);
    }

    public static boolean isNewConditionInBinaryOperator(CtBinaryOperator binaryOperator) {
        if (binaryOperator.getRightHandOperand().getMetadata("isMoved") == null
                || binaryOperator.getLeftHandOperand().getMetadata("isMoved") == null) {
            return true;
        }
        return false;
    }

    public static boolean isDeletedConditionInBinaryOperator(CtBinaryOperator binaryOperator) {
        if (binaryOperator.getRightHandOperand().getMetadata("delete") != null
                || binaryOperator.getLeftHandOperand().getMetadata("delete") != null) {
            return true;
        }
        return false;
    }

    public static boolean isUpdatedConditionInBinaryOperator(CtBinaryOperator binaryOperator) {
        if (binaryOperator.getRightHandOperand().getMetadata("update") != null
                || binaryOperator.getLeftHandOperand().getMetadata("update") != null) {
            return true;
        }
        return false;
    }

    public static boolean isNewConditionInUnaryOperator(CtUnaryOperator unaryOperator) {
        if (unaryOperator.getOperand().getMetadata("isMoved") == null) {
            return true;
        }
        return false;
    }

    public static boolean isUpdatedConditionInUnaryOperator(CtUnaryOperator unaryOperator) {
        if (unaryOperator.getOperand().getMetadata("update") != null) {
            return true;
        }
        return false;
    }

    public static boolean isExistingConditionInBinaryOperator(CtBinaryOperator binaryOperator) {
        if (binaryOperator.getRightHandOperand().getMetadata("isMoved") != null
                || binaryOperator.getLeftHandOperand().getMetadata("isMoved") != null) {
            return true;
        }
        return false;
    }

    public static boolean isExistingConditionInUnaryOperator(CtUnaryOperator unaryOperator) {
        if (unaryOperator.getOperand().getMetadataKeys().size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isStringInvolvedInBinaryOperator(CtBinaryOperator binaryOperator) {
        boolean isStringInvolved = false;
        List<CtTypeReference> types = binaryOperator.getElements(new TypeFilter<>(CtTypeReference.class));
        for (CtTypeReference typeReference : types) {
            if (typeReference.getSimpleName().equals("String")) {
                isStringInvolved = true;
            }
        }
        return isStringInvolved;
    }

    public static boolean wasConditionChangedInIf(CtIf ctIf) {
        boolean wasConditionChanged = false;
        List<CtBinaryOperator> binaryOperatorList = ctIf.getCondition()
                .getElements(new TypeFilter<>(CtBinaryOperator.class));
        for (CtBinaryOperator ctBinaryOperator : binaryOperatorList) {
            if (RepairPatternUtils.isNewBinaryOperator(ctBinaryOperator)) {
                wasConditionChanged = true;
            }
        }
        List<CtUnaryOperator> unaryOperatorList = ctIf.getCondition()
                .getElements(new TypeFilter<>(CtUnaryOperator.class));
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
            List<CtBinaryOperator> binaryOperatorList = ctIf.getCondition()
                    .getElements(new TypeFilter<>(CtBinaryOperator.class));
            for (CtBinaryOperator ctBinaryOperator : binaryOperatorList) {
                if (!isNewBinaryOperator(ctBinaryOperator)) {
                    return false;
                }
            }
            List<CtUnaryOperator> unaryOperatorList = ctIf.getCondition()
                    .getElements(new TypeFilter<>(CtUnaryOperator.class));
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
            List<CtBinaryOperator> binaryOperatorList = ctWhile.getLoopingExpression()
                    .getElements(new TypeFilter<>(CtBinaryOperator.class));
            for (CtBinaryOperator ctBinaryOperator : binaryOperatorList) {
                if (!isNewBinaryOperator(ctBinaryOperator)) {
                    return false;
                }
            }
            List<CtUnaryOperator> unaryOperatorList = ctWhile.getLoopingExpression()
                    .getElements(new TypeFilter<>(CtUnaryOperator.class));
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
            List<CtBinaryOperator> binaryOperatorList = ctFor.getExpression()
                    .getElements(new TypeFilter<>(CtBinaryOperator.class));
            for (CtBinaryOperator ctBinaryOperator : binaryOperatorList) {
                if (!isNewBinaryOperator(ctBinaryOperator)) {
                    return false;
                }
            }
            List<CtUnaryOperator> unaryOperatorList = ctFor.getExpression()
                    .getElements(new TypeFilter<>(CtUnaryOperator.class));
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

    public static List<CtStatement> getIsThereOldStatementInStatementList(Diff diff, List<CtStatement> statements) {
        List<CtStatement> statementsNotNew = new ArrayList<>();
        for (CtStatement statement : statements) {
            if (!RepairPatternUtils.isNewStatement(statement)) {

                ITree leftMoved = MappingAnalysis.getLeftFromRightNodeMapped(diff, statement);
                if (leftMoved != null) {
                    CtElement susp = (CtElement) leftMoved.getMetadata(SpoonGumTreeBuilder.SPOON_OBJECT);
                    if (susp instanceof CtStatement) {
                        statementsNotNew.add((CtStatement) susp);
                    }
                }

                // statementsNotNew.add(statement);

            }
        }
        return statementsNotNew;
    }
    
    public static CtElement getElementInOld(Diff diff, CtElement elementInNew) {
        ITree leftTree = MappingAnalysis.getLeftFromRightNodeMapped(diff, elementInNew);
        CtElement oldElement = null;
        if(leftTree!=null)
            oldElement = (CtElement) leftTree.getMetadata(SpoonGumTreeBuilder.SPOON_OBJECT);

        return oldElement;
    }
    
    public static boolean getIsInvocationInStatemnt(Diff diff, CtElement oldline, CtElement newinvocationorconstructor) {
        
        CtElement newline = MappingAnalysis.getParentLine(new LineFilter(), newinvocationorconstructor);

        ITree treeoldline= MappingAnalysis.getRightFromLeftNodeMapped(diff, oldline); 

        if(treeoldline==null)
            return false;
        
        CtElement newoldline = (CtElement) treeoldline.getMetadata(SpoonGumTreeBuilder.SPOON_OBJECT);
        
        return newline==newoldline;
    }
    
    public static boolean getIsMovedExpressionInStatemnt(Diff diff, CtStatement oldstat, CtExpression oldexpression) {
        
        ITree rightTreeStat= MappingAnalysis.getRightFromLeftNodeMapped(diff, oldstat); 
        ITree rightTreeExper= MappingAnalysis.getRightFromLeftNodeMapped(diff, oldexpression); 

        if(rightTreeStat==null || rightTreeExper==null)
            return true;
        
        CtElement newstatement = (CtElement) rightTreeStat.getMetadata(SpoonGumTreeBuilder.SPOON_OBJECT);
        CtElement newexper = (CtElement) rightTreeExper.getMetadata(SpoonGumTreeBuilder.SPOON_OBJECT);

        CtStatement statementParent = newexper.getParent(new TypeFilter<>(CtStatement.class));

        return newstatement==statementParent;
    }

    public static List<CtStatement> getIsThereOldStatementInStatementList_old(List<CtStatement> statements) {
        List<CtStatement> statementsNotNew = new ArrayList<>();
        for (CtStatement statement : statements) {
            if (!RepairPatternUtils.isNewStatement(statement)) {

                statementsNotNew.add(statement);
                // return true;
            }
        }
        return statementsNotNew;
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
            List<CtVariableAccess> variableAccessList = ((CtArrayAccess) referenceExpression).getTarget()
                    .getElements(new TypeFilter<>(CtVariableAccess.class));
            if (variableAccessList.size() > 0) {
                return variableAccessList.get(0).getVariable().getDeclaration();
            }
        }
        if (referenceExpression instanceof CtInvocation) {
            List<CtVariableAccess> variableAccessList = ((CtInvocation) referenceExpression).getTarget()
                    .getElements(new TypeFilter<>(CtVariableAccess.class));
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

    public static boolean isConstantVariableAccess(CtVariableAccess ctVariableAccess) {
        Set<ModifierKind> modifiers = ctVariableAccess.getVariable().getModifiers();
        if (modifiers.contains(ModifierKind.FINAL)) {
            return true;
        } else {
            String simpleName = ctVariableAccess.getVariable().getSimpleName();
            if (simpleName.toUpperCase().equals(simpleName)) {
                return true;
            }
        }
        return false;
    }

//    public static boolean isConstantTypeAccess(CtTypeAccess ctTypeAccess) {
//        
//        Set<ModifierKind> modifiers = ctTypeAccess.getType().getModifiers();
//        if (modifiers.contains(ModifierKind.FINAL)) {
//            return true;
//        } else {
//            String simpleName = ctTypeAccess.getAccessedType().getSimpleName();
//            if (simpleName.toUpperCase().equals(simpleName)) {
//                return true;
//            }
//        }
//        return false;
//    }
    
   public static boolean isConstantTypeAccess(CtTypeAccess ctTypeAccess) {
        
        String fullname=ctTypeAccess.getAccessedType().getQualifiedName();
        
        String[] splitname=fullname.split("\\.");
        if (splitname.length>1) {
            String simplename=splitname[splitname.length-1];
            if (simplename.toUpperCase().equals(simplename)) 
                return true;
        }        
        return false;
    }
    
    public static boolean isThisAccess(CtTypeAccess ctTypeAccess) {
        // Ignore CtThisAccess
                CtClass classname =(CtClass) ctTypeAccess.getParent(CtClass.class);
                if(classname.getSimpleName().equals(ctTypeAccess.getAccessedType().getSimpleName())) 
                    return true;
                else return false;
    }

    public static boolean isOldStatementInLoop(CtElement oldStatement) {
        
        if((oldStatement.getParent() instanceof CtFor)||(oldStatement.getParent() instanceof CtForEach)
                || (oldStatement.getParent() instanceof CtWhile))
            return true;
        else if ((oldStatement.getParent().getParent() instanceof CtFor)||(oldStatement.getParent().getParent() instanceof CtForEach)
                || (oldStatement.getParent().getParent() instanceof CtWhile))
            return true;
        else return false;
    }
}
