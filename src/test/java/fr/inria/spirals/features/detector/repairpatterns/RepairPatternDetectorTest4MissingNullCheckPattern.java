package fr.inria.spirals.features.detector.repairpatterns;

import fr.inria.spirals.entities.RepairPatterns;
import fr.inria.spirals.main.Config;
import fr.inria.spirals.utils.TestUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fermadeiral
 *
 * Tests for the features missNullCheckP and missNullCheckN
 */
public class RepairPatternDetectorTest4MissingNullCheckPattern {

    @Test
    public void chart4() {
        Config config = TestUtils.setupConfig("Chart 4");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("missNullCheckN") > 0);
    }

    @Test
    public void chart18() {
        Config config = TestUtils.setupConfig("Chart 18");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("missNullCheckP") > 0);
    }

    @Test
    public void closure23() {
        Config config = TestUtils.setupConfig("Closure 23");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("missNullCheckP") == 0);
    }

    @Test
    public void mockito22() {
        Config config = TestUtils.setupConfig("Mockito 22");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("missNullCheckP") == 0);
    }

}
