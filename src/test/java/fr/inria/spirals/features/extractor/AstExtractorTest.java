package fr.inria.spirals.features.extractor;

import fr.inria.spirals.entities.RepairActions;
import fr.inria.spirals.main.Config;
import org.junit.Test;

/**
 * Created by tdurieux
 */
public class AstExtractorTest {

    @Test
    public void chart1() {
        Config config = Config.getInstance();
        config.setProject("Chart");
        config.setBugId("1");

        String buggySourcePath = AstExtractorTest.class.getResource("/chart_1/buggy-version").getPath();
        String diffPath = AstExtractorTest.class.getResource("/chart_1/chart_1.diff").getPath();
        AstExtractor extractor = new AstExtractor(buggySourcePath, diffPath);
        extractor.extract();
    }

    @Test
    public void chart4() {
        Config config = Config.getInstance();
        config.setProject("Chart");
        config.setBugId("4");

        String buggySourcePath = AstExtractorTest.class.getResource("/chart_4/buggy-version").getPath();
        String diffPath = AstExtractorTest.class.getResource("/chart_4/chart_4.diff").getPath();
        AstExtractor extractor = new AstExtractor(buggySourcePath, diffPath);
        extractor.extract();
    }

    @Test
    public void chart18() {
        Config config = Config.getInstance();
        config.setProject("Chart");
        config.setBugId("18");

        String buggySourcePath = AstExtractorTest.class.getResource("/chart_18/buggy-version").getPath();
        String diffPath = AstExtractorTest.class.getResource("/chart_18/chart_18.diff").getPath();
        AstExtractor extractor = new AstExtractor(buggySourcePath, diffPath);
        extractor.extract();
    }

    @Test
    public void closure24() {
        Config config = Config.getInstance();
        config.setProject("Closure");
        config.setBugId("24");

        String buggySourcePath = AstExtractorTest.class.getResource("/closure_24/buggy-version").getPath();
        String diffPath = AstExtractorTest.class.getResource("/closure_24/closure_24.diff").getPath();
        AstExtractor extractor = new AstExtractor(buggySourcePath, diffPath);
        extractor.extract();
    }

    @Test
    public void closure76() {
        Config config = Config.getInstance();
        config.setProject("Closure");
        config.setBugId("76");

        String buggySourcePath = AstExtractorTest.class.getResource("/closure_76/buggy-version").getPath();
        String diffPath = AstExtractorTest.class.getResource("/closure_76/closure_76.diff").getPath();
        AstExtractor extractor = new AstExtractor(buggySourcePath, diffPath);
        extractor.extract();
    }

    @Test
    public void math4() {
        Config config = Config.getInstance();
        config.setProject("Math");
        config.setBugId("4");

        String buggySourcePath = AstExtractorTest.class.getResource("/math_4/buggy-version").getPath();
        String diffPath = AstExtractorTest.class.getResource("/math_4/math_4.diff").getPath();
        AstExtractor extractor = new AstExtractor(buggySourcePath, diffPath);
        extractor.extract();
    }

    @Test
    public void time12() {
        Config config = Config.getInstance();
        config.setProject("Time");
        config.setBugId("12");

        String buggySourcePath = AstExtractorTest.class.getResource("/time_12/buggy-version").getPath();
        String diffPath = AstExtractorTest.class.getResource("/time_12/time_12.diff").getPath();
        AstExtractor extractor = new AstExtractor(buggySourcePath, diffPath);
        extractor.extract();
    }

    @Test
    public void time23() {
        Config config = Config.getInstance();
        config.setProject("Time");
        config.setBugId("23");

        String buggySourcePath = AstExtractorTest.class.getResource("/time_23/buggy-version").getPath();
        String diffPath = AstExtractorTest.class.getResource("/time_23/time_23.diff").getPath();
        AstExtractor extractor = new AstExtractor(buggySourcePath, diffPath);
        extractor.extract();
    }

}
