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
 * Tests for the feature codeMove
 */
public class CodeMovingDetectorTest {

    @Test
    public void closure13() {
        Config config = TestUtils.setupConfig("closure_13");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("codeMove") > 0);
    }

    @Test
    public void closure68() {
        Config config = TestUtils.setupConfig("closure_68");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("codeMove") > 0);
    }

    @Test
    public void closure102() {
        Config config = TestUtils.setupConfig("closure_102");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("codeMove") > 0);
    }

    @Test
    public void closure117() {
        Config config = TestUtils.setupConfig("closure_117");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("codeMove") > 0);
    }

    @Test
    public void lang53() {
        Config config = TestUtils.setupConfig("lang_53");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("codeMove") > 0);
    }

    @Test
    public void math64() {
        Config config = TestUtils.setupConfig("math_64");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("codeMove") > 0);
    }

    @Test
    @Ignore
    public void math86() {
        Config config = TestUtils.setupConfig("math_86");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("codeMove") > 0);
    }

    @Test
    public void chart17() {
        Config config = TestUtils.setupConfig("chart_17");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("codeMove") == 0);
    }

    @Test
    public void chart21() {
        Config config = TestUtils.setupConfig("chart_21");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("codeMove") == 0);
    }

    @Test
    public void time7() {
        Config config = TestUtils.setupConfig("time_7");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("codeMove") == 0);
    }

    @Test
    public void closure76() {
        Config config = TestUtils.setupConfig("closure_76");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("codeMove") == 0);
    }
}
