package add.features.extractor;

import add.entities.Metrics;
import add.main.Config;
import add.utils.TestUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by tdurieux
 *
 * Tests for the features:
 *  - addedLinesAllLines
 *  - removedLinesAllLines
 *  - modifiedLinesAllLines
 *  - patchSizeAllLines
 *  - addedLinesCodeOnly
 *  - removedLinesCodeOnly
 *  - modifiedLinesCodeOnly
 *  - patchSizeCodeOnly
 */
public class PatchSizeMetricsTest {

    // BEGINNING of tests on patch size metrics considering ALL LINES

    @Test
    public void testPatchSizeOnlyWithAddedLinesAllLines_math4() {
        Config config = TestUtils.setupConfig("math_4");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(6, metrics.getFeatureCounter("addedLinesAllLines"));
        assertEquals(0, metrics.getFeatureCounter("removedLinesAllLines"));
        assertEquals(0, metrics.getFeatureCounter("modifiedLinesAllLines"));
        assertEquals(6, metrics.getFeatureCounter("patchSizeAllLines"));
    }

    @Test
    public void testPatchSizeOnlyWithRemovedLinesAllLines_math50() {
        Config config = TestUtils.setupConfig("math_50");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(0, metrics.getFeatureCounter("addedLinesAllLines"));
        assertEquals(4, metrics.getFeatureCounter("removedLinesAllLines"));
        assertEquals(0, metrics.getFeatureCounter("modifiedLinesAllLines"));
        assertEquals(4, metrics.getFeatureCounter("patchSizeAllLines"));
    }

    @Test
    public void testPatchSizeOnlyWithModifiedLinesAllLines_chart1() {
        Config config = TestUtils.setupConfig("chart_1");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(0, metrics.getFeatureCounter("addedLinesAllLines"));
        assertEquals(0, metrics.getFeatureCounter("removedLinesAllLines"));
        assertEquals(1, metrics.getFeatureCounter("modifiedLinesAllLines"));
        assertEquals(1, metrics.getFeatureCounter("patchSizeAllLines"));
    }

    @Test
    public void testPatchSizeAllLines_closure24() {
        Config config = TestUtils.setupConfig("closure_24");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(2, metrics.getFeatureCounter("addedLinesAllLines"));
        assertEquals(1, metrics.getFeatureCounter("removedLinesAllLines"));
        assertEquals(2, metrics.getFeatureCounter("modifiedLinesAllLines"));
        assertEquals(5, metrics.getFeatureCounter("patchSizeAllLines"));
    }

    @Test
    public void testPatchSizeAllLines_time23() {
        Config config = TestUtils.setupConfig("time_23");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(5, metrics.getFeatureCounter("addedLinesAllLines"));
        assertEquals(4, metrics.getFeatureCounter("removedLinesAllLines"));
        assertEquals(4, metrics.getFeatureCounter("modifiedLinesAllLines"));
        assertEquals(13, metrics.getFeatureCounter("patchSizeAllLines"));
    }

    // END of tests on patch size metrics considering ALL LINES

    // BEGINNING of tests on patch size metrics considering CODE ONLY

    // Lines of code were commented
    @Test
    public void testPatchSizeCodeOnly_math38() {
        Config config = TestUtils.setupConfig("math_38");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(0, metrics.getFeatureCounter("addedLinesCodeOnly"));
        assertEquals(2, metrics.getFeatureCounter("removedLinesCodeOnly"));
        assertEquals(2, metrics.getFeatureCounter("modifiedLinesCodeOnly"));
        assertEquals(4, metrics.getFeatureCounter("patchSizeCodeOnly"));
    }

    // Deletion of an empty line and addition of a single line comment
    @Test
    public void testPatchSizeCodeOnly_accumulo13eb19c2() {
        Config config = TestUtils.setupConfig("accumulo_13eb19c2");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(7, metrics.getFeatureCounter("addedLinesCodeOnly"));
        assertEquals(0, metrics.getFeatureCounter("removedLinesCodeOnly"));
        assertEquals(1, metrics.getFeatureCounter("modifiedLinesCodeOnly"));
        assertEquals(8, metrics.getFeatureCounter("patchSizeCodeOnly"));
    }

    // Addition of single line comments
    @Test
    public void testPatchSizeCodeOnly_bears5() {
        Config config = TestUtils.setupConfig("bears_5");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(3, metrics.getFeatureCounter("addedLinesCodeOnly"));
        assertEquals(3, metrics.getFeatureCounter("removedLinesCodeOnly"));
        assertEquals(0, metrics.getFeatureCounter("modifiedLinesCodeOnly"));
        assertEquals(6, metrics.getFeatureCounter("patchSizeCodeOnly"));
    }

    // Single and non-single comments, addition of empty lines
    @Test
    public void testPatchSizeCodeOnly_bears140() {
        Config config = TestUtils.setupConfig("bears_140");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(12, metrics.getFeatureCounter("addedLinesCodeOnly"));
        assertEquals(0, metrics.getFeatureCounter("removedLinesCodeOnly"));
        assertEquals(2, metrics.getFeatureCounter("modifiedLinesCodeOnly"));
        assertEquals(14, metrics.getFeatureCounter("patchSizeCodeOnly"));
    }

    // END of tests on patch size metrics considering CODE ONLY
}
