package fr.inria.spirals.features.detector.repairpatterns;

import fr.inria.spirals.entities.RepairPatterns;
import fr.inria.spirals.features.detector.spoon.CtElementAnalyzer;
import fr.inria.spirals.features.detector.spoon.RepairPatternUtils;
import fr.inria.spirals.features.detector.spoon.filter.NullCheckFilter;
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
public class MissingNullCheckPatternDetector {
    private static Logger LOGGER = LoggerFactory.getLogger(MissingNullCheckPatternDetector.class);

    private CtElement element;
    private CtElementAnalyzer.ACTION_TYPE actionType;

    public MissingNullCheckPatternDetector(CtElement element, CtElementAnalyzer.ACTION_TYPE actionType) {
        this.element = element;
        this.actionType = actionType;
    }

    public void process(RepairPatterns repairPatterns) {
        if (actionType == CtElementAnalyzer.ACTION_TYPE.ADD) {
            LOGGER.debug("Element: " + this.element.toString());
            List<CtBinaryOperator> binaryOperatorList = this.element.getElements(new NullCheckFilter());
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
                        if (variable == null ||
                                (variable != null && !RepairPatternUtils.isNewVariable(variable))) {
                            wasPatternFound = true;
                        } else {
                            CtElement parent = binaryOperator.getParent(new LineFilter());
                            if (parent instanceof CtIf) {
                                CtBlock block = ((CtIf) parent).getThenStatement();
                                if (block != null) {
                                    for (CtStatement statement : block.getStatements()) {
                                        if (statement.getMetadata("new") == null) {
                                            wasPatternFound = true;
                                            break;
                                        }
                                    }
                                }
                                if (!wasPatternFound) {
                                    block = ((CtIf) parent).getElseStatement();
                                    if (block != null) {
                                        for (CtStatement statement : block.getStatements()) {
                                            if (statement.getMetadata("new") == null) {
                                                wasPatternFound = true;
                                                break;
                                            }
                                        }
                                    }
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
