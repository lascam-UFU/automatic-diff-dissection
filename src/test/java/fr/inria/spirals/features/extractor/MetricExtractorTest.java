package fr.inria.spirals.features.extractor;

import fr.inria.spirals.entities.Metrics;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by tdurieux
 */
public class MetricExtractorTest {

	@Test
	public void chart1() {
		String buggySourcePath = MetricExtractorTest.class.getResource("/chart_1/buggy-version").getPath();
		String fixedSourcePath = MetricExtractorTest.class.getResource("/chart_1/fixed-version").getPath();
		String diffPath = MetricExtractorTest.class.getResource("/chart_1/chart_1.diff").getPath();
		MetricExtractor extractor = new MetricExtractor(buggySourcePath, fixedSourcePath, diffPath);
		extractor.setProject("Chart");
		extractor.setBugId("1");
		Metrics metrics = extractor.extract();
		assertNotNull(metrics);
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
		String buggySourcePath = MetricExtractorTest.class.getResource("/chart_4/buggy-version").getPath();
		String fixedSourcePath = MetricExtractorTest.class.getResource("/chart_4/fixed-version").getPath();
		String diffPath = MetricExtractorTest.class.getResource("/chart_4/chart_4.diff").getPath();
		MetricExtractor extractor = new MetricExtractor(buggySourcePath, fixedSourcePath, diffPath);
		extractor.setProject("Chart");
		extractor.setBugId("4");
		Metrics metrics = extractor.extract();
		assertNotNull(metrics);
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
		String buggySourcePath = MetricExtractorTest.class.getResource("/chart_18/buggy-version").getPath();
		String fixedSourcePath = MetricExtractorTest.class.getResource("/chart_18/fixed-version").getPath();
		String diffPath = MetricExtractorTest.class.getResource("/chart_18/chart_18.diff").getPath();
		MetricExtractor extractor = new MetricExtractor(buggySourcePath, fixedSourcePath, diffPath);
		extractor.setProject("Chart");
		extractor.setBugId("18");
		Metrics metrics = extractor.extract();
		assertNotNull(metrics);
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
		String buggySourcePath = MetricExtractorTest.class.getResource("/closure_24/buggy-version").getPath();
		String fixedSourcePath = MetricExtractorTest.class.getResource("/closure_24/fixed-version").getPath();
		String diffPath = MetricExtractorTest.class.getResource("/closure_24/closure_24.diff").getPath();
		MetricExtractor extractor = new MetricExtractor(buggySourcePath, fixedSourcePath, diffPath);
		extractor.setProject("Closure");
		extractor.setBugId("24");
		Metrics metrics = extractor.extract();
		assertNotNull(metrics);
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
		String buggySourcePath = MetricExtractorTest.class.getResource("/closure_76/buggy-version").getPath();
		String fixedSourcePath = MetricExtractorTest.class.getResource("/closure_76/fixed-version").getPath();
		String diffPath = MetricExtractorTest.class.getResource("/closure_76/closure_76.diff").getPath();
		MetricExtractor extractor = new MetricExtractor(buggySourcePath, fixedSourcePath, diffPath);
		extractor.setProject("Closure");
		extractor.setBugId("76");
		Metrics metrics = extractor.extract();
		assertNotNull(metrics);
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
		String buggySourcePath = MetricExtractorTest.class.getResource("/math_4/buggy-version").getPath();
		String fixedSourcePath = MetricExtractorTest.class.getResource("/math_4/fixed-version").getPath();
		String diffPath = MetricExtractorTest.class.getResource("/math_4/math_4.diff").getPath();
		MetricExtractor extractor = new MetricExtractor(buggySourcePath, fixedSourcePath, diffPath);
		extractor.setProject("Math");
		extractor.setBugId("4");
		Metrics metrics = extractor.extract();
		assertNotNull(metrics);
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
		String buggySourcePath = MetricExtractorTest.class.getResource("/time_12/buggy-version").getPath();
		String fixedSourcePath = MetricExtractorTest.class.getResource("/time_12/fixed-version").getPath();
		String diffPath = MetricExtractorTest.class.getResource("/time_12/time_12.diff").getPath();
		MetricExtractor extractor = new MetricExtractor(buggySourcePath, fixedSourcePath, diffPath);
		extractor.setProject("Time");
		extractor.setBugId("12");
		Metrics metrics = extractor.extract();
		assertNotNull(metrics);
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
		String buggySourcePath = MetricExtractorTest.class.getResource("/time_23/buggy-version").getPath();
		String fixedSourcePath = MetricExtractorTest.class.getResource("/time_23/fixed-version").getPath();
		String diffPath = MetricExtractorTest.class.getResource("/time_23/time_23.diff").getPath();
		MetricExtractor extractor = new MetricExtractor(buggySourcePath, fixedSourcePath, diffPath);
		extractor.setProject("Time");
		extractor.setBugId("23");
		Metrics metrics = extractor.extract();
		assertNotNull(metrics);
		assertEquals(5, metrics.getAddedLines());
		assertEquals(4, metrics.getRemovedLines());
		assertEquals(4, metrics.getModifiedLines());
		assertEquals(13, metrics.getPatchSize());
		assertEquals(8, metrics.getNbChunks());
		assertEquals(17, metrics.getSpreadingAllLines());
		assertEquals(17, metrics.getSpreadingCodeOnly());
	}

}
