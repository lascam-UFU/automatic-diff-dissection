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
        config.setProject("Chart");
        config.setBugId("1");

        String buggySourcePath = MetricExtractorTest.class.getResource("/chart_1/buggy-version").getPath();
        String fixedSourcePath = MetricExtractorTest.class.getResource("/chart_1/fixed-version").getPath();
        String diffPath = MetricExtractorTest.class.getResource("/chart_1/chart_1.diff").getPath();
        MetricExtractor extractor = new MetricExtractor(buggySourcePath, fixedSourcePath, diffPath);
        Metrics metrics = extractor.extract();
        assertNotNull(metrics);
        assertEquals(1, metrics.getNbFiles());
        assertEquals(0, metrics.getAddedLines());
        assertEquals(0, metrics.getRemovedLines());
        assertEquals(1, metrics.getModifiedLines());
        assertEquals(1, metrics.getPatchSize());
        assertEquals(1, metrics.getNbChunks());
        assertEquals(0, metrics.getSpreadingAllLines());
        assertEquals(0, metrics.getSpreadingCodeOnly());
    }

    @Test
    public void chart4() {
        Config config = Config.getInstance();
        config.setProject("Chart");
        config.setBugId("4");

        String buggySourcePath = MetricExtractorTest.class.getResource("/chart_4/buggy-version").getPath();
        String fixedSourcePath = MetricExtractorTest.class.getResource("/chart_4/fixed-version").getPath();
        String diffPath = MetricExtractorTest.class.getResource("/chart_4/chart_4.diff").getPath();
        MetricExtractor extractor = new MetricExtractor(buggySourcePath, fixedSourcePath, diffPath);
        Metrics metrics = extractor.extract();
        assertNotNull(metrics);
        assertEquals(1, metrics.getNbFiles());
        assertEquals(2, metrics.getAddedLines());
        assertEquals(0, metrics.getRemovedLines());
        assertEquals(0, metrics.getModifiedLines());
        assertEquals(2, metrics.getPatchSize());
        assertEquals(2, metrics.getNbChunks());
        assertEquals(8, metrics.getSpreadingAllLines());
        assertEquals(8, metrics.getSpreadingCodeOnly());
    }

    @Test
    public void chart18() {
        Config config = Config.getInstance();
        config.setProject("Chart");
        config.setBugId("18");

        String buggySourcePath = MetricExtractorTest.class.getResource("/chart_18/buggy-version").getPath();
        String fixedSourcePath = MetricExtractorTest.class.getResource("/chart_18/fixed-version").getPath();
        String diffPath = MetricExtractorTest.class.getResource("/chart_18/chart_18.diff").getPath();
        MetricExtractor extractor = new MetricExtractor(buggySourcePath, fixedSourcePath, diffPath);
        Metrics metrics = extractor.extract();
        assertNotNull(metrics);
        assertEquals(2, metrics.getNbFiles());
        assertEquals(10, metrics.getAddedLines());
        assertEquals(2, metrics.getRemovedLines());
        assertEquals(1, metrics.getModifiedLines());
        assertEquals(13, metrics.getPatchSize());
        assertEquals(6, metrics.getNbChunks());
        assertEquals(19, metrics.getSpreadingAllLines());
        assertEquals(9, metrics.getSpreadingCodeOnly());
    }

    @Test
    public void closure24() {
        Config config = Config.getInstance();
        config.setProject("Closure");
        config.setBugId("24");

        String buggySourcePath = MetricExtractorTest.class.getResource("/closure_24/buggy-version").getPath();
        String fixedSourcePath = MetricExtractorTest.class.getResource("/closure_24/fixed-version").getPath();
        String diffPath = MetricExtractorTest.class.getResource("/closure_24/closure_24.diff").getPath();
        MetricExtractor extractor = new MetricExtractor(buggySourcePath, fixedSourcePath, diffPath);
        Metrics metrics = extractor.extract();
        assertNotNull(metrics);
        assertEquals(1, metrics.getNbFiles());
        assertEquals(2, metrics.getAddedLines());
        assertEquals(1, metrics.getRemovedLines());
        assertEquals(2, metrics.getModifiedLines());
        assertEquals(5, metrics.getPatchSize());
        assertEquals(4, metrics.getNbChunks());
        assertEquals(15, metrics.getSpreadingAllLines());
        assertEquals(9, metrics.getSpreadingCodeOnly());
    }

    @Test
    public void closure76() {
        Config config = Config.getInstance();
        config.setProject("Closure");
        config.setBugId("76");

        String buggySourcePath = MetricExtractorTest.class.getResource("/closure_76/buggy-version").getPath();
        String fixedSourcePath = MetricExtractorTest.class.getResource("/closure_76/fixed-version").getPath();
        String diffPath = MetricExtractorTest.class.getResource("/closure_76/closure_76.diff").getPath();
        MetricExtractor extractor = new MetricExtractor(buggySourcePath, fixedSourcePath, diffPath);
        Metrics metrics = extractor.extract();
        assertNotNull(metrics);
        assertEquals(1, metrics.getNbFiles());
        assertEquals(37, metrics.getAddedLines());
        assertEquals(6, metrics.getRemovedLines());
        assertEquals(0, metrics.getModifiedLines());
        assertEquals(43, metrics.getPatchSize());
        assertEquals(11, metrics.getNbChunks());
        assertEquals(73, metrics.getSpreadingAllLines());
        assertEquals(48, metrics.getSpreadingCodeOnly());
    }

    @Test
    public void math4() {
        Config config = Config.getInstance();
        config.setProject("Math");
        config.setBugId("4");

        String buggySourcePath = MetricExtractorTest.class.getResource("/math_4/buggy-version").getPath();
        String fixedSourcePath = MetricExtractorTest.class.getResource("/math_4/fixed-version").getPath();
        String diffPath = MetricExtractorTest.class.getResource("/math_4/math_4.diff").getPath();
        MetricExtractor extractor = new MetricExtractor(buggySourcePath, fixedSourcePath, diffPath);
        Metrics metrics = extractor.extract();
        assertNotNull(metrics);
        assertEquals(2, metrics.getNbFiles());
        assertEquals(6, metrics.getAddedLines());
        assertEquals(0, metrics.getRemovedLines());
        assertEquals(0, metrics.getModifiedLines());
        assertEquals(6, metrics.getPatchSize());
        assertEquals(2, metrics.getNbChunks());
        assertEquals(0, metrics.getSpreadingAllLines());
        assertEquals(0, metrics.getSpreadingCodeOnly());
    }

    @Test
    public void time12() {
        Config config = Config.getInstance();
        config.setProject("Time");
        config.setBugId("12");

        String buggySourcePath = MetricExtractorTest.class.getResource("/time_12/buggy-version").getPath();
        String fixedSourcePath = MetricExtractorTest.class.getResource("/time_12/fixed-version").getPath();
        String diffPath = MetricExtractorTest.class.getResource("/time_12/time_12.diff").getPath();
        MetricExtractor extractor = new MetricExtractor(buggySourcePath, fixedSourcePath, diffPath);
        Metrics metrics = extractor.extract();
        assertNotNull(metrics);
        assertEquals(2, metrics.getNbFiles());
        assertEquals(12, metrics.getAddedLines());
        assertEquals(0, metrics.getRemovedLines());
        assertEquals(2, metrics.getModifiedLines());
        assertEquals(14, metrics.getPatchSize());
        assertEquals(8, metrics.getNbChunks());
        assertEquals(70, metrics.getSpreadingAllLines());
        assertEquals(26, metrics.getSpreadingCodeOnly());
    }

    @Test
    public void time23() {
        Config config = Config.getInstance();
        config.setProject("Time");
        config.setBugId("23");

        String buggySourcePath = MetricExtractorTest.class.getResource("/time_23/buggy-version").getPath();
        String fixedSourcePath = MetricExtractorTest.class.getResource("/time_23/fixed-version").getPath();
        String diffPath = MetricExtractorTest.class.getResource("/time_23/time_23.diff").getPath();
        MetricExtractor extractor = new MetricExtractor(buggySourcePath, fixedSourcePath, diffPath);
        Metrics metrics = extractor.extract();
        assertNotNull(metrics);
        assertEquals(1, metrics.getNbFiles());
        assertEquals(5, metrics.getAddedLines());
        assertEquals(4, metrics.getRemovedLines());
        assertEquals(4, metrics.getModifiedLines());
        assertEquals(13, metrics.getPatchSize());
        assertEquals(8, metrics.getNbChunks());
        assertEquals(17, metrics.getSpreadingAllLines());
        assertEquals(17, metrics.getSpreadingCodeOnly());
    }

}
