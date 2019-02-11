package add.features.detector.repairpatterns;

import add.entities.RepairPatterns;
import add.main.Config;
import add.utils.TestUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by tdurieux
 *
 * Tests for the features expLogicMod, expLogicExpand, expLogicReduce, and expArithMod
 */
public class ExpressionFixDetectorTest {

    @Test
    public void chart1() {
        Config config = TestUtils.setupConfig("chart_1");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicMod") > 0);
    }

    @Test
    public void closure4() {
        Config config = TestUtils.setupConfig("closure_4");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicMod") > 0);
    }

    @Test
    public void closure104() {
        Config config = TestUtils.setupConfig("closure_104");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicMod") > 0);
    }

    @Test
    public void closure55() {
        Config config = TestUtils.setupConfig("closure_55");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicExpand") > 0);
    }

    @Test
    public void chart5() {
        Config config = TestUtils.setupConfig("chart_5");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicExpand") == 0);
        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicMod") == 0);
        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicReduce") > 0);
    }

    @Test
    public void chart16() {
        Config config = TestUtils.setupConfig("chart_16");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicReduce") == 0);
        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicMod") > 0);
    }


    @Test
    public void closure6() {
        Config config = TestUtils.setupConfig("closure_6");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicReduce") == 0);
        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicMod") == 0);
    }

    @Test
    public void closure20() {
        Config config = TestUtils.setupConfig("closure_20");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicExpand") > 0);
    }

    @Test
    public void closure23() {
        Config config = TestUtils.setupConfig("closure_23");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicReduce") > 0);
    }

    @Test
    public void closure30() {
        Config config = TestUtils.setupConfig("closure_30");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicReduce") > 0);
        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicMod") == 0);
    }

    @Test
    public void closure31() {
        Config config = TestUtils.setupConfig("closure_31");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicReduce") > 0);
        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicMod") == 0);
    }

    @Test
    public void closure35() {
        Config config = TestUtils.setupConfig("closure_35");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicMod") == 0);
    }

    @Test
    public void closure131() {
        Config config = TestUtils.setupConfig("closure_131");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicReduce") == 0);
        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicMod") == 0);
    }


    @Test
    public void math64() {
        Config config = TestUtils.setupConfig("math_64");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expArithMod") > 0);
        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicMod") == 0);
    }

    @Test
    public void math76() {
        Config config = TestUtils.setupConfig("math_76");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expArithMod") > 0);
        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicMod") > 0);
    }

    @Test
    public void closure80() {
        Config config = TestUtils.setupConfig("closure_80");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicExpand") > 0);
    }

    @Test
    public void closure19() {
        Config config = TestUtils.setupConfig("closure_19");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicExpand") == 0);
    }

    @Test
    public void closure44() {
        Config config = TestUtils.setupConfig("closure_44");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicExpand") == 0);
    }

    @Test
    public void chart10() {
        Config config = TestUtils.setupConfig("chart_10");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expArithMod") == 0);
    }
}
