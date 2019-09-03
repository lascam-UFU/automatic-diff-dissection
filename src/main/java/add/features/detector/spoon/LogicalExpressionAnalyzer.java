package add.features.detector.spoon;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtArrayRead;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtConditional;
import spoon.reflect.code.CtConstructorCall;
import spoon.reflect.code.CtDo;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtFor;
import spoon.reflect.code.CtForEach;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.code.CtSwitch;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.code.CtWhile;
import spoon.reflect.declaration.CtElement;

public class LogicalExpressionAnalyzer {
    
    public static List<CtExpression> getAllRootLogicalExpressions (CtElement parentline) {
        
        List<CtExpression> logicalExpressions = new ArrayList();

        CtElement elementToStudy = retrieveElementToStudy(parentline);

        List<CtExpression> expressionssFromFaultyLine = elementToStudy.getElements(e -> (e instanceof CtExpression)).stream()
                .map(CtExpression.class::cast).collect(Collectors.toList());
        
        for(int index=0; index<expressionssFromFaultyLine.size(); index++) {

            if(isBooleanExpression(expressionssFromFaultyLine.get(index)) &&
                    !whetherparentboolean(expressionssFromFaultyLine.get(index)) &&
                    !logicalExpressions.contains(expressionssFromFaultyLine.get(index))) {
                logicalExpressions.add(expressionssFromFaultyLine.get(index));
            }
        } 
        
        return logicalExpressions;
    }
    
   public static List<CtBinaryOperator> getAllBinaryOperators (CtElement parentline) {

        CtElement elementToStudy = retrieveElementToStudy(parentline);

        List<CtBinaryOperator> binaryOperatorsFromFaultyLine = elementToStudy.getElements(e -> (e instanceof CtBinaryOperator)).stream()
                .map(CtBinaryOperator.class::cast).collect(Collectors.toList());
        
        return binaryOperatorsFromFaultyLine;
    }
    
    public static List<CtExpression> getAllExpressions (CtElement parentline) {

        CtElement elementToStudy = retrieveElementToStudy(parentline);

        List<CtExpression> expressionssFromFaultyLine = elementToStudy.getElements(e -> (e instanceof CtExpression)).stream()
                .map(CtExpression.class::cast).collect(Collectors.toList());
        
        LinkedHashSet<CtExpression> hashSetExpressions = new LinkedHashSet<>(expressionssFromFaultyLine);
        ArrayList<CtExpression> listExpressionWithoutDuplicates = new ArrayList<>(hashSetExpressions);
        
        ArrayList<CtExpression> removeUndesirable = new ArrayList<>();
        
        for(int index=0; index<listExpressionWithoutDuplicates.size(); index++) {
            
            CtExpression certainExpression = listExpressionWithoutDuplicates.get(index);
            
            if(certainExpression instanceof CtVariableAccess || certainExpression instanceof CtLiteral ||
                    certainExpression instanceof CtInvocation || certainExpression instanceof CtConstructorCall ||
                    certainExpression instanceof CtArrayRead || analyzeWhetherAE(certainExpression))
                removeUndesirable.add(certainExpression);
         }
        
        return removeUndesirable;
    }
    
    private static boolean analyzeWhetherAE(CtExpression expression) {
        
        try {
            List<BinaryOperatorKind> opKinds = new ArrayList<>();
            opKinds.add(BinaryOperatorKind.DIV);
            opKinds.add(BinaryOperatorKind.PLUS);
            opKinds.add(BinaryOperatorKind.MINUS);
            opKinds.add(BinaryOperatorKind.MUL);
            opKinds.add(BinaryOperatorKind.MOD);
            
            if(expression instanceof CtBinaryOperator && opKinds.contains(((CtBinaryOperator) expression).getKind()))
                return true;

        } catch (Throwable e) {
            e.printStackTrace();
        }
        
        return false;
    }

    public static CtElement retrieveElementToStudy(CtElement element) {

        if (element instanceof CtIf) {
            return (((CtIf) element).getCondition());
        } else if (element instanceof CtWhile) {
            return (((CtWhile) element).getLoopingExpression());
        } else if (element instanceof CtFor) {
            return (((CtFor) element).getExpression());
        } else if (element instanceof CtDo) {
            return (((CtDo) element).getLoopingExpression());
        } else if (element instanceof CtForEach) {
            return (((CtForEach) element).getExpression());
        } else if (element instanceof CtSwitch) {
            return (((CtSwitch) element).getSelector());
        } else
            return (element);
    }
    
    public static boolean whetherparentboolean (CtExpression tostudy) {
        
        CtElement parent= tostudy;
        while(parent!=null) {        
            parent=parent.getParent();
            
            if(isBooleanExpression(parent))
                return true;
        }
        
        return false;
    }
    
    public static boolean isBooleanExpression(CtElement currentexpression) {
        
        if (currentexpression == null)
            return false;
        
        if (isLogicalExpression(currentexpression)) {
            return true;
        }
        
        if(currentexpression instanceof CtExpression) {
           
           CtExpression exper= (CtExpression) currentexpression;
           try {
              if (exper.getType() != null
                && exper.getType().unbox().toString().equals("boolean")) {
              return true;
             }
           } catch (Exception e) {
               return false;
           }
        }

        return false;
    }
    
    public static boolean isLogicalExpression (CtElement currentElement) {
        
        if (currentElement == null)
            return false;
        
        if ((currentElement instanceof CtBinaryOperator)) {
            
            CtBinaryOperator binOp = (CtBinaryOperator) currentElement;
                        
            if(binOp.getKind().equals(BinaryOperatorKind.AND) || binOp.getKind().equals(BinaryOperatorKind.OR)
                || binOp.getKind().equals(BinaryOperatorKind.EQ)
                || binOp.getKind().equals(BinaryOperatorKind.GE)
                || binOp.getKind().equals(BinaryOperatorKind.GT)
                || binOp.getKind().equals(BinaryOperatorKind.INSTANCEOF)
                || binOp.getKind().equals(BinaryOperatorKind.LE)
                || binOp.getKind().equals(BinaryOperatorKind.LT)
                || binOp.getKind().equals(BinaryOperatorKind.NE)
                || (binOp.getType() != null &&
                      binOp.getType().unbox().getSimpleName().equals("boolean")))
                
                   return true;
        }
        
        if(currentElement.getParent() instanceof CtConditional) {
            CtConditional cond = (CtConditional) currentElement.getParent();
            if(currentElement.equals(cond.getCondition()))
                return true;
        }
        
        if(currentElement.getParent() instanceof CtIf) {
            CtIf ifcond = (CtIf) currentElement.getParent();
            if(currentElement.equals(ifcond.getCondition()))
                return true;
        }
        
        if(currentElement.getParent() instanceof CtWhile) {
            CtWhile whilecond = (CtWhile) currentElement.getParent();
            if(currentElement.equals(whilecond.getLoopingExpression()))
                return true;
        }
        
        if(currentElement.getParent() instanceof CtDo) {
            CtDo docond = (CtDo) currentElement.getParent();
            if(currentElement.equals(docond.getLoopingExpression()))
                return true;
        }
        
        if(currentElement.getParent() instanceof CtFor) {
            CtFor forcond = (CtFor) currentElement.getParent();
            if(currentElement.equals(forcond.getExpression()))
                return true;
        }
        
        return false;
    }
}
