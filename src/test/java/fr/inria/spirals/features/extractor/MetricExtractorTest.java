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
        assertEquals(1, metrics.getMetric("nbFiles"));
        assertEquals(0, metrics.getMetric("addedLines"));
        assertEquals(0, metrics.getMetric("removedLines"));
        assertEquals(1, metrics.getMetric("modifiedLines"));
        assertEquals(1, metrics.getMetric("patchSize"));
        assertEquals(1, metrics.getMetric("nbChunks"));
        assertEquals(0, metrics.getMetric("spreadingAllLines"));
        assertEquals(0, metrics.getMetric("spreadingCodeOnly"));
    }

    @Test
    public void chart4() {
        TestUtils.setupConfig("Chart 4");

        MetricExtractor extractor = new MetricExtractor();
        Metrics metrics = extractor.extract();
        assertEquals(1, metrics.getMetric("nbFiles"));
        assertEquals(2, metrics.getMetric("addedLines"));
        assertEquals(0, metrics.getMetric("removedLines"));
        assertEquals(0, metrics.getMetric("modifiedLines"));
        assertEquals(2, metrics.getMetric("patchSize"));
        assertEquals(2, metrics.getMetric("nbChunks"));
        assertEquals(8, metrics.getMetric("spreadingAllLines"));
        assertEquals(8, metrics.getMetric("spreadingCodeOnly"));
    }

    @Test
    public void chart18() {
        TestUtils.setupConfig("Chart 18");

        MetricExtractor extractor = new MetricExtractor();
        Metrics metrics = extractor.extract();
        assertEquals(2, metrics.getMetric("nbFiles"));
        assertEquals(10, metrics.getMetric("addedLines"));
        assertEquals(2, metrics.getMetric("removedLines"));
        assertEquals(1, metrics.getMetric("modifiedLines"));
        assertEquals(13, metrics.getMetric("patchSize"));
        assertEquals(6, metrics.getMetric("nbChunks"));
        assertEquals(19, metrics.getMetric("spreadingAllLines"));
        assertEquals(9, metrics.getMetric("spreadingCodeOnly"));
    }

    @Test
    public void closure24() {
        TestUtils.setupConfig("Closure 24");

        MetricExtractor extractor = new MetricExtractor();
        Metrics metrics = extractor.extract();
        assertEquals(1, metrics.getMetric("nbFiles"));
        assertEquals(2, metrics.getMetric("addedLines"));
        assertEquals(1, metrics.getMetric("removedLines"));
        assertEquals(2, metrics.getMetric("modifiedLines"));
        assertEquals(5, metrics.getMetric("patchSize"));
        assertEquals(4, metrics.getMetric("nbChunks"));
        assertEquals(15, metrics.getMetric("spreadingAllLines"));
        assertEquals(9, metrics.getMetric("spreadingCodeOnly"));
    }

    @Test
    public void closure76() {
        TestUtils.setupConfig("Closure 76");

        MetricExtractor extractor = new MetricExtractor();
        Metrics metrics = extractor.extract();
        assertEquals(1, metrics.getMetric("nbFiles"));
        assertEquals(37, metrics.getMetric("addedLines"));
        assertEquals(6, metrics.getMetric("removedLines"));
        assertEquals(0, metrics.getMetric("modifiedLines"));
        assertEquals(43, metrics.getMetric("patchSize"));
        assertEquals(11, metrics.getMetric("nbChunks"));
        assertEquals(73, metrics.getMetric("spreadingAllLines"));
        assertEquals(48, metrics.getMetric("spreadingCodeOnly"));
    }

    @Test
    public void math4() {
        TestUtils.setupConfig("Math 4");

        MetricExtractor extractor = new MetricExtractor();
        Metrics metrics = extractor.extract();
        assertEquals(2, metrics.getMetric("nbFiles"));
        assertEquals(6, metrics.getMetric("addedLines"));
        assertEquals(0, metrics.getMetric("removedLines"));
        assertEquals(0, metrics.getMetric("modifiedLines"));
        assertEquals(6, metrics.getMetric("patchSize"));
        assertEquals(2, metrics.getMetric("nbChunks"));
        assertEquals(0, metrics.getMetric("spreadingAllLines"));
        assertEquals(0, metrics.getMetric("spreadingCodeOnly"));
    }

    @Test
    public void time12() {
        TestUtils.setupConfig("Time 12");

        MetricExtractor extractor = new MetricExtractor();
        Metrics metrics = extractor.extract();
        assertEquals(2, metrics.getMetric("nbFiles"));
        assertEquals(12, metrics.getMetric("addedLines"));
        assertEquals(0, metrics.getMetric("removedLines"));
        assertEquals(2, metrics.getMetric("modifiedLines"));
        assertEquals(14, metrics.getMetric("patchSize"));
        assertEquals(8, metrics.getMetric("nbChunks"));
        assertEquals(70, metrics.getMetric("spreadingAllLines"));
        assertEquals(26, metrics.getMetric("spreadingCodeOnly"));
    }

    @Test
    public void time23() {
        TestUtils.setupConfig("Time 23");

        MetricExtractor extractor = new MetricExtractor();
        Metrics metrics = extractor.extract();
        assertEquals(1, metrics.getMetric("nbFiles"));
        assertEquals(5, metrics.getMetric("addedLines"));
        assertEquals(4, metrics.getMetric("removedLines"));
        assertEquals(4, metrics.getMetric("modifiedLines"));
        assertEquals(13, metrics.getMetric("patchSize"));
        assertEquals(8, metrics.getMetric("nbChunks"));
        assertEquals(17, metrics.getMetric("spreadingAllLines"));
        assertEquals(17, metrics.getMetric("spreadingCodeOnly"));
    }

}
