package fr.inria.spirals.features.detector.repairpatterns;

import fr.inria.spirals.entities.RepairPatterns;
import fr.inria.spirals.features.detector.spoon.RepairPatternUtils;
import gumtree.spoon.diff.operations.InsertOperation;
import gumtree.spoon.diff.operations.MoveOperation;
import gumtree.spoon.diff.operations.Operation;
import gumtree.spoon.diff.operations.UpdateOperation;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtCase;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtFor;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.code.CtWhile;
import spoon.reflect.code.UnaryOperatorKind;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.path.CtRole;
import spoon.reflect.visitor.EarlyTerminatingScanner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tdurieux
 */
public class LogicExpressionPatternDetector extends AbstractPatternDetector {


    public LogicExpressionPatternDetector(List<Operation> operations) {
        super(operations);
    }

    @Override
    public void detect(RepairPatterns repairPatterns) {
        List<BinaryOperatorKind> mathematicOperator = Arrays.asList(BinaryOperatorKind.SL, BinaryOperatorKind.SR, BinaryOperatorKind.USR, BinaryOperatorKind.PLUS, BinaryOperatorKind.MINUS, BinaryOperatorKind.MUL, BinaryOperatorKind.DIV, BinaryOperatorKind.MOD);
        List<UnaryOperatorKind> unaryOperators = Arrays.asList(UnaryOperatorKind.POSTDEC, UnaryOperatorKind.POSTINC, UnaryOperatorKind.POSTDEC, UnaryOperatorKind.PREDEC);
        for (int i = 0; i < operations.size(); i++) {
            Operation operation = operations.get(i);
            if (operation instanceof MoveOperation) {
                continue;
            }
            if ((operation instanceof UpdateOperation)) {
                CtElement srcNode = operation.getDstNode();
                CtBinaryOperator binaryOperator = srcNode instanceof CtBinaryOperator? (CtBinaryOperator) srcNode :srcNode.getParent(CtBinaryOperator.class);
                if (binaryOperator != null) {
                    if (mathematicOperator.contains(binaryOperator.getKind())) {
                        repairPatterns.incrementFeatureCounter("expArithMod");
                    } else {
                        CtIf parent = srcNode.getParent(CtIf.class);
                        if (parent != null && parent.getMetadata("isMoved") == null  && srcNode == parent.getCondition()) {
                            repairPatterns.incrementFeatureCounter("expLogicMod");
                        }
                    }
                }
                CtUnaryOperator unaryOperator = srcNode instanceof CtUnaryOperator? (CtUnaryOperator) srcNode :srcNode.getParent(CtUnaryOperator.class);
                if (unaryOperator != null) {
                    if (unaryOperators.contains(unaryOperator.getKind())) {
                        repairPatterns.incrementFeatureCounter("expArithMod");
                    }
                }
                continue;
            }
            CtElement srcNode = operation.getSrcNode();
            if (srcNode.isImplicit() && srcNode instanceof CtBlock && ((CtBlock) srcNode).getStatements().size() == 1) {
                srcNode = ((CtBlock) srcNode).getLastStatement();
            }

            if (srcNode instanceof CtBinaryOperator) {
                if (RepairPatternUtils.isMovedCondition((CtBinaryOperator) srcNode)) {
                    continue;
                }
            }

            boolean isExpLogicExOrRed = false;
            if (srcNode instanceof CtBinaryOperator && hasMetaInIt(srcNode, "isMoved")) {
                if (mathematicOperator.contains(((CtBinaryOperator) srcNode).getKind())) {
                    repairPatterns.incrementFeatureCounter("expArithMod");
                }
            }
            if (srcNode instanceof CtCase) {
                if (srcNode.getParent().getMetadata("isMoved") == null) {
                    if (operation instanceof InsertOperation) {
                        repairPatterns.incrementFeatureCounter("expLogicExpand");
                    } else {
                        // cannot delete completely the condition
                        if (!(srcNode.getParent() instanceof CtIf)) {
                            repairPatterns.incrementFeatureCounter("expLogicReduce");
                        }
                    }
                    isExpLogicExOrRed = true;
                }
            }
            if (srcNode instanceof CtBinaryOperator) {
                if (isInCondition(srcNode)) {
                    if (srcNode.getParent().getMetadata("isMoved") == null) {
                        if (operation instanceof InsertOperation) {
                            repairPatterns.incrementFeatureCounter("expLogicExpand");
                        } else {
                            // cannot delete completely the condition
                            if (!(srcNode.getParent() instanceof CtIf) || hasMetaInIt(srcNode, "isMoved")) {
                                repairPatterns.incrementFeatureCounter("expLogicReduce");
                            }
                        }
                        isExpLogicExOrRed = true;
                    }
                }
            }
            if (srcNode instanceof CtIf) {
                // add an else if
                if (srcNode.getParent().getRoleInParent() == CtRole.ELSE && operation instanceof InsertOperation) {
                    repairPatterns.incrementFeatureCounter("expLogicExpand");
                }
                CtExpression<Boolean> condition = ((CtIf) srcNode).getCondition();
                if (hasMetaInIt(condition, "isMoved") && condition.getMetadata("isMoved") == null && !RepairPatternUtils.isMovedCondition(condition)) {
                    if (operation instanceof InsertOperation) {
                        repairPatterns.incrementFeatureCounter("expLogicExpand");
                        isExpLogicExOrRed = true;
                    }
                }
            }

            if (srcNode instanceof CtExpression && operation instanceof InsertOperation && !isExpLogicExOrRed) {
                CtIf parent = srcNode.getParent(CtIf.class);
                CtBinaryOperator binary = srcNode.getParent(CtBinaryOperator.class);
                if (parent != null && isInCondition(binary)
                        && parent.getMetadata("isMoved") == null) {
                    repairPatterns.incrementFeatureCounter("expLogicMod");
                }

            }
            CtBinaryOperator binaryOperator = srcNode.getParent(CtBinaryOperator.class);
            if (binaryOperator != null) {
                if (mathematicOperator.contains(binaryOperator.getKind())) {
                    repairPatterns.incrementFeatureCounter("expArithMod");
                }
            }

        }
    }

    private boolean isInCondition(CtElement element) {
        if (element == null) {
            return false;
        }
        CtIf ifParent = element.getParent(CtIf.class);
        if (ifParent != null && (element.hasParent(ifParent.getCondition()) || element == ifParent.getCondition())) {
            return true;
        }
        CtFor forParent = element.getParent(CtFor.class);
        if (forParent != null && (element.hasParent(forParent.getExpression()) || element == forParent.getExpression())) {
            return true;
        }
        CtWhile whileParent = element.getParent(CtWhile.class);
        if (whileParent != null && (element.hasParent(whileParent.getLoopingExpression()) || element == whileParent.getLoopingExpression())) {
            return true;
        }
        return false;
    }

    private boolean hasMetaInIt(CtElement element, String meta) {
        EarlyTerminatingScanner hasMeta = new EarlyTerminatingScanner() {
            @Override
            public void scan(CtElement element) {
                if (element != null && element.getMetadata(meta) != null) {
                    setResult(element);
                    terminate();
                }
                super.scan(element);
            }
        };
        hasMeta.scan(element);
        return hasMeta.getResult() != null;
    }
}
