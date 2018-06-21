package fr.inria.spirals.features.detector.repairpatterns;

import fr.inria.spirals.entities.RepairPatterns;
import gumtree.spoon.diff.operations.MoveOperation;
import gumtree.spoon.diff.operations.Operation;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtElement;

import java.util.List;

/**
 * Created by tdurieux
 */
public class CodeMovingPatternDetector extends AbstractPatternDetector {

    public CodeMovingPatternDetector(List<Operation> operations) {
        super(operations);
    }

    @Override
    public void detect(RepairPatterns repairPatterns) {
        for (int i = 0; i < operations.size(); i++) {
            Operation operation = operations.get(i);
            if (!(operation instanceof MoveOperation)) {
                continue;
            }
            CtElement dstNode = operation.getDstNode();
            CtElement dstParent = dstNode.getParent();
            if (dstParent instanceof CtBlock) {
                dstParent = dstParent.getParent();
            }
            CtElement srcNode = operation.getSrcNode();
            CtElement srcParent = srcNode.getParent();
            if (srcParent instanceof CtBlock) {
                srcParent = srcParent.getParent();
            }
            if (!(dstNode instanceof CtStatement)) {
                continue;
            }
            if (!(srcNode instanceof CtStatement)) {
                continue;
            }
            if (dstParent.getMetadata("new") == null &&
                    srcParent.getMetadata("new") == null
                    && dstNode.getPosition().getSourceStart() != srcNode.getPosition().getSourceStart()) {
                repairPatterns.incrementFeatureCounter("codeMove");
            }
        }
    }

}
