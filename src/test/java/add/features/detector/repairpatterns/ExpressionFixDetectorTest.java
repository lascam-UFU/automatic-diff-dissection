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
        Config config = TestUtils.setupConfig("Chart 1");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicMod") > 0);
    }

    @Test
    public void closure4() {
        Config config = TestUtils.setupConfig("Closure 4");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicMod") > 0);
    }

    @Test
    public void closure104() {
        Config config = TestUtils.setupConfig("Closure 104");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicMod") > 0);
    }

    @Test
    public void closure55() {
        Config config = TestUtils.setupConfig("Closure 55");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicExpand") > 0);
    }

    @Test
    public void chart5() {
        Config config = TestUtils.setupConfig("Chart 5");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicExpand") == 0);
        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicMod") == 0);
        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicReduce") > 0);
    }

    @Test
    public void chart16() {
        Config config = TestUtils.setupConfig("Chart 16");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicReduce") == 0);
        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicMod") > 0);
    }


    @Test
    public void closure6() {
        Config config = TestUtils.setupConfig("Closure 6");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicReduce") == 0);
        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicMod") == 0);
    }

    @Test
    public void closure20() {
        Config config = TestUtils.setupConfig("Closure 20");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicExpand") > 0);
    }

    @Test
    public void closure23() {
        Config config = TestUtils.setupConfig("Closure 23");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicReduce") > 0);
    }

    @Test
    public void closure30() {
        Config config = TestUtils.setupConfig("Closure 30");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicReduce") > 0);
        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicMod") == 0);
    }

    @Test
    public void closure31() {
        Config config = TestUtils.setupConfig("Closure 31");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicReduce") > 0);
        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicMod") == 0);
    }

    @Test
    public void closure35() {
        Config config = TestUtils.setupConfig("Closure 35");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicMod") == 0);
    }

    @Test
    public void closure131() {
        Config config = TestUtils.setupConfig("Closure 131");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicReduce") == 0);
        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicMod") == 0);
    }


    @Test
    public void math64() {
        Config config = TestUtils.setupConfig("Math 64");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expArithMod") > 0);
        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicMod") == 0);
    }

    @Test
    public void math76() {
        Config config = TestUtils.setupConfig("Math 76");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expArithMod") > 0);
        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicMod") > 0);
    }

    @Test
    public void closure80() {
        Config config = TestUtils.setupConfig("Closure 80");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicExpand") > 0);
    }

    @Test
    public void closure19() {
        Config config = TestUtils.setupConfig("Closure 19");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicExpand") == 0);
    }

    @Test
    public void closure44() {
        Config config = TestUtils.setupConfig("Closure 44");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expLogicExpand") == 0);
    }

    @Test
    public void chart10() {
        Config config = TestUtils.setupConfig("Chart 10");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("expArithMod") == 0);
    }
}
