package fr.inria.spirals.features.detector.repairpatterns;

import fr.inria.spirals.entities.RepairActions;
import fr.inria.spirals.entities.RepairPatterns;
import fr.inria.spirals.features.detector.repairactions.RepairActionDetector;
import fr.inria.spirals.main.Config;
import gumtree.spoon.diff.operations.Operation;
import gumtree.spoon.diff.operations.UpdateOperation;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtTypeAccess;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.declaration.CtElement;

import java.util.List;

/**
 * Created by tdurieux
 */
public class WrongReferencePatternDetector extends AbstractPatternDetector {


    private Config config;

    public WrongReferencePatternDetector(Config config, List<Operation> operations) {
        super(operations);
        this.config = config;
    }

    @Override
    public void detect(RepairPatterns repairPatterns) {
        RepairActionDetector extractor = new RepairActionDetector(this.config);
        RepairActions metrics = extractor.analyze();

        if (metrics.getFeatureCounter("mcParAdd") - metrics.getFeatureCounter("mcParRem") != 0) {
            repairPatterns.incrementFeatureCounter("wrongMethodRef");
        }

        for (int i = 0; i < operations.size(); i++) {
            Operation operation = operations.get(i);
            if (!(operation instanceof UpdateOperation)) {
                continue;
            }
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
            if (srcNode instanceof CtInvocation) {
                if (operation.getDstNode() instanceof CtInvocation) {
                    repairPatterns.incrementFeatureCounter("wrongMethodRef");
                }
            }
        }
    }

}
