package fr.inria.spirals.features.extractor;

import fr.inria.spirals.entities.Metrics;
import fr.inria.spirals.utils.TestUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by tdurieux
 */
public class MetricExtractorTest {

    @Test
    public void chart1() {
        TestUtils.setupConfig("Chart 1");

        MetricExtractor extractor = new MetricExtractor();
        Metrics metrics = extractor.extract();
        assertEquals(1, metrics.getFeatureCounter("nbFiles"));
        assertEquals(0, metrics.getFeatureCounter("addedLines"));
        assertEquals(0, metrics.getFeatureCounter("removedLines"));
        assertEquals(1, metrics.getFeatureCounter("modifiedLines"));
        assertEquals(1, metrics.getFeatureCounter("patchSize"));
        assertEquals(1, metrics.getFeatureCounter("nbChunks"));
        assertEquals(0, metrics.getFeatureCounter("spreadingAllLines"));
        assertEquals(0, metrics.getFeatureCounter("spreadingCodeOnly"));
    }

    @Test
    public void chart4() {
        TestUtils.setupConfig("Chart 4");

        MetricExtractor extractor = new MetricExtractor();
        Metrics metrics = extractor.extract();
        assertEquals(1, metrics.getFeatureCounter("nbFiles"));
        assertEquals(2, metrics.getFeatureCounter("addedLines"));
        assertEquals(0, metrics.getFeatureCounter("removedLines"));
        assertEquals(0, metrics.getFeatureCounter("modifiedLines"));
        assertEquals(2, metrics.getFeatureCounter("patchSize"));
        assertEquals(2, metrics.getFeatureCounter("nbChunks"));
        assertEquals(8, metrics.getFeatureCounter("spreadingAllLines"));
        assertEquals(8, metrics.getFeatureCounter("spreadingCodeOnly"));
    }

    @Test
    public void chart18() {
        TestUtils.setupConfig("Chart 18");

        MetricExtractor extractor = new MetricExtractor();
        Metrics metrics = extractor.extract();
        assertEquals(2, metrics.getFeatureCounter("nbFiles"));
        assertEquals(10, metrics.getFeatureCounter("addedLines"));
        assertEquals(2, metrics.getFeatureCounter("removedLines"));
        assertEquals(1, metrics.getFeatureCounter("modifiedLines"));
        assertEquals(13, metrics.getFeatureCounter("patchSize"));
        assertEquals(6, metrics.getFeatureCounter("nbChunks"));
        assertEquals(19, metrics.getFeatureCounter("spreadingAllLines"));
        assertEquals(9, metrics.getFeatureCounter("spreadingCodeOnly"));
    }

    @Test
    public void closure24() {
        TestUtils.setupConfig("Closure 24");

        MetricExtractor extractor = new MetricExtractor();
        Metrics metrics = extractor.extract();
        assertEquals(1, metrics.getFeatureCounter("nbFiles"));
        assertEquals(2, metrics.getFeatureCounter("addedLines"));
        assertEquals(1, metrics.getFeatureCounter("removedLines"));
        assertEquals(2, metrics.getFeatureCounter("modifiedLines"));
        assertEquals(5, metrics.getFeatureCounter("patchSize"));
        assertEquals(4, metrics.getFeatureCounter("nbChunks"));
        assertEquals(15, metrics.getFeatureCounter("spreadingAllLines"));
        assertEquals(9, metrics.getFeatureCounter("spreadingCodeOnly"));
    }

    @Test
    public void closure76() {
        TestUtils.setupConfig("Closure 76");

        MetricExtractor extractor = new MetricExtractor();
        Metrics metrics = extractor.extract();
        assertEquals(1, metrics.getFeatureCounter("nbFiles"));
        assertEquals(37, metrics.getFeatureCounter("addedLines"));
        assertEquals(6, metrics.getFeatureCounter("removedLines"));
        assertEquals(0, metrics.getFeatureCounter("modifiedLines"));
        assertEquals(43, metrics.getFeatureCounter("patchSize"));
        assertEquals(11, metrics.getFeatureCounter("nbChunks"));
        assertEquals(73, metrics.getFeatureCounter("spreadingAllLines"));
        assertEquals(48, metrics.getFeatureCounter("spreadingCodeOnly"));
    }

    @Test
    public void math4() {
        TestUtils.setupConfig("Math 4");

        MetricExtractor extractor = new MetricExtractor();
        Metrics metrics = extractor.extract();
        assertEquals(2, metrics.getFeatureCounter("nbFiles"));
        assertEquals(6, metrics.getFeatureCounter("addedLines"));
        assertEquals(0, metrics.getFeatureCounter("removedLines"));
        assertEquals(0, metrics.getFeatureCounter("modifiedLines"));
        assertEquals(6, metrics.getFeatureCounter("patchSize"));
        assertEquals(2, metrics.getFeatureCounter("nbChunks"));
        assertEquals(0, metrics.getFeatureCounter("spreadingAllLines"));
        assertEquals(0, metrics.getFeatureCounter("spreadingCodeOnly"));
    }

    @Test
    public void time12() {
        TestUtils.setupConfig("Time 12");

        MetricExtractor extractor = new MetricExtractor();
        Metrics metrics = extractor.extract();
        assertEquals(2, metrics.getFeatureCounter("nbFiles"));
        assertEquals(12, metrics.getFeatureCounter("addedLines"));
        assertEquals(0, metrics.getFeatureCounter("removedLines"));
        assertEquals(2, metrics.getFeatureCounter("modifiedLines"));
        assertEquals(14, metrics.getFeatureCounter("patchSize"));
        assertEquals(8, metrics.getFeatureCounter("nbChunks"));
        assertEquals(70, metrics.getFeatureCounter("spreadingAllLines"));
        assertEquals(26, metrics.getFeatureCounter("spreadingCodeOnly"));
    }

    @Test
    public void time23() {
        TestUtils.setupConfig("Time 23");

        MetricExtractor extractor = new MetricExtractor();
        Metrics metrics = extractor.extract();
        assertEquals(1, metrics.getFeatureCounter("nbFiles"));
        assertEquals(5, metrics.getFeatureCounter("addedLines"));
        assertEquals(4, metrics.getFeatureCounter("removedLines"));
        assertEquals(4, metrics.getFeatureCounter("modifiedLines"));
        assertEquals(13, metrics.getFeatureCounter("patchSize"));
        assertEquals(8, metrics.getFeatureCounter("nbChunks"));
        assertEquals(17, metrics.getFeatureCounter("spreadingAllLines"));
        assertEquals(17, metrics.getFeatureCounter("spreadingCodeOnly"));
    }

}
