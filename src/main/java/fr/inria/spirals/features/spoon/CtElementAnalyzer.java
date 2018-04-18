package fr.inria.spirals.features.spoon;

import fr.inria.spirals.entities.RepairActions;
import spoon.reflect.code.*;
import spoon.reflect.declaration.*;
import spoon.reflect.path.CtRole;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.CtInheritanceScanner;
import spoon.reflect.visitor.CtScanner;

/**
 * Created by tdurieux
 */
public class CtElementAnalyzer {

    public enum ACTION_TYPE {
        ADD("Add"),
        UPDATE("Change"),
        DELETE("Rem");


        private String name;

        ACTION_TYPE(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private CtElement element;

    public CtElementAnalyzer(CtElement element) {
        this.element = element;
    }

    public RepairActions analyze(final RepairActions output, final ACTION_TYPE actionType) {
        element.accept(new CtScanner() {
            @Override
            public void scan(CtElement element) {
                if (element != null && element.getMetadata("isMoved") == null) {
                    super.scan(element);
                }
            }

            @Override
            protected void enter(CtElement e) {
                e.accept(new CtInheritanceScanner() {
                    @Override
                    public <T, A extends T> void visitCtAssignment(CtAssignment<T, A> e) {
                        output.incrementMetric("assign" + actionType.name);
                        super.visitCtAssignment(e);
                    }

                    @Override
                    public void visitCtIf(CtIf e) {
                        if (actionType == ACTION_TYPE.DELETE) {
                            output.incrementMetric("condBran" + actionType.name);
                        } else if (e.getElseStatement() != null
                                && e.getElseStatement().getMetadata("isMoved") == null) {
                            output.incrementMetric("condBranIfElse" + actionType.name);
                        } else {
                            output.incrementMetric("condBranIf" + actionType.name);
                        }
                        super.visitCtIf(e);
                    }

                    @Override
                    public <T> void visitCtConditional(CtConditional<T> e) {
                        output.incrementMetric("condBranIfElse" + actionType.name);
                        super.visitCtConditional(e);
                    }

                    @Override
                    public <E> void visitCtCase(CtCase<E> e) {
                        output.incrementMetric("condBranCase" + actionType.name);
                        super.visitCtCase(e);
                    }

                    @Override
                    public <T> void visitCtBinaryOperator(CtBinaryOperator<T> expression) {
                        if (actionType == ACTION_TYPE.DELETE) {
                            CtIf ctIf = expression.getParent(CtIf.class);
                            if (ctIf != null && ctIf.getMetadata("new") == null && expression.hasParent(ctIf.getCondition())) {
                                output.incrementMetric("condExpRed");
                            }
                        } else if (actionType == ACTION_TYPE.ADD) {
                            CtIf ctIf = expression.getParent(CtIf.class);
                            if (ctIf != null && ctIf.getMetadata("new") == null) {
                                if (expression.hasParent(ctIf.getCondition())) {
                                    output.incrementMetric("condExpExpand");
                                }
                            }
                        }
                        super.visitCtBinaryOperator(expression);
                    }

                    @Override
                    public void scanCtLoop(CtLoop loop) {
                        output.incrementMetric("loop" + actionType.name);
                        super.scanCtLoop(loop);
                    }

                    @Override
                    public <T> void visitCtInvocation(CtInvocation<T> e) {
                        output.incrementMetric("mc" + actionType.name);
                        super.visitCtInvocation(e);
                    }

                    @Override
                    public <R> void visitCtReturn(CtReturn<R> e) {
                        if (actionType == ACTION_TYPE.ADD) {
                            output.incrementMetric("retBranch" + actionType.name);
                        } else if (actionType == ACTION_TYPE.DELETE) {
                            output.incrementMetric("ret" + actionType.name);
                        }
                        super.visitCtReturn(e);
                    }

                    @Override
                    public void visitCtThrow(CtThrow e) {
                        output.incrementMetric("exThrows" + actionType.name);
                        super.visitCtThrow(e);
                    }

                    @Override
                    public <T> void visitCtMethod(CtMethod<T> e) {
                        if (e.hasAnnotation(Override.class)) {
                            output.incrementMetric("mdOverride");
                        } else {
                            output.incrementMetric("md" + actionType.name);
                        }

                        super.visitCtMethod(e);
                    }

                    @Override
                    public <T> void visitCtParameter(CtParameter<T> e) {
                        output.incrementMetric("mdPar" + actionType.name);
                        super.visitCtParameter(e);
                    }

                    @Override
                    public <T> void visitCtTypeReference(CtTypeReference<T> e) {
                        if (e.getRoleInParent() == CtRole.TYPE && e.getMetadata("new") == null) {
                            if (e.getParent() instanceof CtMethod && actionType == ACTION_TYPE.UPDATE) {
                                output.incrementMetric("mdRetTyChange");
                            }
                            if (e.getParent() instanceof CtVariable) {
                                output.incrementMetric("varTyChange");
                            }
                        } else if (e.getRoleInParent() == CtRole.INTERFACE) {
                            output.incrementMetric("tyImpInterf");
                        }
                        super.visitCtTypeReference(e);
                    }

                    @Override
                    public void visitCtTry(CtTry e) {
                        output.incrementMetric("exTryCatch" + actionType.name);
                        super.visitCtTry(e);
                    }

                    @Override
                    public <T> void visitCtLocalVariable(CtLocalVariable<T> e) {
                        output.incrementMetric("var" + actionType.name);
                        if (e.getDefaultExpression() != null) {
                            output.incrementMetric("assign" + actionType.name);
                        }
                        super.visitCtLocalVariable(e);
                    }

                    @Override
                    public <T> void scanCtType(CtType<T> type) {
                        output.incrementMetric("ty");
                        super.scanCtType(type);
                    }

                    @Override
                    public <T> void visitCtConstructorCall(CtConstructorCall<T> e) {
                        output.incrementMetric("objInst" + actionType.name);
                        super.visitCtConstructorCall(e);
                    }

                    @Override
                    public <T> void scanCtExpression(CtExpression<T> expression) {
                        if (expression.getRoleInParent() == CtRole.ARGUMENT && expression.getParent().getMetadata("new") == null) {
                            if (actionType == ACTION_TYPE.UPDATE) {
                                output.incrementMetric("mcParVal" + actionType.name);
                            } else {
                                output.incrementMetric("mcPar" + actionType.name);
                            }
                        }
                        if (actionType == ACTION_TYPE.UPDATE) {
                            CtAssignment assignment = expression.getParent(CtAssignment.class);
                            if (assignment != null && assignment.getMetadata("isMoved") != null && expression.hasParent(assignment.getAssignment())) {
                                output.incrementMetric("assignExp" + actionType.name);
                            }
                            CtIf ctIf = expression.getParent(CtIf.class);
                            if (ctIf != null && expression.hasParent(ctIf.getCondition())) {
                                output.incrementMetric("condExpMod");
                            }

                            CtFor ctFor = expression.getParent(CtFor.class);
                            if (ctFor != null && ctIf.getMetadata("new") == null) {
                                if (expression.hasParent(ctFor.getForInit().get(0))) {
                                    output.incrementMetric("loopInitChange");
                                } else if (expression.hasParent(ctFor.getExpression())) {
                                    output.incrementMetric("loopCondChange");
                                }
                            }
                        }
                        super.scanCtExpression(expression);
                    }
                });
            }

        });
        return output;
    }

}
