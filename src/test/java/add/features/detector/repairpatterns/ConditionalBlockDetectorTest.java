package add.features.detector.repairpatterns;

import add.entities.RepairPatterns;
import add.main.Config;
import add.utils.TestUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fermadeiral
 *
 * Tests for the features condBlockOthersAdd, condBlockRetAdd, condBlockExcAdd and condBlockRem
 */
public class ConditionalBlockDetectorTest {

    @Test
    public void lang45() {
        Config config = TestUtils.setupConfig("lang_45");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("condBlockOthersAdd") > 0);
    }

    @Test
    public void closure5() {
        Config config = TestUtils.setupConfig("closure_5");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("condBlockRetAdd") > 0);
    }

    @Test
    public void math48() {
        Config config = TestUtils.setupConfig("math_48");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("condBlockExcAdd") > 0);
    }

    @Test
    public void math50() {
        Config config = TestUtils.setupConfig("math_50");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("condBlockRem") > 0);
    }

    @Test
    public void chart4() {
        Config config = TestUtils.setupConfig("chart_4");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("condBlockOthersAdd") == 0);
    }

    @Test
    public void chart14() {
        Config config = TestUtils.setupConfig("chart_14");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("condBlockOthersAdd") == 0);
    }

    @Test
    public void closure61() {
        Config config = TestUtils.setupConfig("closure_61");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("condBlockOthersAdd") > 0);
        Assert.assertTrue(repairPatterns.getFeatureCounter("condBlockRetAdd") > 0);
    }

    @Test
    public void closure19() {
        Config config = TestUtils.setupConfig("closure_19");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("condBlockOthersAdd") > 0);
    }

    @Test
    public void closure60() {
        Config config = TestUtils.setupConfig("closure_60");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("condBlockRetAdd") > 0);
    }

    @Test
    public void closure3() {
        Config config = TestUtils.setupConfig("closure_3");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("condBlockOthersAdd") > 0);
        Assert.assertTrue(repairPatterns.getFeatureCounter("condBlockRetAdd") > 0);
    }

    @Test
    public void closure66() {
        Config config = TestUtils.setupConfig("closure_66");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("condBlockOthersAdd") > 0);
    }

    @Test
    public void lang49() {
        Config config = TestUtils.setupConfig("lang_49");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("condBlockOthersAdd") > 0);
    }

    @Test
    public void closure11() {
        Config config = TestUtils.setupConfig("closure_11");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("condBlockRem") == 0);
    }

}
