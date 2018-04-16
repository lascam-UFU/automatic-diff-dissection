package fr.inria.spirals.features;

import fr.inria.spirals.features.extractor.PositionExtractor;
import org.junit.Test;

public class PositionExtractorTest {

	@Test
	public void chart1() {
		String buggySourcePath = PositionExtractorTest.class.getResource("/chart_1/buggy-version").getPath();
		String fixedSourcePath = PositionExtractorTest.class.getResource("/chart_1/fixed-version").getPath();
		String diffPath = PositionExtractorTest.class.getResource("/chart_1/chart_1.diff").getPath();
		PositionExtractor extractor = new PositionExtractor(buggySourcePath, fixedSourcePath, diffPath);
		extractor.setProject("Chart");
		extractor.setBugId("1");
		extractor.getLimitOfPatch();
		extractor.nbChucks();
		extractor.countAddRemoveModify();
	}

	@Test
	public void chart4() {
		String buggySourcePath = PositionExtractorTest.class.getResource("/chart_4/buggy-version").getPath();
		String fixedSourcePath = PositionExtractorTest.class.getResource("/chart_4/fixed-version").getPath();
		String diffPath = PositionExtractorTest.class.getResource("/chart_4/chart_4.diff").getPath();
		PositionExtractor extractor = new PositionExtractor(buggySourcePath, fixedSourcePath, diffPath);
		extractor.setProject("Chart");
		extractor.setBugId("4");
		extractor.getLimitOfPatch();
		extractor.nbChucks();
		extractor.countAddRemoveModify();
	}

	@Test
	public void chart18() {
		String buggySourcePath = PositionExtractorTest.class.getResource("/chart_18/buggy-version").getPath();
		String fixedSourcePath = PositionExtractorTest.class.getResource("/chart_18/fixed-version").getPath();
		String diffPath = PositionExtractorTest.class.getResource("/chart_18/chart_18.diff").getPath();
		PositionExtractor extractor = new PositionExtractor(buggySourcePath, fixedSourcePath, diffPath);
		extractor.setProject("Chart");
		extractor.setBugId("18");
		extractor.getLimitOfPatch();
		extractor.nbChucks();
		extractor.countAddRemoveModify();
	}

	@Test
	public void closure24() {
		String buggySourcePath = PositionExtractorTest.class.getResource("/closure_24/buggy-version").getPath();
		String fixedSourcePath = PositionExtractorTest.class.getResource("/closure_24/fixed-version").getPath();
		String diffPath = PositionExtractorTest.class.getResource("/closure_24/closure_24.diff").getPath();
		PositionExtractor extractor = new PositionExtractor(buggySourcePath, fixedSourcePath, diffPath);
		extractor.setProject("Closure");
		extractor.setBugId("24");
		extractor.getLimitOfPatch();
		extractor.nbChucks();
		extractor.countAddRemoveModify();
	}

	@Test
	public void closure76() {
		String buggySourcePath = PositionExtractorTest.class.getResource("/closure_76/buggy-version").getPath();
		String fixedSourcePath = PositionExtractorTest.class.getResource("/closure_76/fixed-version").getPath();
		String diffPath = PositionExtractorTest.class.getResource("/closure_76/closure_76.diff").getPath();
		PositionExtractor extractor = new PositionExtractor(buggySourcePath, fixedSourcePath, diffPath);
		extractor.setProject("Closure");
		extractor.setBugId("76");
		extractor.getLimitOfPatch();
		extractor.nbChucks();
		extractor.countAddRemoveModify();
	}

	@Test
	public void math4() {
		String buggySourcePath = PositionExtractorTest.class.getResource("/math_4/buggy-version").getPath();
		String fixedSourcePath = PositionExtractorTest.class.getResource("/math_4/fixed-version").getPath();
		String diffPath = PositionExtractorTest.class.getResource("/math_4/math_4.diff").getPath();
		PositionExtractor extractor = new PositionExtractor(buggySourcePath, fixedSourcePath, diffPath);
		extractor.setProject("Math");
		extractor.setBugId("4");
		extractor.getLimitOfPatch();
		extractor.nbChucks();
		extractor.countAddRemoveModify();
	}

	@Test
	public void time12() {
		String buggySourcePath = PositionExtractorTest.class.getResource("/time_12/buggy-version").getPath();
		String fixedSourcePath = PositionExtractorTest.class.getResource("/time_12/fixed-version").getPath();
		String diffPath = PositionExtractorTest.class.getResource("/time_12/time_12.diff").getPath();
		PositionExtractor extractor = new PositionExtractor(buggySourcePath, fixedSourcePath, diffPath);
		extractor.setProject("Time");
		extractor.setBugId("12");
		extractor.getLimitOfPatch();
		extractor.nbChucks();
		extractor.countAddRemoveModify();
	}

	@Test
	public void time23() {
		String buggySourcePath = PositionExtractorTest.class.getResource("/time_23/buggy-version").getPath();
		String fixedSourcePath = PositionExtractorTest.class.getResource("/time_23/fixed-version").getPath();
		String diffPath = PositionExtractorTest.class.getResource("/time_23/time_23.diff").getPath();
		PositionExtractor extractor = new PositionExtractor(buggySourcePath, fixedSourcePath, diffPath);
		extractor.setProject("Time");
		extractor.setBugId("23");
		extractor.getLimitOfPatch();
		extractor.nbChucks();
		extractor.countAddRemoveModify();
	}

}
