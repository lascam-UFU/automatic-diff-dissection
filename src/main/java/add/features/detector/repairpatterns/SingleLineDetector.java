package add.features.detector.repairpatterns;

import add.entities.Metrics;
import add.entities.RepairPatterns;
import add.features.detector.spoon.RepairPatternUtils;
import add.features.extractor.MetricExtractor;
import add.main.Config;
import gumtree.spoon.diff.operations.DeleteOperation;
import gumtree.spoon.diff.operations.InsertOperation;
import gumtree.spoon.diff.operations.MoveOperation;
import gumtree.spoon.diff.operations.Operation;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.visitor.filter.LineFilter;

import java.util.List;

/**
 * Created by fermadeiral
 */
public class SingleLineDetector extends AbstractPatternDetector {

    private Config config;

    public SingleLineDetector(Config config, List<Operation> operations) {
        super(operations);
        this.config = config;
    }

    @Override
    public void detect(RepairPatterns repairPatterns) {
        boolean wasPatternFound = false;

        MetricExtractor extractor = new MetricExtractor(this.config);
        Metrics metrics = extractor.analyze();
        if (metrics.getFeatureCounter("patchSizeCodeOnly") == 1) {
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
                    if (statements.size() == 1 || metrics.getFeatureCounter("patchSizeCodeOnly") == 2) {
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
