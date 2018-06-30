package br.ufu.lascam.features.detector.repairpatterns;

import br.ufu.lascam.entities.RepairPatterns;
import br.ufu.lascam.main.Config;
import br.ufu.lascam.utils.TestUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by tdurieux
 *
 * Tests for the feature copyPaste
 */
public class CopyPasteDetectorTest {

    @Test
    public void closure35() {
        Config config = TestUtils.setupConfig("Closure 35");

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
