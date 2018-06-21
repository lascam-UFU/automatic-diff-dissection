package fr.inria.spirals.features.detector.repairpatterns;

import fr.inria.spirals.entities.RepairPatterns;
import fr.inria.spirals.features.detector.spoon.RepairPatternUtils;
import fr.inria.spirals.features.detector.spoon.SpoonHelper;
import fr.inria.spirals.features.detector.spoon.filter.NullCheckFilter;
import gumtree.spoon.diff.operations.InsertOperation;
import gumtree.spoon.diff.operations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.visitor.filter.LineFilter;

import java.util.List;

/**
 * Created by fermadeiral
 */
public class MissingNullCheckDetector extends AbstractPatternDetector {
    private static Logger LOGGER = LoggerFactory.getLogger(MissingNullCheckDetector.class);

    public MissingNullCheckDetector(List<Operation> operations) {
        super(operations);
    }

    @Override
    public void detect(RepairPatterns repairPatterns) {
        for (Operation operation : this.operations) {
            if (operation instanceof InsertOperation) {
                CtElement srcNode = operation.getSrcNode();
                SpoonHelper.printInsertOrDeleteOperation(srcNode.getFactory().getEnvironment(), srcNode, operation);

                List<CtBinaryOperator> binaryOperatorList = srcNode.getElements(new NullCheckFilter());
                for (CtBinaryOperator binaryOperator : binaryOperatorList) {
                    if (RepairPatternUtils.isNewBinaryOperator(binaryOperator)) {
                        if (RepairPatternUtils.isNewConditionInBinaryOperator(binaryOperator)) {
                            LOGGER.debug("-New null check: " + binaryOperator.toString());

                            final CtElement referenceExpression;
                            if (binaryOperator.getRightHandOperand().toString().equals("null")) {
                                referenceExpression = binaryOperator.getLeftHandOperand();
                            } else {
                                referenceExpression = binaryOperator.getRightHandOperand();
                            }
                            LOGGER.debug("-Reference expression: " + referenceExpression.toString());

                            boolean wasPatternFound = false;

                            CtVariable variable = RepairPatternUtils.getVariableFromReferenceExpression(referenceExpression);
                            if (variable == null || (variable != null && !RepairPatternUtils.isNewVariable(variable))) {
                                wasPatternFound = true;
                            } else {
                                CtElement parent = binaryOperator.getParent(new LineFilter());
                                if (parent instanceof CtIf) {
                                    CtBlock thenBlock = ((CtIf) parent).getThenStatement();
                                    CtBlock elseBlock = ((CtIf) parent).getElseStatement();
                                    if ((thenBlock != null && RepairPatternUtils.isThereOldStatementInStatementList(thenBlock.getStatements())) ||
                                            (elseBlock != null && RepairPatternUtils.isThereOldStatementInStatementList(elseBlock.getStatements()))) {
                                        wasPatternFound = true;
                                    }
                                }
                            }
                            if (wasPatternFound) {
                                if (binaryOperator.getKind().equals(BinaryOperatorKind.EQ)) {
                                    repairPatterns.incrementFeatureCounter("missNullCheckP");
                                } else {
                                    repairPatterns.incrementFeatureCounter("missNullCheckN");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
