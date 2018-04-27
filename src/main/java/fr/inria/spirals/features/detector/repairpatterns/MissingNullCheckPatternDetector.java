package fr.inria.spirals.features.detector.repairpatterns;

import fr.inria.spirals.entities.RepairPatterns;
import fr.inria.spirals.features.detector.spoon.*;
import fr.inria.spirals.features.detector.spoon.filter.*;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.reference.CtVariableReference;
import spoon.reflect.visitor.filter.LineFilter;
import spoon.reflect.visitor.filter.TypeFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fermadeiral
 */
public class MissingNullCheckPatternDetector {

    private CtElement element;
    private CtElementAnalyzer.ACTION_TYPE actionType;

    public MissingNullCheckPatternDetector(CtElement element, CtElementAnalyzer.ACTION_TYPE actionType) {
        this.element = element;
        this.actionType = actionType;
    }

    public void process(RepairPatterns repairPatterns) {
        if (actionType == CtElementAnalyzer.ACTION_TYPE.ADD) {
            System.out.println("Element: " + this.element.toString());
            List<CtBinaryOperator> binaryOperatorList = this.element.getElements(new NullCheckFilter());
            for (CtBinaryOperator binaryOperator : binaryOperatorList) {
                if (binaryOperator.getMetadata("new") != null && (Boolean) binaryOperator.getMetadata("new")) {
                    System.out.println("-Null check: " + binaryOperator.toString());
                    final CtElement referenceExpression;
                    if (binaryOperator.getRightHandOperand().toString().equals("null")) {
                        referenceExpression = binaryOperator.getLeftHandOperand();
                    } else {
                        referenceExpression = binaryOperator.getRightHandOperand();
                    }
                    System.out.println("-Reference expression: " + referenceExpression.toString());

                    final CtElement parent = binaryOperator.getParent(new LineFilter());

                    CtMethod surroundingMethod = parent.getParent(CtMethod.class);
                    List<CtElement> referenceExpressionUsages = this.getReferenceExpressionUsages(referenceExpression, surroundingMethod);
                    for (CtElement e : referenceExpressionUsages) {
                        if (e.getPosition() == null) {
                            continue;
                        }
                        if (e.getMetadata("new") == null) {
                            if (e.getPosition().getSourceStart() > referenceExpression.getPosition().getSourceEnd()) {
                                System.out.println("Valid usage found in line " + e.getPosition().getSourceStart() + ": " + e.getParent().toString());
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

    private List<CtElement> getReferenceExpressionUsages(final CtElement referenceExpression, CtMethod method) {
        List<CtElement> referenceExpressionUsages = new ArrayList<>();

        if (referenceExpression instanceof CtVariableAccess) {
            final CtVariableReference variable = ((CtVariableAccess) referenceExpression).getVariable();
            referenceExpressionUsages.addAll(method.getElements(new TypeFilter<CtVariableAccess>(CtVariableAccess.class) {
                @Override
                public boolean matches(CtVariableAccess element) {
                    if (element == referenceExpression) {
                        return false;
                    }
                    return variable.equals(element.getVariable());
                }
            }));
        } else {
            if (referenceExpression instanceof CtArrayAccess) {
                referenceExpressionUsages.addAll(method.getElements(new TypeFilter<CtArrayAccess>(CtArrayAccess.class) {
                    @Override
                    public boolean matches(CtArrayAccess element) {
                        if (element == referenceExpression) {
                            return false;
                        }
                        return referenceExpression.equals(element);
                    }
                }));
            } else {
                if (referenceExpression instanceof CtInvocation) {
                    referenceExpressionUsages.addAll(method.getElements(new TypeFilter<CtInvocation>(CtInvocation.class) {
                        @Override
                        public boolean matches(CtInvocation element) {
                            if (element == referenceExpression) {
                                return false;
                            }
                            return referenceExpression.equals(element);
                        }
                    }));
                } else {
                    System.err.println(referenceExpression);
                }
            }
        }
        return referenceExpressionUsages;
    }

}
