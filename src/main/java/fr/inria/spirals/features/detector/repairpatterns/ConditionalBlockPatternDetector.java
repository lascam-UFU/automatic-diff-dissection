package fr.inria.spirals.features.detector.repairpatterns;

import fr.inria.spirals.entities.RepairPatterns;
import fr.inria.spirals.features.detector.spoon.RepairPatternUtils;
import fr.inria.spirals.features.detector.spoon.SpoonHelper;
import gumtree.spoon.diff.operations.DeleteOperation;
import gumtree.spoon.diff.operations.InsertOperation;
import gumtree.spoon.diff.operations.Operation;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.visitor.filter.TypeFilter;

import java.util.List;

/**
 * Created by fermadeiral
 */
public class ConditionalBlockPatternDetector extends AbstractPatternDetector {

    public ConditionalBlockPatternDetector(List<Operation> operations) {
        super(operations);
    }

    @Override
    public void detect(RepairPatterns repairPatterns) {
        for (Operation operation : this.operations) {
            if (operation instanceof InsertOperation || operation instanceof DeleteOperation) {
                CtElement ctElement = operation.getSrcNode();
                SpoonHelper.printInsertOrDeleteOperation(ctElement.getFactory().getEnvironment(), ctElement, operation);

                List<CtIf> ifList = ctElement.getElements(new TypeFilter<>(CtIf.class));
                for (CtIf ctIf : ifList) {
                    if (ctIf.getMetadata("new") != null) {
                        CtBlock thenBlock = ctIf.getThenStatement();
                        CtBlock elseBlock = ctIf.getElseStatement();
                        if (thenBlock != null && !RepairPatternUtils.isThereOldStatementInStatementList(thenBlock.getStatements()) &&
                                (elseBlock == null || (elseBlock != null && !RepairPatternUtils.isThereOldStatementInStatementList(elseBlock.getStatements())))) {
                            String pattern = this.getVariant(ctIf, operation);
                            if (!pattern.isEmpty()) {
                                repairPatterns.incrementFeatureCounter(pattern);
                            }
                        }
                    }
                }

                CtCase ctCase = ctElement.getParent(CtCase.class);
                if (ctCase != null) {
                    if (ctElement.getMetadata("new") != null) {
                        List<CtStatement> statements = ctCase.getStatements();
                        if (statements.size() > 0 && !RepairPatternUtils.isThereOldStatementInStatementList(statements)) {
                            String pattern = this.getVariant(ctCase, operation);
                            if (!pattern.isEmpty()) {
                                repairPatterns.incrementFeatureCounter(pattern);
                            }
                        }
                    }
                }
            }
        }
    }

    private String getVariant(CtElement ctElement, Operation operation) {
        if (operation instanceof InsertOperation) {
            boolean isThereReturn = RepairPatternUtils.isThereReturnInIfOrCase(ctElement);
            if (isThereReturn) {
                return "condBlockRetAdd";
            }
            boolean isThereThrow = RepairPatternUtils.isThereThrowInIfOrCase(ctElement);
            if (isThereThrow) {
                return "condBlockExcAdd";
            }
            if (!isThereReturn && !isThereThrow) {
                return "condBlockOthersAdd";
            }
        } else {
            return "condBlockRem";
        }
        return "";
    }

}
