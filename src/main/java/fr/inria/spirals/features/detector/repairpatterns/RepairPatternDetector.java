package fr.inria.spirals.features.detector.repairpatterns;

import fr.inria.spirals.entities.RepairPatterns;
import fr.inria.spirals.features.detector.EditScriptBasedDetector;
import fr.inria.spirals.main.Config;
import gumtree.spoon.diff.operations.Operation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fermadeiral
 */
public class RepairPatternDetector extends EditScriptBasedDetector {

    private RepairPatterns repairPatterns;

    public RepairPatternDetector(Config config) {
        super(config);
        this.repairPatterns = new RepairPatterns();
    }

    @Override
    public RepairPatterns analyze() {
        List<Operation> operations = this.editScript.getRootOperations();

        List<AbstractPatternDetector> detectors = new ArrayList<>();
        detectors.add(new MissingNullCheckPatternDetector(operations));
        detectors.add(new SingleLinePatternDetector(this.config, operations));
        detectors.add(new ConditionalBlockPatternDetector(operations));
        detectors.add(new CopyPastePatternDetector(operations));
        detectors.add(new ConstantChangePatternDetector(operations));
        detectors.add(new CodeMovingPatternDetector(operations));

        for (AbstractPatternDetector detector : detectors) {
            detector.detect(this.repairPatterns);
        }

        return this.repairPatterns;
    }

}
