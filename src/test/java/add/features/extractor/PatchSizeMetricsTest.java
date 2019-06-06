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

    @Test
    public void chart1() {
        Config config = TestUtils.setupConfig("chart_1");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(0, metrics.getFeatureCounter("addedLinesAllLines"));
        assertEquals(0, metrics.getFeatureCounter("removedLinesAllLines"));
        assertEquals(1, metrics.getFeatureCounter("modifiedLinesAllLines"));
        assertEquals(1, metrics.getFeatureCounter("patchSizeAllLines"));
    }

    @Test
    public void chart4() {
        Config config = TestUtils.setupConfig("chart_4");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(2, metrics.getFeatureCounter("addedLinesAllLines"));
        assertEquals(0, metrics.getFeatureCounter("removedLinesAllLines"));
        assertEquals(0, metrics.getFeatureCounter("modifiedLinesAllLines"));
        assertEquals(2, metrics.getFeatureCounter("patchSizeAllLines"));
    }

    @Test
    public void chart18() {
        Config config = TestUtils.setupConfig("chart_18");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(10, metrics.getFeatureCounter("addedLinesAllLines"));
        assertEquals(2, metrics.getFeatureCounter("removedLinesAllLines"));
        assertEquals(1, metrics.getFeatureCounter("modifiedLinesAllLines"));
        assertEquals(13, metrics.getFeatureCounter("patchSizeAllLines"));
    }

    @Test
    public void closure24() {
        Config config = TestUtils.setupConfig("closure_24");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(2, metrics.getFeatureCounter("addedLinesAllLines"));
        assertEquals(1, metrics.getFeatureCounter("removedLinesAllLines"));
        assertEquals(2, metrics.getFeatureCounter("modifiedLinesAllLines"));
        assertEquals(5, metrics.getFeatureCounter("patchSizeAllLines"));
    }

    @Test
    public void closure76() {
        Config config = TestUtils.setupConfig("closure_76");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(37, metrics.getFeatureCounter("addedLinesAllLines"));
        assertEquals(6, metrics.getFeatureCounter("removedLinesAllLines"));
        assertEquals(0, metrics.getFeatureCounter("modifiedLinesAllLines"));
        assertEquals(43, metrics.getFeatureCounter("patchSizeAllLines"));
    }

    @Test
    public void math4() {
        Config config = TestUtils.setupConfig("math_4");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(6, metrics.getFeatureCounter("addedLinesAllLines"));
        assertEquals(0, metrics.getFeatureCounter("removedLinesAllLines"));
        assertEquals(0, metrics.getFeatureCounter("modifiedLinesAllLines"));
        assertEquals(6, metrics.getFeatureCounter("patchSizeAllLines"));
    }

    @Test
    public void time12() {
        Config config = TestUtils.setupConfig("time_12");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(12, metrics.getFeatureCounter("addedLinesAllLines"));
        assertEquals(0, metrics.getFeatureCounter("removedLinesAllLines"));
        assertEquals(2, metrics.getFeatureCounter("modifiedLinesAllLines"));
        assertEquals(14, metrics.getFeatureCounter("patchSizeAllLines"));
    }

    @Test
    public void time23() {
        Config config = TestUtils.setupConfig("time_23");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(5, metrics.getFeatureCounter("addedLinesAllLines"));
        assertEquals(4, metrics.getFeatureCounter("removedLinesAllLines"));
        assertEquals(4, metrics.getFeatureCounter("modifiedLinesAllLines"));
        assertEquals(13, metrics.getFeatureCounter("patchSizeAllLines"));
    }

    @Test
    public void accumulo13eb19c2() {
        Config config = TestUtils.setupConfig("accumulo_13eb19c2");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(7, metrics.getFeatureCounter("addedLinesCodeOnly"));
        assertEquals(0, metrics.getFeatureCounter("removedLinesCodeOnly"));
        assertEquals(1, metrics.getFeatureCounter("modifiedLinesCodeOnly"));
        assertEquals(8, metrics.getFeatureCounter("patchSizeCodeOnly"));
    }

    @Test
    public void accumulo17344890() {
        Config config = TestUtils.setupConfig("accumulo_17344890");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(3, metrics.getFeatureCounter("addedLinesCodeOnly"));
        assertEquals(4, metrics.getFeatureCounter("removedLinesCodeOnly"));
        assertEquals(3, metrics.getFeatureCounter("modifiedLinesCodeOnly"));
        assertEquals(10, metrics.getFeatureCounter("patchSizeCodeOnly"));
    }

    @Test
    public void bears5() {
        Config config = TestUtils.setupConfig("bears_5");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(3, metrics.getFeatureCounter("addedLinesCodeOnly"));
        assertEquals(3, metrics.getFeatureCounter("removedLinesCodeOnly"));
        assertEquals(0, metrics.getFeatureCounter("modifiedLinesCodeOnly"));
        assertEquals(6, metrics.getFeatureCounter("patchSizeCodeOnly"));
    }

    @Test
    public void bears140() {
        Config config = TestUtils.setupConfig("bears_140");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(12, metrics.getFeatureCounter("addedLinesCodeOnly"));
        assertEquals(0, metrics.getFeatureCounter("removedLinesCodeOnly"));
        assertEquals(2, metrics.getFeatureCounter("modifiedLinesCodeOnly"));
        assertEquals(14, metrics.getFeatureCounter("patchSizeCodeOnly"));
    }

    @Test
    public void bears144() {
        Config config = TestUtils.setupConfig("bears_144");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(0, metrics.getFeatureCounter("addedLinesCodeOnly"));
        assertEquals(0, metrics.getFeatureCounter("removedLinesCodeOnly"));
        assertEquals(2, metrics.getFeatureCounter("modifiedLinesCodeOnly"));
        assertEquals(2, metrics.getFeatureCounter("patchSizeCodeOnly"));
    }

}
