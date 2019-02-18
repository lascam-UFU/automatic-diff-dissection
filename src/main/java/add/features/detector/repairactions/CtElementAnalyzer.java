package add.features.detector.repairactions;

import add.entities.RepairActions;
import spoon.reflect.code.*;
import spoon.reflect.declaration.*;
import spoon.reflect.path.CtRole;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.CtInheritanceScanner;
import spoon.reflect.visitor.CtScanner;
import spoon.support.reflect.code.CtConstructorCallImpl;

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
    private CtElement dstElement;

    public CtElementAnalyzer(CtElement e, CtElement dst) {
        this.element = e;
        this.dstElement = dst;
    }

    public CtElementAnalyzer(CtElement element) {
        this(element, null);
    }

    public RepairActions analyze(final RepairActions output, final ACTION_TYPE actionType) {
        if (actionType == ACTION_TYPE.UPDATE) {
            element.accept(new CtInheritanceScanner() {
                @Override
                public <T> void visitCtBinaryOperator(CtBinaryOperator<T> e) {
                    CtIf ctIf = e.getParent(CtIf.class);
                    if (ctIf != null && (e.equals(ctIf.getCondition()) || e.hasParent(ctIf.getCondition()))) {
                        output.incrementFeatureCounter("condExpMod");
                    }
                    super.visitCtBinaryOperator(e);
                }

                @Override
                public <T> void visitCtMethod(CtMethod<T> e) {
                    if (!e.getSimpleName().equals(((CtNamedElement) dstElement).getSimpleName())) {
                        output.incrementFeatureCounter("mdRen");
                    }
                    if (e.getModifiers().size() != ((CtModifiable) dstElement).getModifiers().size() ||
                            !e.getModifiers().containsAll(((CtModifiable) dstElement).getModifiers())) {
                        output.incrementFeatureCounter("mdModChange");
                    }
                    super.visitCtMethod(e);
                }

                @Override
                public <T> void scanCtAbstractInvocation(CtAbstractInvocation<T> e) {
                    if (dstElement instanceof CtAbstractInvocation) {
                        if (e.getArguments().size() > ((CtAbstractInvocation) dstElement).getArguments().size()) {
                            output.incrementFeatureCounter("mcParRem");
                        } else if (e.getArguments().size() < ((CtAbstractInvocation) dstElement).getArguments().size()) {
                            output.incrementFeatureCounter("mcParAdd");
                        }
                    }
                    super.scanCtAbstractInvocation(e);
                }

                @Override
                public <T> void visitCtConditional(CtConditional<T> e) {
                    if (!(dstElement instanceof CtConditional)) {
                        output.incrementFeatureCounter("condBranRem");
                    }
                    super.visitCtConditional(e);
                }

                @Override
                public <T> void scanCtVariable(CtVariable<T> e) {
                    if (e.getModifiers().size() != ((CtModifiable) dstElement).getModifiers().size() ||
                            !e.getModifiers().containsAll(((CtModifiable) dstElement).getModifiers())) {
                        output.incrementFeatureCounter("varModChange");
                    }
                    super.scanCtVariable(e);
                }

                @Override
                public <T> void scanCtType(CtType<T> type) {
                    if (!type.getSimpleName().equals(((CtNamedElement) dstElement).getSimpleName())) {
                        //output.incrementFeatureCounter("tyRen");
                    }
                    super.scanCtType(type);
                }

                @Override
                public <T> void visitCtConstructorCall(CtConstructorCall<T> e) {
                    output.incrementFeatureCounter("objInstMod");
                    super.visitCtConstructorCall(e);
                }

                @Override
                public <T> void scanCtExpression(CtExpression<T> expression) {
                    if (!(expression instanceof CtConditional) && dstElement instanceof CtConditional) {
                        output.incrementFeatureCounter("condBranIfElseAdd");
                    }
                    if (expression.getRoleInParent() == CtRole.ARGUMENT && expression.getParent().getMetadata("new") == null) {
                        output.incrementFeatureCounter("mcParVal" + actionType.name);
                    }
                    CtAssignment assignment = expression.getParent(CtAssignment.class);
                    if (assignment != null && assignment.getMetadata("isMoved") != null && expression.hasParent(assignment.getAssignment())) {
                        output.incrementFeatureCounter("assignExp" + actionType.name);
                    }
                    CtLocalVariable localVariable = expression.getParent(CtLocalVariable.class);
                    if (localVariable != null && localVariable.getMetadata("new") == null && localVariable.getMetadata("delete") == null) {
                        output.incrementFeatureCounter("assignExp" + actionType.name);
                    }

                    CtReturn ctReturn = expression.getParent(CtReturn.class);
                    if (ctReturn != null && ctReturn.getMetadata("new") == null && ctReturn.getMetadata("delete") == null) {
                        output.incrementFeatureCounter("retExpChange");
                    }

                    CtFor ctFor = expression.getParent(CtFor.class);
                    if (ctFor != null && ctFor.getMetadata("new") == null) {
                        if (ctFor.getForInit() != null && expression.hasParent(ctFor.getForInit().get(0))) {
                            output.incrementFeatureCounter("loopInitChange");
                        } else if (expression.hasParent(ctFor.getExpression())) {
                            output.incrementFeatureCounter("loopCondChange");
                        }
                    }
                    super.scanCtExpression(expression);
                }

                @Override
                public <T> void visitCtParameter(CtParameter<T> e) {
                    if (e.getParent().getMetadata("new") == null) {
                        output.incrementFeatureCounter("mdParRem");
                    }
                    super.visitCtParameter(e);
                }

                @Override
                public <T> void visitCtTypeReference(CtTypeReference<T> e) {
                    if ((e.getRoleInParent() == CtRole.TYPE || e.getRoleInParent() == CtRole.MULTI_TYPE)
                            && e.getMetadata("update") != null) {
                        if (e.getParent() instanceof CtMethod) {
                            output.incrementFeatureCounter("mdRetTyChange");
                        }
                        if (e.getParent() instanceof CtVariable) {
                            output.incrementFeatureCounter("varTyChange");
                            if (e.getParent() instanceof CtParameter) {
                                output.incrementFeatureCounter("mdParTyChange");
                            }
                        }
                    } else if (e.getRoleInParent() == CtRole.INTERFACE) {
                        output.incrementFeatureCounter("tyImpInterf");
                    }
                    super.visitCtTypeReference(e);
                }

                @Override
                public <T> void visitCtInvocation(CtInvocation<T> e) {
                    output.incrementFeatureCounter("mcRepl");
                    super.visitCtInvocation(e);
                }

                @Override
                public <T> void visitCtFieldRead(CtFieldRead<T> e) {
                    output.incrementFeatureCounter("varReplVar");
                    super.visitCtFieldRead(e);
                }

                @Override
                public <T> void visitCtVariableRead(CtVariableRead<T> e) {
                    output.incrementFeatureCounter("varReplVar");
                    super.visitCtVariableRead(e);
                }
            });
            return output;
        }
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
                    public <R> void visitCtBlock(CtBlock<R> e) {
                        if (e.getRoleInParent() == CtRole.ELSE) {
                            if (actionType == ACTION_TYPE.DELETE) {
                                output.incrementFeatureCounter("condBran" + actionType.name);
                            } else {
                                if (e.getParent().getMetadata("new") == null) {
                                    output.incrementFeatureCounter("condBranElseAdd");
                                } else {
                                    output.incrementFeatureCounter("condBranIfElse" + actionType.name);
                                }
                            }
                        }
                        super.visitCtBlock(e);
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
                        if (actionType == ACTION_TYPE.DELETE) {
                            output.incrementFeatureCounter("condBranRem");
                        } else if (actionType == ACTION_TYPE.ADD) {
                            output.incrementFeatureCounter("condBranCaseAdd");
                        }
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
                        output.incrementFeatureCounter("mc" + actionType.name);
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
                        if (e.getParent().getMetadata("new") == null) {
                            output.incrementFeatureCounter("mdPar" + actionType.name);
                        }
                        super.visitCtParameter(e);
                    }

                    @Override
                    public <T> void visitCtTypeReference(CtTypeReference<T> e) {
                        if ((e.getRoleInParent() == CtRole.TYPE || e.getRoleInParent() == CtRole.MULTI_TYPE)
                                && e.getMetadata("update") != null) {
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
                        output.incrementFeatureCounter("objInst" + actionType.name);
                        super.visitCtConstructorCall(e);
                    }

                    @Override
                    public <T> void scanCtExpression(CtExpression<T> expression) {
                        if (expression.getRoleInParent() == CtRole.ARGUMENT && expression.getParent().getMetadata("new") == null) {
                            output.incrementFeatureCounter("mcPar" + actionType.name);
                        }
                        CtInvocation ctInvocation = expression.getParent(CtInvocation.class);
                        if (ctInvocation != null && ctInvocation.getMetadata("new") == null) {
                            output.incrementFeatureCounter("mcParValChange");
                        }
                        CtConstructorCall ctConstructorCall = expression.getParent(CtConstructorCall.class);
                        if (ctConstructorCall != null && ctConstructorCall.getMetadata("new") == null) {
                            output.incrementFeatureCounter("mcParValChange");
                        }
                        super.scanCtExpression(expression);
                    }
                });
            }

        });
        return output;
    }

}
