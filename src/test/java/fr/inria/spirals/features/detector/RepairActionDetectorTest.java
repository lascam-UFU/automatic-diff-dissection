package fr.inria.spirals.features.detector;

import fr.inria.spirals.entities.RepairActions;
import fr.inria.spirals.utils.TestUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fermadeiral
 */
public class RepairActionDetectorTest {

    @Test
    public void chart1() {
        TestUtils.setupConfig("Chart 1");

        RepairActionDetector detector = new RepairActionDetector();
        RepairActions repairActions = detector.detect();

        Assert.assertTrue(repairActions.getMetric("condExpMod") > 0);
    }

    @Test
    public void chart4() {
        TestUtils.setupConfig("Chart 4");

        RepairActionDetector detector = new RepairActionDetector();
        RepairActions repairActions = detector.detect();

        Assert.assertTrue(repairActions.getMetric("condBranIfAdd") > 0);
    }

    @Test
    public void chart18() {
        TestUtils.setupConfig("Chart 18");

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
        TestUtils.setupConfig("Closure 24");

        RepairActionDetector detector = new RepairActionDetector();
        RepairActions repairActions = detector.detect();

        Assert.assertTrue(repairActions.getMetric("condBranIfElseAdd") > 0);
        Assert.assertTrue(repairActions.getMetric("condBranRem") > 0);
        Assert.assertTrue(repairActions.getMetric("condExpExpand") > 0);
        Assert.assertTrue(repairActions.getMetric("mcAdd") > 0);

    }

    @Test
    public void closure76() {
        TestUtils.setupConfig("Closure 76");

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
        TestUtils.setupConfig("Math 4");

        RepairActionDetector detector = new RepairActionDetector();
        RepairActions repairActions = detector.detect();

        Assert.assertTrue(repairActions.getMetric("condBranIfAdd") > 0);
        Assert.assertTrue(repairActions.getMetric("retBranchAdd") > 0);
    }

    @Test
    public void time12() {
        TestUtils.setupConfig("Time 12");

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
        TestUtils.setupConfig("Time 23");

        RepairActionDetector detector = new RepairActionDetector();
        RepairActions repairActions = detector.detect();

        Assert.assertTrue(repairActions.getMetric("mcAdd") > 0);
        Assert.assertTrue(repairActions.getMetric("mcParValChange") > 0);
    }

}
