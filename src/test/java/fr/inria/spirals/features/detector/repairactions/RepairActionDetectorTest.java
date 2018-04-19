package fr.inria.spirals.features.detector.repairactions;

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

        Assert.assertTrue(repairActions.getFeatureCounter("condExpMod") > 0);
    }

    @Test
    public void chart4() {
        TestUtils.setupConfig("Chart 4");

        RepairActionDetector detector = new RepairActionDetector();
        RepairActions repairActions = detector.detect();

        Assert.assertTrue(repairActions.getFeatureCounter("condBranIfAdd") > 0);
    }

    @Test
    public void chart18() {
        TestUtils.setupConfig("Chart 18");

        RepairActionDetector detector = new RepairActionDetector();
        RepairActions repairActions = detector.detect();

        Assert.assertTrue(repairActions.getFeatureCounter("assignAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("condBranIfAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("condBranRem") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("mcAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("mcRem") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("objInstAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("exThrowsAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("retRem") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("varAdd") > 0);
    }

    @Test
    public void closure24() {
        TestUtils.setupConfig("Closure 24");

        RepairActionDetector detector = new RepairActionDetector();
        RepairActions repairActions = detector.detect();

        Assert.assertTrue(repairActions.getFeatureCounter("condBranIfElseAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("condBranRem") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("condExpExpand") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("mcAdd") > 0);

    }

    @Test
    public void closure76() {
        TestUtils.setupConfig("Closure 76");

        RepairActionDetector detector = new RepairActionDetector();
        RepairActions repairActions = detector.detect();

        Assert.assertTrue(repairActions.getFeatureCounter("assignAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("condBranIfAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("condBranIfElseAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("condBranRem") > 0);
        //Assert.assertTrue(repairActions.getFeatureCounter("condExpMod") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("mcAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("mcRem") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("retBranchAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("varAdd") > 0);
    }

    @Test
    public void math4() {
        TestUtils.setupConfig("Math 4");

        RepairActionDetector detector = new RepairActionDetector();
        RepairActions repairActions = detector.detect();

        Assert.assertTrue(repairActions.getFeatureCounter("condBranIfAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("retBranchAdd") > 0);
    }

    @Test
    public void time12() {
        TestUtils.setupConfig("Time 12");

        RepairActionDetector detector = new RepairActionDetector();
        RepairActions repairActions = detector.detect();

        Assert.assertTrue(repairActions.getFeatureCounter("assignAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("condBranIfAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("condBranIfElseAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("mcAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("objInstAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("retBranchAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("varAdd") > 0);
        //Assert.assertTrue(repairActions.getFeatureCounter("mcParValChange") > 0);

    }

    @Test
    public void time23() {
        TestUtils.setupConfig("Time 23");

        RepairActionDetector detector = new RepairActionDetector();
        RepairActions repairActions = detector.detect();

        Assert.assertTrue(repairActions.getFeatureCounter("mcAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("mcParValChange") > 0);
    }

}
