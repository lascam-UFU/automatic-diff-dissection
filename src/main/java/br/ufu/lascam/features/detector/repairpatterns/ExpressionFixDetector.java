package br.ufu.lascam.features.detector.repairpatterns;

import br.ufu.lascam.entities.RepairPatterns;
import br.ufu.lascam.features.detector.spoon.RepairPatternUtils;
import gumtree.spoon.diff.operations.InsertOperation;
import gumtree.spoon.diff.operations.MoveOperation;
import gumtree.spoon.diff.operations.Operation;
import gumtree.spoon.diff.operations.UpdateOperation;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.path.CtRole;
import spoon.reflect.visitor.EarlyTerminatingScanner;
import spoon.reflect.visitor.filter.LineFilter;
import spoon.reflect.visitor.filter.TypeFilter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tdurieux
 */
public class ExpressionFixDetector extends AbstractPatternDetector {


    public ExpressionFixDetector(List<Operation> operations) {
        super(operations);
    }

    @Override
    public void detect(RepairPatterns repairPatterns) {
        List<BinaryOperatorKind> mathematicOperator = Arrays.asList(BinaryOperatorKind.SL, BinaryOperatorKind.SR, BinaryOperatorKind.USR, BinaryOperatorKind.PLUS, BinaryOperatorKind.MINUS, BinaryOperatorKind.MUL, BinaryOperatorKind.DIV, BinaryOperatorKind.MOD);
        List<UnaryOperatorKind> unaryOperators = Arrays.asList(UnaryOperatorKind.PREINC, UnaryOperatorKind.POSTINC, UnaryOperatorKind.POSTDEC, UnaryOperatorKind.PREDEC);
        List<BinaryOperatorKind> logicOperators = Arrays.asList(BinaryOperatorKind.OR, BinaryOperatorKind.AND, BinaryOperatorKind.BITOR, BinaryOperatorKind.BITXOR, BinaryOperatorKind.BITAND, BinaryOperatorKind.EQ, BinaryOperatorKind.NE, BinaryOperatorKind.LT, BinaryOperatorKind.GT, BinaryOperatorKind.LE, BinaryOperatorKind.GE);
        List<BinaryOperatorKind> conditionalOperators = Arrays.asList(BinaryOperatorKind.AND, BinaryOperatorKind.OR);
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
                        if (!RepairPatternUtils.isStringInvolvedInBinaryOperator(binaryOperator)) {
                            repairPatterns.incrementFeatureCounter("expArithMod");
                        }
                    } else {
                        CtElement parent = binaryOperator.getParent(new LineFilter());
                        if (parent != null && parent instanceof CtIf) {
                            CtIf parentIf = (CtIf) parent;
                            if (parentIf.getMetadata("isMoved") == null) {
                                repairPatterns.incrementFeatureCounter("expLogicMod");
                            }
                        } else {
                            CtBinaryOperator parentBinaryOperator = binaryOperator;
                            while (parentBinaryOperator.getParent() instanceof CtBinaryOperator) {
                                parentBinaryOperator = (CtBinaryOperator) parentBinaryOperator.getParent();
                            }
                            if (hasMetaInIt(parentBinaryOperator, "update")) {
                                repairPatterns.incrementFeatureCounter("expLogicMod");
                            }
                        }
                    }
                }
                CtUnaryOperator unaryOperator = srcNode instanceof CtUnaryOperator? (CtUnaryOperator) srcNode :srcNode.getParent(CtUnaryOperator.class);
                if (unaryOperator != null) {
                    if (unaryOperators.contains(unaryOperator.getKind())) {
                        repairPatterns.incrementFeatureCounter("expArithMod");
                    }
                }
                if (srcNode.getParent() instanceof CtIf && srcNode.getRoleInParent() == CtRole.CONDITION) {
                    repairPatterns.incrementFeatureCounter("expLogicMod");
                }
            } else {
                CtElement srcNode = operation.getSrcNode();
                if (srcNode.isImplicit() && srcNode instanceof CtBlock && ((CtBlock) srcNode).getStatements().size() == 1) {
                    srcNode = ((CtBlock) srcNode).getLastStatement();
                }

                boolean isExpLogicExOrRed = false;
                if (srcNode instanceof CtBinaryOperator) {
                    if (RepairPatternUtils.isMovedCondition((CtBinaryOperator) srcNode)) {
                        continue;
                    }

                    if (hasMetaInIt(srcNode, "isMoved")) {
                        if (mathematicOperator.contains(((CtBinaryOperator) srcNode).getKind())) {
                            if (!RepairPatternUtils.isStringInvolvedInBinaryOperator((CtBinaryOperator) srcNode)) {
                                repairPatterns.incrementFeatureCounter("expArithMod");
                            }
                        }
                    }

                    CtBinaryOperator ctBinaryOperator = (CtBinaryOperator) srcNode;
                    CtBinaryOperator parentBinaryOperator = ctBinaryOperator.getParent(CtBinaryOperator.class);
                    if (parentBinaryOperator == null) {
                        parentBinaryOperator = ctBinaryOperator;
                    }
                    boolean isThereOldCondition = false;
                    boolean isThereNewCondition = false;
                    boolean isThereDeletedCondition = false;
                    boolean isThereChangedCondition = false;
                    List<CtBinaryOperator> binaryOperatorList = parentBinaryOperator.getElements(new TypeFilter<>(CtBinaryOperator.class));
                    for (CtBinaryOperator binaryOperator : binaryOperatorList) {
                        if (conditionalOperators.contains(ctBinaryOperator.getKind())) {
                            if (RepairPatternUtils.isNewBinaryOperator(binaryOperator) &&
                                    !RepairPatternUtils.isDeletedBinaryOperator(binaryOperator) &&
                                    RepairPatternUtils.isNewConditionInBinaryOperator(binaryOperator)) {
                                isThereNewCondition = true;
                            }
                            if (RepairPatternUtils.isExistingConditionInBinaryOperator(binaryOperator)) {
                                isThereOldCondition = true;
                            }
                            if (RepairPatternUtils.isDeletedBinaryOperator(binaryOperator) &&
                                    RepairPatternUtils.isDeletedConditionInBinaryOperator(binaryOperator)) {
                                isThereDeletedCondition = true;
                            }
                            if (RepairPatternUtils.isUpdatedConditionInBinaryOperator(binaryOperator)) {
                                isThereChangedCondition = true;
                            }
                        }
                    }
                    List<CtUnaryOperator> unaryOperatorList = parentBinaryOperator.getElements(new TypeFilter<>(CtUnaryOperator.class));
                    for (CtUnaryOperator unaryOperator : unaryOperatorList) {
                        //if (logicOperators.contains(ctBinaryOperator.getKind()))
                        if (RepairPatternUtils.isNewUnaryOperator(unaryOperator) &&
                                !RepairPatternUtils.isDeletedUnaryOperator(unaryOperator) &&
                                RepairPatternUtils.isNewConditionInUnaryOperator(unaryOperator)) {
                            isThereNewCondition = true;
                        }
                        if (RepairPatternUtils.isExistingConditionInUnaryOperator(unaryOperator)) {
                            isThereOldCondition = true;
                        }
                        if (RepairPatternUtils.isDeletedUnaryOperator(unaryOperator)) {
                            isThereDeletedCondition = true;
                        }
                        if (RepairPatternUtils.isUpdatedConditionInUnaryOperator(unaryOperator)) {
                            isThereChangedCondition = true;
                        }
                    }
                    if (isThereChangedCondition) {
                        repairPatterns.incrementFeatureCounter("expLogicMod");
                        isExpLogicExOrRed = true;
                    }
                    if (isThereOldCondition && isThereNewCondition) {
                        repairPatterns.incrementFeatureCounter("expLogicExpand");
                        isExpLogicExOrRed = true;
                    }
                    if (isThereOldCondition && isThereDeletedCondition) {
                        repairPatterns.incrementFeatureCounter("expLogicReduce");
                        isExpLogicExOrRed = true;
                    }
                }

                if (srcNode instanceof CtUnaryOperator) {
                    if (isInCondition(srcNode)) {
                        if (srcNode.getParent().getMetadata("isMoved") == null) {
                            if (operation instanceof InsertOperation) {
                                if (hasMetaInIt(((InsertOperation) operation).getParent(), "delete")) {
                                    repairPatterns.incrementFeatureCounter("expLogicMod");
                                }
                            }
                        }
                    }
                }

                if (srcNode instanceof CtCase) {
                    CtCase ctCase = (CtCase) srcNode;
                    if (ctCase.getParent().getMetadata("isMoved") == null) {
                        if (ctCase.getStatements().size() == 0) {
                            if (operation instanceof InsertOperation) {
                                repairPatterns.incrementFeatureCounter("expLogicExpand");
                                isExpLogicExOrRed = true;
                            } else {
                                // cannot delete completely the condition
                                if (!(srcNode.getParent() instanceof CtIf) || hasMetaInIt(srcNode, "isMoved")) {
                                    repairPatterns.incrementFeatureCounter("expLogicReduce");
                                    isExpLogicExOrRed = true;
                                }
                            }
                        } else {
                            if (RepairPatternUtils.isThereOldStatementInStatementList(ctCase.getStatements())) {
                                for (int j = 0; j < operations.size(); j++) {
                                    Operation operation2 = operations.get(j);
                                    if (operation2 instanceof MoveOperation) {
                                        CtElement movedSrcNode = operation2.getSrcNode();
                                        if (movedSrcNode.getParent() instanceof CtCase) {
                                            if (operation instanceof InsertOperation) {
                                                repairPatterns.incrementFeatureCounter("expLogicExpand");
                                                isExpLogicExOrRed = true;
                                            }
                                        }
                                    }
                                }
                            }
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
                        if (!RepairPatternUtils.isStringInvolvedInBinaryOperator(binaryOperator)) {
                            repairPatterns.incrementFeatureCounter("expArithMod");
                        }
                    }
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
