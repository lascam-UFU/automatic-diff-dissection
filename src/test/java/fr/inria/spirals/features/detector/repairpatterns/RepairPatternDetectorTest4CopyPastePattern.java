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
 */
public class RepairPatternDetectorTest4CopyPastePattern {

    @Test
    public void closure35() {
        Config config = TestUtils.setupConfig("Closure 35");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("copyPaste") == 0);
    }

    @Test
    public void closure47() {
        Config config = TestUtils.setupConfig("Closure 47");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("copyPaste") == 0);
    }

    @Test
    public void closure94() {
        Config config = TestUtils.setupConfig("Closure 94");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("copyPaste") == 0);
    }

    @Test
    public void closure110() {
        Config config = TestUtils.setupConfig("Closure 110");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("copyPaste") == 0);
    }

    @Test
    public void closure131() {
        Config config = TestUtils.setupConfig("Closure 131");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("copyPaste") > 0);
    }

    @Test
    public void lang8() {
        Config config = TestUtils.setupConfig("Lang 8");

        RepairPatternDetector detector = new RepairPatternDetector(config);
        RepairPatterns repairPatterns = detector.analyze();

        Assert.assertTrue(repairPatterns.getFeatureCounter("copyPaste") == 0);
    }
}
