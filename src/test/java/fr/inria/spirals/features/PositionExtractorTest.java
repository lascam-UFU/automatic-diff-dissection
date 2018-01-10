package fr.inria.spirals.features;

import fr.inria.spirals.features.extractor.PositionExtractor;
import org.junit.Ignore;
import org.junit.Test;

public class PositionExtractorTest {

	@Test
	@Ignore
	public void closure24() {
		PositionExtractor extractor = new PositionExtractor(
				"/mnt/secondary/projects/closure/closure_1/src/",
				"/mnt/secondary/projects_fix/closure/closure_1/src/",
				PositionExtractorTest.class.getResource("/closure_24.diff").getPath());
		extractor.setBugId("24");
		extractor.setProject("Closure");
		extractor.getLimitOfPatch();

		extractor.nbChucks();

		extractor.countAddRemoveModify();

		extractor.spreading();
	}

	@Test
	@Ignore
	public void math4() {
		PositionExtractor extractor = new PositionExtractor(
				"/mnt/secondary/projects/math/math_4/src/main/java/",
				"/mnt/secondary/projects_fix/math/math_4/src/main/java/",
				PositionExtractorTest.class.getResource("/math_4.diff").getPath());
		extractor.setBugId("4");
		extractor.setProject("Math");
		extractor.getLimitOfPatch();

		extractor.nbChucks();

		extractor.countAddRemoveModify();

		extractor.spreading();
	}

	@Test
	@Ignore
	public void time12() {
		PositionExtractor extractor = new PositionExtractor(
				"/mnt/secondary/projects/time/time_12/src/main/java/",
				"/mnt/secondary/projects_fix/time/time_12/src/main/java/",
				PositionExtractorTest.class.getResource("/time_12.diff").getPath());
		extractor.setBugId("12");
		extractor.setProject("Time");
		extractor.getLimitOfPatch();

		extractor.nbChucks();

		extractor.countAddRemoveModify();

		extractor.spreading();
	}

	@Test
	@Ignore
	public void time23() {
		PositionExtractor extractor = new PositionExtractor(
				"/mnt/secondary/projects/time/time_23/src/main/java/",
				"/mnt/secondary/projects_fix/time/time_23/src/main/java/",
				PositionExtractorTest.class.getResource("/time_23.diff").getPath());
		extractor.setBugId("23");
		extractor.setProject("Time");
		extractor.getLimitOfPatch();

		extractor.nbChucks();

		extractor.countAddRemoveModify();

		extractor.spreading2();
	}


	@Test
	@Ignore
	public void chart1() {
		PositionExtractor extractor = new PositionExtractor(
				"/mnt/secondary/projects/chart/chart_1/source/",
				"/mnt/secondary/projects_fix/chart/chart_1/source/",
				PositionExtractorTest.class.getResource("/chart_1.diff").getPath());
		extractor.setBugId("1");
		extractor.setProject("Chart");
		extractor.getLimitOfPatch();

		extractor.nbChucks();

		extractor.countAddRemoveModify();
	}

	@Test
	@Ignore
	public void chart4() {
		PositionExtractor extractor = new PositionExtractor(
				"/mnt/secondary/projects/chart/chart_4/source/",
				"/mnt/secondary/projects_fix/chart/chart_4/source/",
				PositionExtractorTest.class.getResource("/chart_4.diff").getPath());
		extractor.setBugId("4");
		extractor.setProject("Chart");

		extractor.getLimitOfPatch();

		extractor.nbChucks();

		extractor.countAddRemoveModify();
		extractor.spreading2();
	}


	@Test
	@Ignore
	public void chart18() {
		PositionExtractor extractor = new PositionExtractor(
				"/mnt/secondary/projects/chart/chart_18/source/",
				"/mnt/secondary/projects_fix/chart/chart_18/source/",
				PositionExtractorTest.class.getResource("/chart_18.diff").getPath());
		extractor.setBugId("18");
		extractor.setProject("Chart");

		extractor.getLimitOfPatch();

		extractor.nbChucks();

		extractor.countAddRemoveModify();
		extractor.spreading2();
	}

	@Test
	@Ignore
	public void closure76() {
		PositionExtractor extractor = new PositionExtractor(
				"/mnt/secondary/projects/closure/closure_76/src/",
				"/mnt/secondary/projects_fix/closure/closure_76/src/",
				PositionExtractorTest.class.getResource("/closure_76.diff").getPath());
		extractor.setBugId("76");
		extractor.setProject("Closure");

		extractor.getLimitOfPatch();

		extractor.nbChucks();

		extractor.countAddRemoveModify();
		extractor.spreading2();
	}
}