package fr.inria.spirals.features.detector.repairpatterns;

import fr.inria.spirals.entities.RepairPatterns;
import fr.inria.spirals.main.Config;
import fr.inria.spirals.utils.TestUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fermadeiral
 *
 * Tests for the feature singleLine
 */
public class RepairPatternDetectorTest4SingleLinePattern {

    /**
     * Single line addition
     */
    @Test
    public void lang38() {
        Config config = TestUtils.setupConfig("Lang 38");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("singleLine") > 0);
    }

    /**
     * Single line removal
     */
    @Test
    public void closure31() {
        Config config = TestUtils.setupConfig("Closure 31");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("singleLine") > 0);
    }

    /**
     * Single line modification
     */
    @Test
    public void chart1() {
        Config config = TestUtils.setupConfig("Chart 1");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("singleLine") > 0);
    }

    /**
     * Single statement modification
     */
    @Test
    public void closure20() {
        Config config = TestUtils.setupConfig("Closure 20");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("singleLine") > 0);
    }

    /**
     * Single statement moved
     */
    @Test
    public void closure13() {
        Config config = TestUtils.setupConfig("Closure 13");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("singleLine") > 0);
    }

    @Test
    public void closure102() {
        Config config = TestUtils.setupConfig("Closure 102");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("singleLine") > 0);
    }

    @Test
    public void closure111() {
        Config config = TestUtils.setupConfig("Closure 111");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("singleLine") > 0);
    }

    @Test
    public void closure121() {
        Config config = TestUtils.setupConfig("Closure 121");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("singleLine") > 0);
    }

    @Test
    public void chart4() {
        Config config = TestUtils.setupConfig("Chart 4");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("singleLine") == 0);
    }

    @Test
    public void closure1() {
        Config config = TestUtils.setupConfig("Closure 1");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("singleLine") == 0);
    }

    @Test
    public void closure81() {
        Config config = TestUtils.setupConfig("Closure 81");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("singleLine") == 0);
    }

    @Test
    public void closure109() {
        Config config = TestUtils.setupConfig("Closure 109");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("singleLine") == 0);
    }

    @Test
    public void closure117() {
        Config config = TestUtils.setupConfig("Closure 117");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("singleLine") == 0);
    }

}
