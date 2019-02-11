package add.features.detector.repairpatterns;

import add.entities.RepairPatterns;
import add.main.Config;
import add.utils.TestUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fermadeiral
 *
 * Tests for the feature singleLine
 */
public class SingleLineDetectorTest {

    /**
     * Single line addition
     */
    @Test
    public void lang38() {
        Config config = TestUtils.setupConfig("lang_38");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("singleLine") > 0);
    }

    /**
     * Single line removal
     */
    @Test
    public void closure31() {
        Config config = TestUtils.setupConfig("closure_31");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("singleLine") > 0);
    }

    /**
     * Single line modification
     */
    @Test
    public void chart1() {
        Config config = TestUtils.setupConfig("chart_1");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("singleLine") > 0);
    }

    /**
     * Single statement modification
     */
    @Test
    public void closure20() {
        Config config = TestUtils.setupConfig("closure_20");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("singleLine") > 0);
    }

    /**
     * Single statement moved
     */
    @Test
    public void closure13() {
        Config config = TestUtils.setupConfig("closure_13");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("singleLine") > 0);
    }

    @Test
    public void closure102() {
        Config config = TestUtils.setupConfig("closure_102");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("singleLine") > 0);
    }

    @Test
    public void closure111() {
        Config config = TestUtils.setupConfig("closure_111");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("singleLine") > 0);
    }

    @Test
    public void closure121() {
        Config config = TestUtils.setupConfig("closure_121");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("singleLine") > 0);
    }

    @Test
    public void chart4() {
        Config config = TestUtils.setupConfig("chart_4");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("singleLine") == 0);
    }

    @Test
    public void closure1() {
        Config config = TestUtils.setupConfig("closure_1");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("singleLine") == 0);
    }

    @Test
    public void closure81() {
        Config config = TestUtils.setupConfig("closure_81");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("singleLine") == 0);
    }

    @Test
    public void closure109() {
        Config config = TestUtils.setupConfig("closure_109");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("singleLine") == 0);
    }

    @Test
    public void closure117() {
        Config config = TestUtils.setupConfig("closure_117");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("singleLine") == 0);
    }

}
