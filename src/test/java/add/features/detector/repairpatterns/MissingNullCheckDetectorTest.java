package add.features.detector.repairpatterns;

import add.entities.RepairPatterns;
import add.main.Config;
import add.utils.TestUtils;
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
        Config config = TestUtils.setupConfig("chart_4");

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
        Config config = TestUtils.setupConfig("chart_14");

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
        Config config = TestUtils.setupConfig("chart_15");

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
        Config config = TestUtils.setupConfig("chart_25");

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
        Config config = TestUtils.setupConfig("lang_33");

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
        Config config = TestUtils.setupConfig("math_68");

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
        Config config = TestUtils.setupConfig("mockito_4");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("missNullCheckP") > 0);
    }

    @Test
    public void closure20() {
        Config config = TestUtils.setupConfig("closure_20");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("missNullCheckP") > 0);
        Assert.assertTrue(repairPatterns.getFeatureCounter("missNullCheckN") == 0);
    }

    @Test
    public void closure23() {
        Config config = TestUtils.setupConfig("closure_23");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("missNullCheckP") == 0);
    }

    @Test
    public void closure125() {
        Config config = TestUtils.setupConfig("closure_125");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("missNullCheckN") == 0);
    }

    @Test
    public void mockito22() {
        Config config = TestUtils.setupConfig("mockito_22");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("missNullCheckP") == 0);
    }

}
