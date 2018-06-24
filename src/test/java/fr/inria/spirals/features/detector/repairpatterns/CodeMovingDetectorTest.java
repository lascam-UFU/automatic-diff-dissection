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
 * Tests for the feature codeMove
 */
public class CodeMovingDetectorTest {

    @Test
    public void closure13() {
        Config config = TestUtils.setupConfig("Closure 13");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("codeMove") > 0);
    }

    @Test
    public void closure68() {
        Config config = TestUtils.setupConfig("Closure 68");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("codeMove") > 0);
    }

    @Test
    public void closure102() {
        Config config = TestUtils.setupConfig("Closure 102");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("codeMove") > 0);
    }

    @Test
    public void closure117() {
        Config config = TestUtils.setupConfig("Closure 117");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("codeMove") > 0);
    }

    @Test
    public void lang53() {
        Config config = TestUtils.setupConfig("Lang 53");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("codeMove") > 0);
    }

    @Test
    public void math64() {
        Config config = TestUtils.setupConfig("Math 64");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("codeMove") > 0);
    }

    @Test
    @Ignore
    public void math86() {
        Config config = TestUtils.setupConfig("Math 86");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("codeMove") > 0);
    }
}
