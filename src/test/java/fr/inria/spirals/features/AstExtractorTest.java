package fr.inria.spirals.features;

import fr.inria.spirals.features.extractor.AstExtractor;
import org.junit.Test;

import static fr.inria.spirals.features.extractor.AstExtractor.CSV_SEPARATOR;

public class AstExtractorTest {

	@Test
	public void camel() {
		AstExtractor extractor = new AstExtractor(
				"/mnt/secondary/bugs-dot-jar/bugs/camel/2e985f9b/",
				"/mnt/secondary/bugs-dot-jar/bugs/camel/2e985f9b/.bugs-dot-jar/developer-patch.diff");
		extractor.setBugId("3276");
		extractor.setProject("camel");
		extractor.extract();
	}


	@Test
	public void chart1() {
		AstExtractor extractor = new AstExtractor(
				"/mnt/secondary/projects/chart/chart_1/source/",
				AstExtractorTest.class.getResource("/chart_1.diff").getPath());
		extractor.setBugId("1");
		extractor.setProject("Chart");
		extractor.extract();
	}

	@Test
	public void chart4() {
		AstExtractor extractor = new AstExtractor(
				"/mnt/secondary/projects/chart/chart_4/source/",
				AstExtractorTest.class.getResource("/chart_4.diff").getPath());
		StringBuilder sb = new StringBuilder();
		sb.append("Project").append(CSV_SEPARATOR);
		sb.append("Bug ID").append(CSV_SEPARATOR);

		sb.append("# Files").append(CSV_SEPARATOR);

		sb.append("# Variables").append(CSV_SEPARATOR);
		sb.append("# variable accesses").append(CSV_SEPARATOR);
		sb.append("# invocations").append(CSV_SEPARATOR);
		sb.append("# external invocations").append(CSV_SEPARATOR);
		sb.append("# if").append(CSV_SEPARATOR);
		sb.append("# loops").append(CSV_SEPARATOR);
		sb.append("# types").append(CSV_SEPARATOR);
		sb.append("# comments").append(CSV_SEPARATOR);
		sb.append("# assignments").append(CSV_SEPARATOR);
		sb.append("# binary expressions").append(CSV_SEPARATOR);
		sb.append("# unary expressions").append(CSV_SEPARATOR);
		sb.append("# instantiations").append(CSV_SEPARATOR);
		sb.append("# try/catch").append(CSV_SEPARATOR);
		sb.append("# literals").append(CSV_SEPARATOR);

		sb.append("# throws").append(CSV_SEPARATOR);
		sb.append("# returns").append(CSV_SEPARATOR);
		sb.append("# breaks").append(CSV_SEPARATOR);
		sb.append("# continues").append(CSV_SEPARATOR);

		sb.append("# Variables").append(CSV_SEPARATOR);
		sb.append("# variable accesses").append(CSV_SEPARATOR);
		sb.append("# invocations").append(CSV_SEPARATOR);
		sb.append("# external invocations").append(CSV_SEPARATOR);
		sb.append("# if").append(CSV_SEPARATOR);
		sb.append("# loops").append(CSV_SEPARATOR);
		sb.append("# types").append(CSV_SEPARATOR);
		sb.append("# comments").append(CSV_SEPARATOR);
		sb.append("# assignments").append(CSV_SEPARATOR);
		sb.append("# binary expressions").append(CSV_SEPARATOR);
		sb.append("# unary expressions").append(CSV_SEPARATOR);
		sb.append("# instantiations").append(CSV_SEPARATOR);
		sb.append("# try/catch").append(CSV_SEPARATOR);
		sb.append("# literals").append(CSV_SEPARATOR);

		sb.append("# throws").append(CSV_SEPARATOR);
		sb.append("# returns").append(CSV_SEPARATOR);
		sb.append("# breaks").append(CSV_SEPARATOR);
		sb.append("# continues").append(CSV_SEPARATOR);

		extractor.extract();

		System.out.println(sb.toString());
	}

	@Test
	public void chart18() {
		AstExtractor extractor = new AstExtractor(
				"/mnt/secondary/",
				AstExtractorTest.class.getResource("/chart_18.diff").getPath());
		extractor.setBugId("18");
		extractor.setProject("Chart");
		extractor.extract();
	}


	@Test
	public void closure24() {
		AstExtractor extractor = new AstExtractor(
				"/mnt/secondary/projects/closure/closure_24/src/",
				AstExtractorTest.class.getResource("/closure_24.diff").getPath());
		extractor.setBugId("24");
		extractor.setProject("closure");
		extractor.extract();
	}

	@Test
	public void closure76() {
		AstExtractor extractor = new AstExtractor(
				"/mnt/secondary/projects/closure/closure_76/src/",
				AstExtractorTest.class.getResource("/closure_76.diff").getPath());
		extractor.setBugId("76");
		extractor.setProject("closure");
		extractor.extract();
	}

	@Test
	public void math4() {
		AstExtractor extractor = new AstExtractor(
				"/mnt/secondary",
				AstExtractorTest.class.getResource("/math_4.diff").getPath());
		extractor.setBugId("4");
		extractor.setProject("Math");
		extractor.extract();
	}

	@Test
	public void time12() {
		AstExtractor extractor = new AstExtractor(
				"/mnt/secondary",
				AstExtractorTest.class.getResource("/time_12.diff").getPath());
		extractor.setBugId("12");
		extractor.setProject("Time");
		extractor.extract();
	}

	@Test
	public void time23() {
		AstExtractor extractor = new AstExtractor(
				"/mnt/secondary/",
				AstExtractorTest.class.getResource("/time_23.diff").getPath());
		extractor.setBugId("23");
		extractor.setProject("Time");
		extractor.extract();
	}
}