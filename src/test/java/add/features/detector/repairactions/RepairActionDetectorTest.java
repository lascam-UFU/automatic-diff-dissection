package add.features.detector.repairactions;

import add.entities.RepairActions;
import add.main.Config;
import add.utils.TestUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fermadeiral
 */
public class RepairActionDetectorTest {

    @Test
    public void chart1() {
        Config config = TestUtils.setupConfig("chart_1");

        RepairActionDetector detector = new RepairActionDetector(config);
        RepairActions repairActions = detector.analyze();

        Assert.assertTrue(repairActions.getFeatureCounter("condExpMod") > 0);
    }

    @Test
    public void chart4() {
        Config config = TestUtils.setupConfig("chart_4");

        RepairActionDetector detector = new RepairActionDetector(config);
        RepairActions repairActions = detector.analyze();

        Assert.assertTrue(repairActions.getFeatureCounter("condBranIfAdd") > 0);
    }

    @Test
    public void chart7() {
        Config config = TestUtils.setupConfig("chart_7");

        RepairActionDetector detector = new RepairActionDetector(config);
        RepairActions repairActions = detector.analyze();

        Assert.assertTrue(repairActions.getFeatureCounter("varReplVar") > 0);
    }

    @Test
    public void chart11() {
        Config config = TestUtils.setupConfig("chart_11");

        RepairActionDetector detector = new RepairActionDetector(config);
        RepairActions repairActions = detector.analyze();

        Assert.assertTrue(repairActions.getFeatureCounter("varReplVar") > 0);
    }

    @Test
    public void chart13() {
        Config config = TestUtils.setupConfig("chart_13");

        RepairActionDetector detector = new RepairActionDetector(config);
        RepairActions repairActions = detector.analyze();

        Assert.assertTrue(repairActions.getFeatureCounter("mcParValChange") > 0);
    }

