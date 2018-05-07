package fr.inria.spirals.features.detector.repairpatterns;

import fr.inria.spirals.entities.Metrics;
import fr.inria.spirals.entities.RepairPatterns;
import fr.inria.spirals.features.detector.spoon.RepairPatternUtils;
import fr.inria.spirals.features.extractor.MetricExtractor;
import fr.inria.spirals.main.Config;
import gumtree.spoon.diff.operations.*;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.visitor.filter.LineFilter;

import java.util.List;

/**
 * Created by fermadeiral
 */
public class SingleLinePatternDetector extends AbstractPatternDetector {

    private Config config;

    public SingleLinePatternDetector(Config config, List<Operation> operations) {
        super(operations);
        this.config = config;
    }

    @Override
    public void detect(RepairPatterns repairPatterns) {
        boolean wasPatternFound = false;

        MetricExtractor extractor = new MetricExtractor(this.config);
        Metrics metrics = extractor.analyze();
        if (metrics.getFeatureCounter("patchSize") == 1) {
            wasPatternFound = true;
        } else {
            if (metrics.getFeatureCounter("spreadingCodeOnly") == 0) {
                List<Operation> operationsWithoutMoveOperation = RepairPatternUtils.getOperationsWithoutMoveOperation(this.operations);
                if (operationsWithoutMoveOperation.size() == 1) {
                    Operation operation = operationsWithoutMoveOperation.get(0);
                    if (operation instanceof InsertOperation || operation instanceof DeleteOperation) {
                        CtElement srcNode = operation.getSrcNode();
                        List<CtStatement> statements = srcNode.getElements(new LineFilter());
                        if (statements.size() == 0) {
                            wasPatternFound = true;
                        }
                        if (RepairPatternUtils.getNumberOfNewStatements(statements) == 0) {
                            wasPatternFound = true;
                        }
                    }
                } else {
                    if (RepairPatternUtils.areAllOperationsAtTheSamePosition(operationsWithoutMoveOperation)) {
                        int newStatements = 0;
                        for (int i = 0; i < operationsWithoutMoveOperation.size(); i++) {
                            CtElement srcNode = operationsWithoutMoveOperation.get(i).getSrcNode();
                            List<CtStatement> statements = srcNode.getElements(new LineFilter());
                            newStatements += RepairPatternUtils.getNumberOfNewStatements(statements);
                        }
                        if (newStatements == 0) {
                            wasPatternFound = true;
                        }
                    }
                }
            } else {
                if (this.operations.size() == 1 && this.operations.get(0) instanceof MoveOperation) {
                    CtElement srcNode = this.operations.get(0).getSrcNode();
                    List<CtStatement> statements = srcNode.getElements(new LineFilter());
                    if (statements.size() == 1 || metrics.getFeatureCounter("patchSize") == 2) {
                        wasPatternFound = true;
                    }
                }
            }
        }
        if (wasPatternFound) {
            repairPatterns.incrementFeatureCounter("singleLine");
        }
    }

}
