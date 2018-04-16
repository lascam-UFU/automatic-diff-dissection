package fr.inria.spirals.features;

import fr.inria.spirals.features.extractor.AstExtractor;
import org.junit.Test;

public class AstExtractorTest {

	@Test
	public void chart1() {
		String buggySourcePath = PositionExtractorTest.class.getResource("/chart_1/buggy-version").getPath();
		String diffPath = AstExtractorTest.class.getResource("/chart_1/chart_1.diff").getPath();
		AstExtractor extractor = new AstExtractor(buggySourcePath, diffPath);
		extractor.setProject("Chart");
		extractor.setBugId("1");
		extractor.extract();
	}

	@Test
	public void chart4() {
		String buggySourcePath = AstExtractorTest.class.getResource("/chart_4/buggy-version").getPath();
		String diffPath = AstExtractorTest.class.getResource("/chart_4/chart_4.diff").getPath();
		AstExtractor extractor = new AstExtractor(buggySourcePath, diffPath);
		extractor.setProject("Chart");
		extractor.setBugId("4");
		extractor.extract();
	}

	@Test
	public void chart18() {
		String buggySourcePath = AstExtractorTest.class.getResource("/chart_18/buggy-version").getPath();
		String diffPath = AstExtractorTest.class.getResource("/chart_18/chart_18.diff").getPath();
		AstExtractor extractor = new AstExtractor(buggySourcePath, diffPath);
		extractor.setProject("Chart");
		extractor.setBugId("18");
		extractor.extract();
	}

	@Test
	public void closure24() {
		String buggySourcePath = AstExtractorTest.class.getResource("/closure_24/buggy-version").getPath();
		String diffPath = AstExtractorTest.class.getResource("/closure_24/closure_24.diff").getPath();
		AstExtractor extractor = new AstExtractor(buggySourcePath, diffPath);
		extractor.setProject("Closure");
		extractor.setBugId("24");
		extractor.extract();
	}

	@Test
	public void closure76() {
		String buggySourcePath = AstExtractorTest.class.getResource("/closure_76/buggy-version").getPath();
		String diffPath = AstExtractorTest.class.getResource("/closure_76/closure_76.diff").getPath();
		AstExtractor extractor = new AstExtractor(buggySourcePath, diffPath);
		extractor.setProject("Closure");
		extractor.setBugId("76");
		extractor.extract();
	}

	@Test
	public void math4() {
		String buggySourcePath = AstExtractorTest.class.getResource("/math_4/buggy-version").getPath();
		String diffPath = AstExtractorTest.class.getResource("/math_4/math_4.diff").getPath();
		AstExtractor extractor = new AstExtractor(buggySourcePath, diffPath);
		extractor.setProject("Math");
		extractor.setBugId("4");
		extractor.extract();
	}

	@Test
	public void time12() {
		String buggySourcePath = AstExtractorTest.class.getResource("/time_12/buggy-version").getPath();
		String diffPath = AstExtractorTest.class.getResource("/time_12/time_12.diff").getPath();
		AstExtractor extractor = new AstExtractor(buggySourcePath, diffPath);
		extractor.setProject("Time");
		extractor.setBugId("12");
		extractor.extract();
	}

	@Test
	public void time23() {
		String buggySourcePath = AstExtractorTest.class.getResource("/time_23/buggy-version").getPath();
		String diffPath = AstExtractorTest.class.getResource("/time_23/time_23.diff").getPath();
		AstExtractor extractor = new AstExtractor(buggySourcePath, diffPath);
		extractor.setProject("Time");
		extractor.setBugId("23");
		extractor.extract();
	}

}
