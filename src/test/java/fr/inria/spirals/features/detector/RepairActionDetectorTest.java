package fr.inria.spirals.features.detector;

import fr.inria.spirals.entities.RepairActions;
import fr.inria.spirals.main.Config;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fermadeiral
 */
public class RepairActionDetectorTest {

    @Test
    public void chart1() {
        Config config = Config.getInstance();
        config.setBugId("Chart 1");
        config.setBuggySourceDirectoryPath(RepairActionDetectorTest.class.getResource("/chart_1/buggy-version").getPath());
        config.setDiffPath(RepairActionDetectorTest.class.getResource("/chart_1/chart_1.diff").getPath());

        RepairActionDetector detector = new RepairActionDetector();
        RepairActions repairActions = detector.detect();

        Assert.assertTrue(repairActions.getMetric("condExpMod") > 0);
    }

    @Test
    public void chart4() {
        Config config = Config.getInstance();
        config.setBugId("Chart 4");
        config.setBuggySourceDirectoryPath(RepairActionDetectorTest.class.getResource("/chart_4/buggy-version").getPath());
        config.setDiffPath(RepairActionDetectorTest.class.getResource("/chart_4/chart_4.diff").getPath());

        RepairActionDetector detector = new RepairActionDetector();
        RepairActions repairActions = detector.detect();

        Assert.assertTrue(repairActions.getMetric("condBranIfAdd") > 0);
    }

    @Test
    public void chart18() {
        Config config = Config.getInstance();
        config.setBugId("Chart 18");
        config.setBuggySourceDirectoryPath(RepairActionDetectorTest.class.getResource("/chart_18/buggy-version").getPath());
        config.setDiffPath(RepairActionDetectorTest.class.getResource("/chart_18/chart_18.diff").getPath());

        RepairActionDetector detector = new RepairActionDetector();
        RepairActions repairActions = detector.detect();

        Assert.assertTrue(repairActions.getMetric("assignAdd") > 0);
        Assert.assertTrue(repairActions.getMetric("condBranIfAdd") > 0);
        Assert.assertTrue(repairActions.getMetric("condBranRem") > 0);
        Assert.assertTrue(repairActions.getMetric("mcAdd") > 0);
        Assert.assertTrue(repairActions.getMetric("mcRem") > 0);
        Assert.assertTrue(repairActions.getMetric("objInstAdd") > 0);
        Assert.assertTrue(repairActions.getMetric("exThrowsAdd") > 0);
        Assert.assertTrue(repairActions.getMetric("retRem") > 0);
        Assert.assertTrue(repairActions.getMetric("varAdd") > 0);
    }

    @Test
    public void closure24() {
        Config config = Config.getInstance();
        config.setBugId("Closure 24");
        config.setBuggySourceDirectoryPath(RepairActionDetectorTest.class.getResource("/closure_24/buggy-version").getPath());
        config.setDiffPath(RepairActionDetectorTest.class.getResource("/closure_24/closure_24.diff").getPath());

        RepairActionDetector detector = new RepairActionDetector();
        RepairActions repairActions = detector.detect();

        Assert.assertTrue(repairActions.getMetric("condBranIfElseAdd") > 0);
        Assert.assertTrue(repairActions.getMetric("condBranRem") > 0);
        Assert.assertTrue(repairActions.getMetric("condExpExpand") > 0);
        Assert.assertTrue(repairActions.getMetric("mcAdd") > 0);

    }

    @Test
    public void closure76() {
        Config config = Config.getInstance();
        config.setBugId("Closure 76");
        config.setBuggySourceDirectoryPath(RepairActionDetectorTest.class.getResource("/closure_76/buggy-version").getPath());
        config.setDiffPath(RepairActionDetectorTest.class.getResource("/closure_76/closure_76.diff").getPath());

        RepairActionDetector detector = new RepairActionDetector();
        RepairActions repairActions = detector.detect();

        Assert.assertTrue(repairActions.getMetric("assignAdd") > 0);
        Assert.assertTrue(repairActions.getMetric("condBranIfAdd") > 0);
        Assert.assertTrue(repairActions.getMetric("condBranIfElseAdd") > 0);
        Assert.assertTrue(repairActions.getMetric("condBranRem") > 0);
        //Assert.assertTrue(repairActions.getMetric("condExpMod") > 0);
        Assert.assertTrue(repairActions.getMetric("mcAdd") > 0);
        Assert.assertTrue(repairActions.getMetric("mcRem") > 0);
        Assert.assertTrue(repairActions.getMetric("retBranchAdd") > 0);
        Assert.assertTrue(repairActions.getMetric("varAdd") > 0);
    }

    @Test
    public void math4() {
        Config config = Config.getInstance();
        config.setBugId("Math 4");
        config.setBuggySourceDirectoryPath(RepairActionDetectorTest.class.getResource("/math_4/buggy-version").getPath());
        config.setDiffPath(RepairActionDetectorTest.class.getResource("/math_4/math_4.diff").getPath());

        RepairActionDetector detector = new RepairActionDetector();
        RepairActions repairActions = detector.detect();

        Assert.assertTrue(repairActions.getMetric("condBranIfAdd") > 0);
        Assert.assertTrue(repairActions.getMetric("retBranchAdd") > 0);
    }

    @Test
    public void time12() {
        Config config = Config.getInstance();
        config.setBugId("Time 12");
        config.setBuggySourceDirectoryPath(RepairActionDetectorTest.class.getResource("/time_12/buggy-version").getPath());
        config.setDiffPath(RepairActionDetectorTest.class.getResource("/time_12/time_12.diff").getPath());

        RepairActionDetector detector = new RepairActionDetector();
        RepairActions repairActions = detector.detect();

        Assert.assertTrue(repairActions.getMetric("assignAdd") > 0);
        Assert.assertTrue(repairActions.getMetric("condBranIfAdd") > 0);
        Assert.assertTrue(repairActions.getMetric("condBranIfElseAdd") > 0);
        Assert.assertTrue(repairActions.getMetric("mcAdd") > 0);
        Assert.assertTrue(repairActions.getMetric("objInstAdd") > 0);
        Assert.assertTrue(repairActions.getMetric("retBranchAdd") > 0);
        Assert.assertTrue(repairActions.getMetric("varAdd") > 0);
        //Assert.assertTrue(repairActions.getMetric("mcParValChange") > 0);

    }

    @Test
    public void time23() {
        Config config = Config.getInstance();
        config.setBugId("Time 23");
        config.setBuggySourceDirectoryPath(RepairActionDetectorTest.class.getResource("/time_23/buggy-version").getPath());
        config.setDiffPath(RepairActionDetectorTest.class.getResource("/time_23/time_23.diff").getPath());

        RepairActionDetector detector = new RepairActionDetector();
        RepairActions repairActions = detector.detect();

        Assert.assertTrue(repairActions.getMetric("mcAdd") > 0);
        Assert.assertTrue(repairActions.getMetric("mcParValChange") > 0);
    }

}
