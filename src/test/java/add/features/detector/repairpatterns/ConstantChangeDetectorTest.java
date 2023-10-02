package add.features.detector.repairpatterns;

import add.entities.RepairPatterns;
import add.main.Config;
import add.utils.TestUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by tdurieux
 *
 * Tests for the feature constChange
 */
public class ConstantChangeDetectorTest {

    @Test
    public void closure14() {
        Config config = TestUtils.setupConfig("closure_14");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("constChange") > 0);
    }

    @Test
    @Ignore
    public void closure40() {
        Config config = TestUtils.setupConfig("closure_40");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("constChange") > 0);
    }

    @Test
    public void math15() {
        Config config = TestUtils.setupConfig("math_15");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("constChange") == 0);
    }

    @Test
    public void math60() {
        Config config = TestUtils.setupConfig("math_60");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("constChange") == 0);
    }

    @Test
    @Ignore
    public void time8() {
        Config config = TestUtils.setupConfig("time_8");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("constChange") > 0);
    }

    @Test
    public void time10() {
        Config config = TestUtils.setupConfig("time_10");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("constChange") > 0);
    }
}
