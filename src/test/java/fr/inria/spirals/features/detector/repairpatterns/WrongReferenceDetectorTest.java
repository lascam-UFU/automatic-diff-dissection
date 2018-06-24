package fr.inria.spirals.features.detector.repairpatterns;

import fr.inria.spirals.entities.RepairPatterns;
import fr.inria.spirals.main.Config;
import fr.inria.spirals.utils.TestUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by tdurieux
 *
 * Tests for the features wrongVarRef and wrongMethodRef
 */
public class WrongReferenceDetectorTest {

    @Test
    public void closure30() {
        Config config = TestUtils.setupConfig("Closure 30");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("wrongMethodRef") > 0);
    }

    @Test
    public void closure37() {
        Config config = TestUtils.setupConfig("Closure 37");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("wrongMethodRef") > 0);
    }

    @Ignore
    @Test
    public void closure109() {
        Config config = TestUtils.setupConfig("Closure 109");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("wrongMethodRef") > 0);
    }

    @Test
    public void math64() {
        Config config = TestUtils.setupConfig("Math 64");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("wrongVarRef") > 0);
    }
}
