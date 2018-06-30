package br.ufu.lascam.features.detector.repairpatterns;

import br.ufu.lascam.entities.RepairPatterns;
import br.ufu.lascam.main.Config;
import br.ufu.lascam.utils.TestUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fermadeiral
 *
 * Tests for the features missNullCheckP and missNullCheckN
 */
public class MissingNullCheckDetectorTest {

    /**
     * Addition of a null-check for an existing variable.
     *
     * The null-check is inside a new if condition, wrapping existing code.
     */
    @Test
    public void chart4() {
        Config config = TestUtils.setupConfig("Chart 4");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("missNullCheckN") > 0);
    }

    /**
     * Addition of null-checks for an existing variable.
     *
     * The null-checks are inside new if conditions, and their bodies contain new code.
     */
    @Test
    public void chart14() {
        Config config = TestUtils.setupConfig("Chart 14");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("missNullCheckP") > 0);
    }

    /**
     * Addition of null-checks for an existing variable.
     *
     * One null-check is inside a new if condition, and its body contains new code.
     * One null-check is inside a new if condition, wrapping existing code.
     */
    @Test
    public void chart15() {
        Config config = TestUtils.setupConfig("Chart 15");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("missNullCheckP") > 0);
        Assert.assertTrue(repairPatterns.getFeatureCounter("missNullCheckN") > 0);
    }

    /**
     * Addition of a null-check for a new variable.
     *
     * The null-check is inside a new if condition, wrapping existing code.
     */
    @Test
    public void chart25() {
        Config config = TestUtils.setupConfig("Chart 25");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("missNullCheckP") > 0);
        Assert.assertTrue(repairPatterns.getFeatureCounter("missNullCheckN") > 0);
    }

    /**
     * Addition of a null-check for an existing variable (array).
     *
     * The null-check is inside a new condition of the type <condition> ? true : false, wrapping existing code.
     */
    @Test
    public void lang33() {
        Config config = TestUtils.setupConfig("Lang 33");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("missNullCheckP") > 0);
    }

    /**
     * Addition of a null-check for an existing variable: the variable is a field declared in a super class.
     *
     * The null-check is inside a new if condition, wrapping existing code.
     */
    @Test
    public void math68() {
        Config config = TestUtils.setupConfig("Math 68");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("missNullCheckN") > 0);
    }

    /**
     * Addition of a null-check for the return of a method call done with an existing variable.
     *
     * The null-check is inside a new if condition, and its body contains new code.
     */
    @Test
    public void mockito4() {
        Config config = TestUtils.setupConfig("Mockito 4");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("missNullCheckP") > 0);
    }

    @Test
    public void closure20() {
        Config config = TestUtils.setupConfig("Closure 20");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("missNullCheckP") > 0);
        Assert.assertTrue(repairPatterns.getFeatureCounter("missNullCheckN") == 0);
    }

    @Test
    public void closure23() {
        Config config = TestUtils.setupConfig("Closure 23");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("missNullCheckP") == 0);
    }

    @Test
    public void closure125() {
        Config config = TestUtils.setupConfig("Closure 125");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("missNullCheckN") == 0);
    }

    @Test
    public void mockito22() {
        Config config = TestUtils.setupConfig("Mockito 22");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("missNullCheckP") == 0);
    }

}