    @Test
    public void chart18() {
        Config config = TestUtils.setupConfig("chart_18");

        RepairActionDetector detector = new RepairActionDetector(config);
        RepairActions repairActions = detector.analyze();

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
    public void chart21() {
        Config config = TestUtils.setupConfig("chart_21");

        RepairActionDetector detector = new RepairActionDetector(config);
        RepairActions repairActions = detector.analyze();

        Assert.assertTrue(repairActions.getFeatureCounter("condBranElseAdd") > 0);
    }

    @Test
    public void chart22() {
        Config config = TestUtils.setupConfig("chart_22");

        RepairActionDetector detector = new RepairActionDetector(config);
        RepairActions repairActions = detector.analyze();

        Assert.assertTrue(repairActions.getFeatureCounter("retExpChange") > 0);
    }

    @Test
    public void closure13() {
        Config config = TestUtils.setupConfig("closure_13");

        RepairActionDetector detector = new RepairActionDetector(config);
        RepairActions repairActions = detector.analyze();

        Assert.assertTrue(repairActions.getFeatureCounter("mcMove") > 0);
    }

    @Test
    public void closure24() {
        Config config = TestUtils.setupConfig("closure_24");

        RepairActionDetector detector = new RepairActionDetector(config);
        RepairActions repairActions = detector.analyze();

        Assert.assertTrue(repairActions.getFeatureCounter("condBranIfElseAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("condBranRem") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("condExpExpand") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("mcAdd") > 0);

    }

    @Test
    public void closure37() {
        Config config = TestUtils.setupConfig("closure_37");

        RepairActionDetector detector = new RepairActionDetector(config);
        RepairActions repairActions = detector.analyze();
        System.out.println(repairActions);

    }

    @Test
    public void closure76() {
        Config config = TestUtils.setupConfig("closure_76");

        RepairActionDetector detector = new RepairActionDetector(config);
        RepairActions repairActions = detector.analyze();

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
    public void lang29() {
        Config config = TestUtils.setupConfig("lang_29");

        RepairActionDetector detector = new RepairActionDetector(config);
        RepairActions repairActions = detector.analyze();

        Assert.assertTrue(repairActions.getFeatureCounter("mdRetTyChange") > 0);
    }

    @Test
    public void lang30() {
        Config config = TestUtils.setupConfig("lang_30");

        RepairActionDetector detector = new RepairActionDetector(config);
        RepairActions repairActions = detector.analyze();

        Assert.assertTrue(repairActions.getFeatureCounter("mdParTyChange") > 0);
    }

    @Test
    public void lang64() {
        Config config = TestUtils.setupConfig("lang_64");

        RepairActionDetector detector = new RepairActionDetector(config);
        RepairActions repairActions = detector.analyze();

        Assert.assertTrue(repairActions.getFeatureCounter("mdParAdd") == 0);
    }

    @Test
    public void math4() {
        Config config = TestUtils.setupConfig("math_4");

        RepairActionDetector detector = new RepairActionDetector(config);
        RepairActions repairActions = detector.analyze();

        Assert.assertTrue(repairActions.getFeatureCounter("condBranIfAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("retBranchAdd") > 0);
    }

    @Test
    public void mockito5() {
        Config config = TestUtils.setupConfig("mockito_5");

        RepairActionDetector detector = new RepairActionDetector(config);
        RepairActions repairActions = detector.analyze();

        Assert.assertTrue(repairActions.getFeatureCounter("varTyChange") > 0);
    }

    @Test
    public void mockito27() {
        Config config = TestUtils.setupConfig("mockito_27");

        RepairActionDetector detector = new RepairActionDetector(config);
        RepairActions repairActions = detector.analyze();

        Assert.assertTrue(repairActions.getFeatureCounter("assignAdd") == 0);
        Assert.assertTrue(repairActions.getFeatureCounter("assignRem") > 0);
        //Assert.assertTrue(repairActions.getFeatureCounter("assignExpChange") > 0);
    }

    @Test
    public void time4() {
        Config config = TestUtils.setupConfig("time_4");

        RepairActionDetector detector = new RepairActionDetector(config);
        RepairActions repairActions = detector.analyze();

        Assert.assertTrue(repairActions.getFeatureCounter("mcParSwap") > 0);
    }

    @Test
    public void time12() {
        Config config = TestUtils.setupConfig("time_12");

        RepairActionDetector detector = new RepairActionDetector(config);
        RepairActions repairActions = detector.analyze();

        Assert.assertTrue(repairActions.getFeatureCounter("assignAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("condBranIfAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("condBranIfElseAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("mcAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("objInstAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("retBranchAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("varAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("mcParValChange") > 0);

    }

    @Test
    public void time23() {
        Config config = TestUtils.setupConfig("time_23");

        RepairActionDetector detector = new RepairActionDetector(config);
        RepairActions repairActions = detector.analyze();

        Assert.assertTrue(repairActions.getFeatureCounter("mcAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("mcParValChange") > 0);
    }


    @Test
    public void jackrabbit002c5845() {
        Config config = TestUtils.setupConfig("jackrabbit-oak_002c5845");

        RepairActionDetector detector = new RepairActionDetector(config);
        RepairActions repairActions = detector.analyze();

        Assert.assertTrue(repairActions.getFeatureCounter("objInstMod") > 0);
    }

    @Test
    public void jackrabbit999097e1() {
        Config config = TestUtils.setupConfig("jackrabbit-oak_999097e1");

        RepairActionDetector detector = new RepairActionDetector(config);
        RepairActions repairActions = detector.analyze();

        Assert.assertTrue(repairActions.getFeatureCounter("assignAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("mcAdd") > 0);
        Assert.assertTrue(repairActions.getFeatureCounter("varAdd") > 0);
    }

    @Test
    public void wicket34634266() {
        Config config = TestUtils.setupConfig("wicket_34634266");

        RepairActionDetector detector = new RepairActionDetector(config);
        RepairActions repairActions = detector.analyze();

        Assert.assertTrue(repairActions.getFeatureCounter("mdRen") > 0);
    }


}
