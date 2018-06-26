package fr.inria.spirals.features.detector.repairpatterns;

import fr.inria.spirals.entities.RepairPatterns;
import fr.inria.spirals.main.Config;
import gumtree.spoon.diff.operations.DeleteOperation;
import gumtree.spoon.diff.operations.InsertOperation;
import gumtree.spoon.diff.operations.Operation;
import gumtree.spoon.diff.operations.UpdateOperation;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.path.CtRole;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tdurieux
 */
public class WrongReferenceDetector extends AbstractPatternDetector {


    private Config config;

    public WrongReferenceDetector(Config config, List<Operation> operations) {
        super(operations);
        this.config = config;
    }

    @Override
    public void detect(RepairPatterns repairPatterns) {
        for (int i = 0; i < operations.size(); i++) {
            Operation operation = operations.get(i);

            if (operation instanceof DeleteOperation) {
                CtElement srcNode = operation.getSrcNode();
                if (srcNode instanceof CtVariableAccess || srcNode instanceof CtTypeAccess) {
                    if (srcNode.getMetadata("delete") != null) {
                        CtElement statementParent = srcNode.getParent(CtStatement.class);
                        if (statementParent.getMetadata("delete") == null) {
                            // skip when it's a wrap with method call
                            boolean wasVariableWrapped = false;
                            for (int j = 0; j < operations.size(); j++) {
                                Operation operation2 = operations.get(j);
                                if (operation2 instanceof InsertOperation) {
                                    CtElement node2 = operation2.getSrcNode();
                                    if (node2 instanceof CtInvocation || node2 instanceof CtConstructorCall) {
                                        if (((InsertOperation) operation2).getParent() != null) {
                                            if (srcNode.getParent() == ((InsertOperation) operation2).getParent()) {
                                                List<CtExpression> invocationArguments = new ArrayList<>();
                                                if (node2 instanceof CtInvocation) {
                                                    invocationArguments = ((CtInvocation) node2).getArguments();
                                                }
                                                if (node2 instanceof CtConstructorCall) {
                                                    invocationArguments = ((CtConstructorCall) node2).getArguments();
                                                }
                                                for (CtExpression ctExpression : invocationArguments) {
                                                    if (srcNode instanceof CtVariableAccess && ctExpression instanceof CtVariableAccess) {
                                                        CtVariableAccess srcVariableAccess = (CtVariableAccess) srcNode;
                                                        CtVariableAccess dstVariableAccess = (CtVariableAccess) ctExpression;
                                                        if (srcVariableAccess.getVariable().getSimpleName().equals(dstVariableAccess.getVariable().getSimpleName())) {
                                                            wasVariableWrapped = true;
                                                        }
                                                    } else {
                                                        if (srcNode instanceof CtTypeAccess && ctExpression instanceof CtTypeAccess) {
                                                            CtTypeAccess srcTypeAccess = (CtTypeAccess) srcNode;
                                                            CtTypeAccess dstTypeAccess = (CtTypeAccess) ctExpression;
                                                            if (srcTypeAccess.getAccessedType().getSimpleName().equals(dstTypeAccess.getAccessedType().getSimpleName())) {
                                                                wasVariableWrapped = true;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!wasVariableWrapped) {
                                repairPatterns.incrementFeatureCounter("wrongVarRef");
                            }
                        }
                    }
                } else {
                    if (srcNode.getRoleInParent() == CtRole.ARGUMENT) {
                        repairPatterns.incrementFeatureCounter("wrongMethodRef");
                    }
                }
            }

            if (operation instanceof UpdateOperation) {
                CtElement srcNode = operation.getSrcNode();
                CtElement dstNode = operation.getDstNode();
                if (dstNode.getParent().getMetadata("new") != null ||
                        dstNode.getParent().getMetadata("isMoved") != null) {
                    continue;
                }
                if (srcNode.getParent().getMetadata("new") != null ||
                        srcNode.getParent().getMetadata("isMoved") != null) {
                    continue;
                }
                if (srcNode instanceof CtVariableAccess || srcNode instanceof CtTypeAccess) {
                    if (operation.getDstNode() instanceof CtVariableAccess
                            || operation.getDstNode() instanceof CtTypeAccess
                            || operation.getDstNode() instanceof CtInvocation) {
                        repairPatterns.incrementFeatureCounter("wrongVarRef");
                    }
                }
                if (!(srcNode instanceof CtInvocation) && !(srcNode instanceof CtConstructorCall)) {
                    continue;
                }

                if (dstNode instanceof CtInvocation || dstNode instanceof CtConstructorCall) {
                    String srcMethodName;
                    int srcArguments;
                    if (srcNode instanceof CtInvocation) {
                        srcMethodName = ((CtInvocation) srcNode).getExecutable().getSimpleName();
                        srcArguments = ((CtInvocation) srcNode).getArguments().size();
                    } else {
                        srcMethodName = ((CtConstructorCall) srcNode).getExecutable().getSimpleName();
                        srcArguments = ((CtConstructorCall) srcNode).getArguments().size();
                    }
                    String dstMethodName;
                    int dstArguments;
                    if (dstNode instanceof CtInvocation) {
                        dstMethodName = ((CtInvocation) dstNode).getExecutable().getSimpleName();
                        dstArguments = ((CtInvocation) dstNode).getArguments().size();
                    } else {
                        dstMethodName = ((CtConstructorCall) dstNode).getExecutable().getSimpleName();
                        dstArguments = ((CtConstructorCall) dstNode).getArguments().size();
                    }

                    if (!srcMethodName.equals(dstMethodName)) {
                        repairPatterns.incrementFeatureCounter("wrongMethodRef");
                    } else {
                        if (srcArguments != dstArguments) {
                            repairPatterns.incrementFeatureCounter("wrongMethodRef");
                        }
                    }
                }
            }
        }
    }

}
