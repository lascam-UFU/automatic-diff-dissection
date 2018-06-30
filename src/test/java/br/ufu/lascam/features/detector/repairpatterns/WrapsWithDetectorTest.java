package br.ufu.lascam.features.detector.repairpatterns;

import br.ufu.lascam.entities.RepairPatterns;
import br.ufu.lascam.main.Config;
import br.ufu.lascam.utils.TestUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by fermadeiral
 *
 * Tests for the features wrapsIf, wrapsIfElse, wrapsElse, wrapsTryCatch, wrapsMethod, wrapsLoop, unwrapIfElse, unwrapTryCatch and unwrapMethod
 */
public class WrapsWithDetectorTest {

    @Test
    public void chart18() {
        Config config = TestUtils.setupConfig("Chart 18");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("wrapsIf") > 0);
    }

    @Ignore
    @Test
    public void lang31() {
        Config config = TestUtils.setupConfig("Lang 31");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("wrapsIfElse") > 0);
    }

    @Test
    public void closure2() {
        Config config = TestUtils.setupConfig("Closure 2");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("wrapsIfElse") > 0);
    }

    @Test
    public void lang33() {
        Config config = TestUtils.setupConfig("Lang 33");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("wrapsIfElse") > 0);
    }

    @Test
    public void closure111() {
        Config config = TestUtils.setupConfig("Closure 111");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("wrapsIfElse") > 0);
    }

    @Test
    public void chart21() {
        Config config = TestUtils.setupConfig("Chart 21");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("wrapsElse") > 0);
    }

    @Test
    public void lang17() {
        Config config = TestUtils.setupConfig("Lang 17");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("unwrapIfElse") > 0);
        Assert.assertTrue(repairPatterns.getFeatureCounter("unwrapMethod") > 0);
    }

    @Test
    public void math46() {
        Config config = TestUtils.setupConfig("Math 46");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("unwrapIfElse") > 0);
    }

    @Test
    public void time18() {
        Config config = TestUtils.setupConfig("Time 18");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("wrapsTryCatch") > 0);
    }

    @Test
    public void closure83() {
        Config config = TestUtils.setupConfig("Closure 83");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("wrapsTryCatch") > 0);
    }

    @Test
    public void math60() {
        Config config = TestUtils.setupConfig("Math 60");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("unwrapTryCatch") > 0);
    }

    @Test
    public void lang13() {
        Config config = TestUtils.setupConfig("Lang 13");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("unwrapTryCatch") == 0);
    }

    @Test
    public void chart10() {
        Config config = TestUtils.setupConfig("Chart 10");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("wrapsMethod") > 0);
    }

    @Test
    public void chart12() {
        Config config = TestUtils.setupConfig("Chart 12");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("wrapsMethod") > 0);
    }

    @Test
    public void math105() {
        Config config = TestUtils.setupConfig("Math 105");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("wrapsMethod") > 0);
    }

    @Test
    public void time8() {
        Config config = TestUtils.setupConfig("Time 8");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("wrapsMethod") > 0);
    }

    @Ignore
    @Test
    public void mockito14() {
        Config config = TestUtils.setupConfig("Mockito 14");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("wrapsMethod") > 0);
    }

    @Ignore
    @Test
    public void math27() {
        Config config = TestUtils.setupConfig("Math 27");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("unwrapMethod") > 0);
    }

    @Test
    public void time17() {
        Config config = TestUtils.setupConfig("Time 17");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("unwrapMethod") > 0);
    }

    @Test
    public void closure124() {
        Config config = TestUtils.setupConfig("Closure 124");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("wrapsLoop") > 0);
    }

    @Test
    public void math7() {
        Config config = TestUtils.setupConfig("Math 7");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("wrapsLoop") > 0);
    }

}
