package fr.inria.spirals.features.detector.repairpatterns;

import fr.inria.spirals.entities.RepairPatterns;
import fr.inria.spirals.features.detector.spoon.RepairPatternUtils;
import fr.inria.spirals.features.detector.spoon.SpoonHelper;
import gumtree.spoon.diff.operations.*;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.visitor.filter.TypeFilter;

import java.util.List;

/**
 * Created by fermadeiral
 */
public class WrapsWithDetector extends AbstractPatternDetector {

    public WrapsWithDetector(List<Operation> operations) {
        super(operations);
    }

    @Override
    public void detect(RepairPatterns repairPatterns) {
        for (Operation operation : this.operations) {
            this.detectWrapsIf(operation, repairPatterns);
            this.detectWrapsTryCatch(operation, repairPatterns);
            this.detectWrapsMethod(operation, repairPatterns);
            this.detectWrapsLoop(operation, repairPatterns);
        }
    }

    private void detectWrapsIf(Operation operation, RepairPatterns repairPatterns) {
        if (operation instanceof InsertOperation || operation instanceof DeleteOperation) {
            CtElement ctElement = operation.getSrcNode();
            SpoonHelper.printInsertOrDeleteOperation(ctElement.getFactory().getEnvironment(), ctElement, operation);

            List<CtIf> ifList = ctElement.getElements(new TypeFilter<>(CtIf.class));
            for (CtIf ctIf : ifList) {
                if (RepairPatternUtils.isNewIf(ctIf)) {
                    CtBlock thenBlock = ctIf.getThenStatement();
                    CtBlock elseBlock = ctIf.getElseStatement();
                    if (elseBlock == null) {
                        if (thenBlock != null && RepairPatternUtils.isThereOldStatementInStatementList(thenBlock.getStatements())) {
                            if (operation instanceof InsertOperation) {
                                repairPatterns.incrementFeatureCounter("wrapsIf");
                            } else {
                                repairPatterns.incrementFeatureCounter("unwrapIfElse");
                            }
                        }
                    } else {
                        if (RepairPatternUtils.isThereOldStatementInStatementList(thenBlock.getStatements()) ||
                                RepairPatternUtils.isThereOldStatementInStatementList(elseBlock.getStatements())) {
                            if (operation instanceof InsertOperation) {
                                repairPatterns.incrementFeatureCounter("wrapsIfElse");
                            } else {
                                repairPatterns.incrementFeatureCounter("unwrapIfElse");
                            }
                        }
                    }
                }
            }

            List<CtBlock> blockList = ctElement.getElements(new TypeFilter<>(CtBlock.class));
            for (CtBlock ctBlock : blockList) {
                if (ctBlock.getMetadata("new") != null) {
                    if (ctBlock.getParent() instanceof CtIf) {
                        CtIf ctIfParent = (CtIf) ctBlock.getParent();
                        CtBlock elseBlock = ctIfParent.getElseStatement();
                        if (ctBlock == elseBlock) {
                            if (!RepairPatternUtils.isNewIf(ctIfParent)) {
                                CtBlock thenBlock = ctIfParent.getThenStatement();
                                if (thenBlock != null && RepairPatternUtils.isThereOldStatementInStatementList(thenBlock.getStatements())) {
                                    if (RepairPatternUtils.isThereOldStatementInStatementList(elseBlock.getStatements())) {
                                        repairPatterns.incrementFeatureCounter("wrapsElse");
                                    }
                                }
                            }
                        }
                    }
                }
            }

            List<CtConditional> conditionalList = ctElement.getElements(new TypeFilter<>(CtConditional.class));
            for (CtConditional ctConditional : conditionalList) {
                if (ctConditional.getMetadata("new") != null) {
                    CtExpression thenExpression = ctConditional.getThenExpression();
                    CtExpression elseExpression = ctConditional.getElseExpression();
                    if (thenExpression.getMetadata("new") == null ||
                            elseExpression.getMetadata("new") == null) {
                        CtElement statementParent = ctConditional.getParent(new TypeFilter<>(CtStatement.class));
                        if (operation instanceof InsertOperation) {
                            if (statementParent.getMetadata("new") == null) {
                                repairPatterns.incrementFeatureCounter("wrapsIfElse");
                            }
                        } else {
                            if (statementParent.getMetadata("delete") == null) {
                                repairPatterns.incrementFeatureCounter("unwrapIfElse");
                            }
                        }
                    } else {
                        if (operation instanceof InsertOperation) {
                            for (int j = 0; j < operations.size(); j++) {
                                Operation operation2 = operations.get(j);
                                if (operation2 instanceof DeleteOperation) {
                                    CtElement node2 = operation2.getSrcNode();
                                    if (((InsertOperation) operation).getParent() != null) {
                                        if (node2.getParent() == ((InsertOperation) operation).getParent()) {
                                            repairPatterns.incrementFeatureCounter("wrapsIfElse");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void detectWrapsTryCatch(Operation operation, RepairPatterns repairPatterns) {
        if (operation instanceof InsertOperation || operation instanceof DeleteOperation) {
            CtElement ctElement = operation.getSrcNode();
            SpoonHelper.printInsertOrDeleteOperation(ctElement.getFactory().getEnvironment(), ctElement, operation);

            List<CtTry> tryList = ctElement.getElements(new TypeFilter<>(CtTry.class));
            for (CtTry ctTry : tryList) {
                if (ctTry.getMetadata("new") != null) {
                    List<CtCatch> catchList = ctTry.getCatchers();
                    if (RepairPatternUtils.isThereOnlyNewCatch(catchList)) {
                        CtBlock tryBodyBlock = ctTry.getBody();
                        if (tryBodyBlock != null && RepairPatternUtils.isThereOldStatementInStatementList(tryBodyBlock.getStatements())) {
                            if (operation instanceof InsertOperation) {
                                repairPatterns.incrementFeatureCounter("wrapsTryCatch");
                            } else {
                                repairPatterns.incrementFeatureCounter("unwrapTryCatch");
                            }
                        } else { // try to find a move into the body of the try
                            for (Operation operationAux : this.operations) {
                                if (operationAux instanceof MoveOperation) {
                                    CtElement ctElementDst = operationAux.getDstNode();
                                    CtTry ctTryParent = ctElementDst.getParent(new TypeFilter<>(CtTry.class));
                                    if (ctTryParent != null && ctTryParent == ctTry) {
                                        if (operation instanceof InsertOperation) {
                                            repairPatterns.incrementFeatureCounter("wrapsTryCatch");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void detectWrapsMethod(Operation operation, RepairPatterns repairPatterns) {
        if (operation.getSrcNode() instanceof CtInvocation) {
            if (operation instanceof InsertOperation) {
                CtInvocation ctInvocation = (CtInvocation) operation.getSrcNode();
                List<CtExpression> invocationArguments = ctInvocation.getArguments();

                for (Operation operation2 : this.operations) {
                    if (operation2 instanceof DeleteOperation) {
                        CtElement ctElement = operation2.getSrcNode();

                        if (ctElement instanceof CtVariableRead) {
                            if (invocationArguments.contains(ctElement)) {
                                repairPatterns.incrementFeatureCounter("wrapsMethod");
                            }
                        }
                        if (ctElement instanceof CtAssignment) {
                            if (invocationArguments.contains(((CtAssignment) ctElement).getAssignment())) {
                                repairPatterns.incrementFeatureCounter("wrapsMethod");
                            }
                        }
                    }
                }

                for (CtExpression ctExpression : invocationArguments) {
                    if (ctExpression.getMetadata("isMoved") != null) {
                        repairPatterns.incrementFeatureCounter("wrapsMethod");
                    }
                }
            } else {
                if (operation instanceof DeleteOperation) {
                    CtInvocation ctInvocation = (CtInvocation) operation.getSrcNode();
                    CtStatement statementParent = ctInvocation.getParent(new TypeFilter<>(CtStatement.class));

                    if (statementParent.getMetadata("delete") == null) {
                        List<CtExpression> invocationArguments = ctInvocation.getArguments();

                        for (CtExpression ctExpression : invocationArguments) {
                            if (ctExpression.getMetadata("isMoved") != null && ctExpression.getMetadata("movingSrc") != null) {
                                repairPatterns.incrementFeatureCounter("unwrapMethod");
                            }
                        }
                    }
                }
            }
        }
    }

    private void detectWrapsLoop(Operation operation, RepairPatterns repairPatterns) {
        if (operation instanceof InsertOperation) {
            CtElement ctElement = operation.getSrcNode();
            SpoonHelper.printInsertOrDeleteOperation(ctElement.getFactory().getEnvironment(), ctElement, operation);

            List<CtLoop> loopList = ctElement.getElements(new TypeFilter<>(CtLoop.class));
            for (CtLoop ctLoop : loopList) {
                if ((ctLoop instanceof CtFor && RepairPatternUtils.isNewFor((CtFor) ctLoop)) ||
                        (ctLoop instanceof CtForEach && RepairPatternUtils.isNewForEach((CtForEach) ctLoop)) ||
                        (ctLoop instanceof CtWhile && RepairPatternUtils.isNewWhile((CtWhile) ctLoop))) {
                    if (ctLoop.getBody() instanceof CtBlock) {
                        CtBlock bodyBlock = (CtBlock) ctLoop.getBody();
                        if (bodyBlock != null && RepairPatternUtils.isThereOldStatementInStatementList(bodyBlock.getStatements())) {
                            repairPatterns.incrementFeatureCounter("wrapsLoop");
                        } else { // try to find an update inside the body of the loop
                            for (Operation operationAux : this.operations) {
                                if (operationAux instanceof UpdateOperation) {
                                    CtElement ctElementDst = operationAux.getDstNode();
                                    CtLoop ctLoopParent = ctElementDst.getParent(new TypeFilter<>(CtLoop.class));
                                    if (ctLoopParent != null && ctLoopParent == ctLoop) {
                                        repairPatterns.incrementFeatureCounter("wrapsLoop");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
