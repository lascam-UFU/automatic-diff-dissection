package br.ufu.lascam.features.detector.repairpatterns;

import br.ufu.lascam.entities.RepairPatterns;
import gumtree.spoon.diff.operations.Operation;

import java.util.List;

/**
 * Created by fermadeiral
 */
public abstract class AbstractPatternDetector {

    protected List<Operation> operations;

    AbstractPatternDetector(List<Operation> operations) {
        this.operations = operations;
    }

    public abstract void detect(RepairPatterns repairPatterns);

}
