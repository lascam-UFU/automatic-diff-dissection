package add.features.extractor;

import add.entities.Metrics;
import add.main.Config;
import add.utils.TestUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by fermadeiral
 *
 * Tests for the features:
 *  - nbChunks
 *  - spreadingAllLines
 *  - spreadingCodeOnly
 *  - nbFiles
 *  - nbModifiedClasses
 *  - nbModifiedMethods
 */
public class PatchSpreadingMetricsTest {

    @Test
    public void chart1() {
        Config config = TestUtils.setupConfig("chart_1");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(1, metrics.getFeatureCounter("nbFiles"));
        assertEquals(1, metrics.getFeatureCounter("nbChunks"));
        assertEquals(0, metrics.getFeatureCounter("spreadingAllLines"));
        assertEquals(0, metrics.getFeatureCounter("spreadingCodeOnly"));
    }

    @Test
    public void chart4() {
        Config config = TestUtils.setupConfig("chart_4");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(1, metrics.getFeatureCounter("nbFiles"));
        assertEquals(2, metrics.getFeatureCounter("nbChunks"));
        assertEquals(8, metrics.getFeatureCounter("spreadingAllLines"));
        assertEquals(8, metrics.getFeatureCounter("spreadingCodeOnly"));
    }

    @Test
    public void chart18() {
        Config config = TestUtils.setupConfig("chart_18");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(2, metrics.getFeatureCounter("nbFiles"));
        assertEquals(6, metrics.getFeatureCounter("nbChunks"));
        assertEquals(19, metrics.getFeatureCounter("spreadingAllLines"));
        assertEquals(9, metrics.getFeatureCounter("spreadingCodeOnly"));
    }

    @Test
    public void closure24() {
        Config config = TestUtils.setupConfig("closure_24");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(1, metrics.getFeatureCounter("nbFiles"));
        assertEquals(4, metrics.getFeatureCounter("nbChunks"));
        assertEquals(15, metrics.getFeatureCounter("spreadingAllLines"));
        assertEquals(9, metrics.getFeatureCounter("spreadingCodeOnly"));
    }

    @Test
    public void closure76() {
        Config config = TestUtils.setupConfig("closure_76");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(1, metrics.getFeatureCounter("nbFiles"));
        assertEquals(11, metrics.getFeatureCounter("nbChunks"));
        assertEquals(73, metrics.getFeatureCounter("spreadingAllLines"));
        assertEquals(48, metrics.getFeatureCounter("spreadingCodeOnly"));
    }

    @Test
    public void math4() {
        Config config = TestUtils.setupConfig("math_4");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(2, metrics.getFeatureCounter("nbFiles"));
        assertEquals(2, metrics.getFeatureCounter("nbChunks"));
        assertEquals(0, metrics.getFeatureCounter("spreadingAllLines"));
        assertEquals(0, metrics.getFeatureCounter("spreadingCodeOnly"));
    }

    @Test
    public void time12() {
        Config config = TestUtils.setupConfig("time_12");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(2, metrics.getFeatureCounter("nbFiles"));
        assertEquals(8, metrics.getFeatureCounter("nbChunks"));
        assertEquals(70, metrics.getFeatureCounter("spreadingAllLines"));
        assertEquals(26, metrics.getFeatureCounter("spreadingCodeOnly"));
    }

    @Test
    public void time23() {
        Config config = TestUtils.setupConfig("time_23");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(1, metrics.getFeatureCounter("nbFiles"));
        assertEquals(8, metrics.getFeatureCounter("nbChunks"));
        assertEquals(17, metrics.getFeatureCounter("spreadingAllLines"));
        assertEquals(17, metrics.getFeatureCounter("spreadingCodeOnly"));
    }

    @Test
    public void testNbModifiedClassesClosure30() {
        Config config = TestUtils.setupConfig("closure_30");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(3, metrics.getFeatureCounter("nbModifiedClasses"));
    }

    @Test
    public void testNbModifiedClassesClosure120() {
        Config config = TestUtils.setupConfig("closure_120");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(1, metrics.getFeatureCounter("nbModifiedClasses"));
    }

    @Test
    public void testNbModifiedClassesMath4() {
        Config config = TestUtils.setupConfig("math_4");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(2, metrics.getFeatureCounter("nbModifiedClasses"));
    }

    @Test
    public void testNbModifiedClassesMath12() {
        Config config = TestUtils.setupConfig("math_12");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(1, metrics.getFeatureCounter("nbModifiedClasses"));
    }

    @Test
    public void testNbModifiedClassesMockito23() {
        Config config = TestUtils.setupConfig("mockito_23");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(2, metrics.getFeatureCounter("nbModifiedClasses"));
    }

    @Test
    public void testNbModifiedMethodsMath12() {
        Config config = TestUtils.setupConfig("math_12");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(0, metrics.getFeatureCounter("nbModifiedMethods"));
    }

    @Test
    public void testNbModifiedMethodsMath104() {
        Config config = TestUtils.setupConfig("math_104");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(0, metrics.getFeatureCounter("nbModifiedMethods"));
    }

    @Test
    public void testNbModifiedMethodsMockito21() {
        Config config = TestUtils.setupConfig("mockito_21");

        MetricExtractor extractor = new MetricExtractor(config);
        Metrics metrics = extractor.analyze();
        assertEquals(3, metrics.getFeatureCounter("nbModifiedMethods"));
    }

}
