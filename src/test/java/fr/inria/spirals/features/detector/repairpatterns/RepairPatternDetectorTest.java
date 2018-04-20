package fr.inria.spirals.features.detector.repairpatterns;

import fr.inria.spirals.entities.RepairPatterns;
import fr.inria.spirals.utils.TestUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fermadeiral
 */
public class RepairPatternDetectorTest {

    @Test
    public void chart4() {
        TestUtils.setupConfig("Chart 4");

        RepairPatternDetector detector = new RepairPatternDetector();
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("missNullCheckN") > 0);
        Assert.assertTrue(repairPatterns.getFeatureCounter("wrapsIf") > 0);
    }

    @Test
    public void chart18() {
        TestUtils.setupConfig("Chart 18");

        RepairPatternDetector detector = new RepairPatternDetector();
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("missNullCheckP") > 0);
    }

}
