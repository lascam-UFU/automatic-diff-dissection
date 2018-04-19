package fr.inria.spirals.features.extractor;

import fr.inria.spirals.entities.Metrics;
import fr.inria.spirals.main.Config;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by tdurieux
 */
public class MetricExtractorTest {

    @Test
    public void chart1() {
        Config config = Config.getInstance();
        config.setBugId("Chart 1");

        String buggySourcePath = MetricExtractorTest.class.getResource("/chart_1/buggy-version").getPath();
        String fixedSourcePath = MetricExtractorTest.class.getResource("/chart_1/fixed-version").getPath();
        String diffPath = MetricExtractorTest.class.getResource("/chart_1/chart_1.diff").getPath();
        MetricExtractor extractor = new MetricExtractor(buggySourcePath, fixedSourcePath, diffPath);
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
        Config config = Config.getInstance();
        config.setBugId("Chart 4");

        String buggySourcePath = MetricExtractorTest.class.getResource("/chart_4/buggy-version").getPath();
        String fixedSourcePath = MetricExtractorTest.class.getResource("/chart_4/fixed-version").getPath();
        String diffPath = MetricExtractorTest.class.getResource("/chart_4/chart_4.diff").getPath();
        MetricExtractor extractor = new MetricExtractor(buggySourcePath, fixedSourcePath, diffPath);
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
        Config config = Config.getInstance();
        config.setBugId("Chart 18");

        String buggySourcePath = MetricExtractorTest.class.getResource("/chart_18/buggy-version").getPath();
        String fixedSourcePath = MetricExtractorTest.class.getResource("/chart_18/fixed-version").getPath();
        String diffPath = MetricExtractorTest.class.getResource("/chart_18/chart_18.diff").getPath();
        MetricExtractor extractor = new MetricExtractor(buggySourcePath, fixedSourcePath, diffPath);
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
        Config config = Config.getInstance();
        config.setBugId("Closure 24");

        String buggySourcePath = MetricExtractorTest.class.getResource("/closure_24/buggy-version").getPath();
        String fixedSourcePath = MetricExtractorTest.class.getResource("/closure_24/fixed-version").getPath();
        String diffPath = MetricExtractorTest.class.getResource("/closure_24/closure_24.diff").getPath();
        MetricExtractor extractor = new MetricExtractor(buggySourcePath, fixedSourcePath, diffPath);
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
        Config config = Config.getInstance();
        config.setBugId("Closure 76");

        String buggySourcePath = MetricExtractorTest.class.getResource("/closure_76/buggy-version").getPath();
        String fixedSourcePath = MetricExtractorTest.class.getResource("/closure_76/fixed-version").getPath();
        String diffPath = MetricExtractorTest.class.getResource("/closure_76/closure_76.diff").getPath();
        MetricExtractor extractor = new MetricExtractor(buggySourcePath, fixedSourcePath, diffPath);
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
        Config config = Config.getInstance();
        config.setBugId("Math 4");

        String buggySourcePath = MetricExtractorTest.class.getResource("/math_4/buggy-version").getPath();
        String fixedSourcePath = MetricExtractorTest.class.getResource("/math_4/fixed-version").getPath();
        String diffPath = MetricExtractorTest.class.getResource("/math_4/math_4.diff").getPath();
        MetricExtractor extractor = new MetricExtractor(buggySourcePath, fixedSourcePath, diffPath);
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
        Config config = Config.getInstance();
        config.setBugId("Time 12");

        String buggySourcePath = MetricExtractorTest.class.getResource("/time_12/buggy-version").getPath();
        String fixedSourcePath = MetricExtractorTest.class.getResource("/time_12/fixed-version").getPath();
        String diffPath = MetricExtractorTest.class.getResource("/time_12/time_12.diff").getPath();
        MetricExtractor extractor = new MetricExtractor(buggySourcePath, fixedSourcePath, diffPath);
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
        Config config = Config.getInstance();
        config.setBugId("Time 23");

        String buggySourcePath = MetricExtractorTest.class.getResource("/time_23/buggy-version").getPath();
        String fixedSourcePath = MetricExtractorTest.class.getResource("/time_23/fixed-version").getPath();
        String diffPath = MetricExtractorTest.class.getResource("/time_23/time_23.diff").getPath();
        MetricExtractor extractor = new MetricExtractor(buggySourcePath, fixedSourcePath, diffPath);
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
