package fr.inria.spirals.features.detector.spoon;

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
                        output.incrementFeatureCounter("assign" + actionType.name);
                        super.visitCtAssignment(e);
                    }

                    @Override
                    public void visitCtIf(CtIf e) {
                        if (actionType == ACTION_TYPE.DELETE) {
                            output.incrementFeatureCounter("condBran" + actionType.name);
                        } else if (e.getElseStatement() != null
                                && e.getElseStatement().getMetadata("isMoved") == null) {
                            output.incrementFeatureCounter("condBranIfElse" + actionType.name);
                        } else {
                            output.incrementFeatureCounter("condBranIf" + actionType.name);
                        }
                        super.visitCtIf(e);
                    }

                    @Override
                    public <T> void visitCtConditional(CtConditional<T> e) {
                        if (actionType == ACTION_TYPE.DELETE) {
                            output.incrementFeatureCounter("condBran" + actionType.name);
                        } else {
                            output.incrementFeatureCounter("condBranIfElse" + actionType.name);
                        }
                        super.visitCtConditional(e);
                    }

                    @Override
                    public <E> void visitCtCase(CtCase<E> e) {
                        output.incrementFeatureCounter("condBranCase" + actionType.name);
                        super.visitCtCase(e);
                    }

                    @Override
                    public <T> void visitCtBinaryOperator(CtBinaryOperator<T> expression) {
                        if (actionType == ACTION_TYPE.DELETE) {
                            CtIf ctIf = expression.getParent(CtIf.class);
                            if (ctIf != null && ctIf.getMetadata("new") == null && expression.hasParent(ctIf.getCondition())) {
                                output.incrementFeatureCounter("condExpRed");
                            }
                        } else if (actionType == ACTION_TYPE.ADD) {
                            CtIf ctIf = expression.getParent(CtIf.class);
                            if (ctIf != null && ctIf.getMetadata("new") == null) {
                                if (expression.hasParent(ctIf.getCondition())) {
                                    output.incrementFeatureCounter("condExpExpand");
                                }
                            }
                        }
                        super.visitCtBinaryOperator(expression);
                    }

                    @Override
                    public void scanCtLoop(CtLoop loop) {
                        output.incrementFeatureCounter("loop" + actionType.name);
                        super.scanCtLoop(loop);
                    }

                    @Override
                    public <T> void visitCtLiteral(CtLiteral<T> e) {
                        CtVariable parent = e.getParent(CtVariable.class);
                        if (parent != null && parent.getMetadata("new") == null)  {
                            output.incrementFeatureCounter("objInstMod");
                        }
                        super.visitCtLiteral(e);
                    }

                    @Override
                    public <T> void visitCtInvocation(CtInvocation<T> e) {
                        if (actionType == ACTION_TYPE.UPDATE) {
                            output.incrementFeatureCounter("mcRepl");
                        } else {
                            output.incrementFeatureCounter("mc" + actionType.name);
                        }
                        super.visitCtInvocation(e);
                    }

                    @Override
                    public <R> void visitCtReturn(CtReturn<R> e) {
                        if (actionType == ACTION_TYPE.ADD) {
                            output.incrementFeatureCounter("retBranch" + actionType.name);
                        } else if (actionType == ACTION_TYPE.DELETE) {
                            output.incrementFeatureCounter("ret" + actionType.name);
                        }
                        super.visitCtReturn(e);
                    }

                    @Override
                    public void visitCtThrow(CtThrow e) {
                        output.incrementFeatureCounter("exThrows" + actionType.name);
                        super.visitCtThrow(e);
                    }

                    @Override
                    public <T> void visitCtMethod(CtMethod<T> e) {
                        if (e.hasAnnotation(Override.class)) {
                            output.incrementFeatureCounter("mdOverride");
                        } else {
                            output.incrementFeatureCounter("md" + actionType.name);
                        }

                        super.visitCtMethod(e);
                    }

                    @Override
                    public <T> void visitCtParameter(CtParameter<T> e) {
                        if (actionType == ACTION_TYPE.UPDATE) {
                            output.incrementFeatureCounter("mdParRem");
                        } else {
                            output.incrementFeatureCounter("mdPar" + actionType.name);
                        }
                        super.visitCtParameter(e);
                    }

                    @Override
                    public <T> void visitCtTypeReference(CtTypeReference<T> e) {
                        if (e.getRoleInParent() == CtRole.TYPE && e.getMetadata("new") == null) {
                            if (e.getParent() instanceof CtMethod && actionType == ACTION_TYPE.UPDATE) {
                                output.incrementFeatureCounter("mdRetTyChange");
                            }
                            if (e.getParent() instanceof CtVariable) {
                                output.incrementFeatureCounter("varTyChange");
                            }
                        } else if (e.getRoleInParent() == CtRole.INTERFACE) {
                            output.incrementFeatureCounter("tyImpInterf");
                        }
                        super.visitCtTypeReference(e);
                    }

                    @Override
                    public void visitCtTry(CtTry e) {
                        output.incrementFeatureCounter("exTryCatch" + actionType.name);
                        super.visitCtTry(e);
                    }

                    @Override
                    public <T> void visitCtLocalVariable(CtLocalVariable<T> e) {
                        output.incrementFeatureCounter("var" + actionType.name);
                        if (e.getDefaultExpression() != null) {
                            output.incrementFeatureCounter("assign" + actionType.name);
                        }
                        super.visitCtLocalVariable(e);
                    }

                    @Override
                    public <T> void scanCtType(CtType<T> type) {
                        if (actionType == ACTION_TYPE.ADD) {
                            output.incrementFeatureCounter("tyAdd");
                        }
                        super.scanCtType(type);
                    }

                    @Override
                    public <T> void visitCtConstructorCall(CtConstructorCall<T> e) {
                        if (actionType == ACTION_TYPE.UPDATE) {
                            output.incrementFeatureCounter("objInstMod" + actionType.name);
                        } else {
                            output.incrementFeatureCounter("objInst" + actionType.name);
                        }
                        super.visitCtConstructorCall(e);
                    }

                    @Override
                    public <T> void scanCtExpression(CtExpression<T> expression) {
                        if (expression.getRoleInParent() == CtRole.ARGUMENT && expression.getParent().getMetadata("new") == null) {
                            if (actionType == ACTION_TYPE.UPDATE) {
                                output.incrementFeatureCounter("mcParVal" + actionType.name);
                            } else {
                                output.incrementFeatureCounter("mcPar" + actionType.name);
                            }
                        }
                        if (actionType == ACTION_TYPE.UPDATE) {
                            CtAssignment assignment = expression.getParent(CtAssignment.class);
                            if (assignment != null && assignment.getMetadata("isMoved") != null && expression.hasParent(assignment.getAssignment())) {
                                output.incrementFeatureCounter("assignExp" + actionType.name);
                            }
                            CtIf ctIf = expression.getParent(CtIf.class);
                            if (ctIf != null && expression.hasParent(ctIf.getCondition())) {
                                output.incrementFeatureCounter("condExpMod");
                            }

                            CtFor ctFor = expression.getParent(CtFor.class);
                            if (ctFor != null && ctIf.getMetadata("new") == null) {
                                if (ctFor.getForInit() != null && expression.hasParent(ctFor.getForInit().get(0))) {
                                    output.incrementFeatureCounter("loopInitChange");
                                } else if (expression.hasParent(ctFor.getExpression())) {
                                    output.incrementFeatureCounter("loopCondChange");
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
