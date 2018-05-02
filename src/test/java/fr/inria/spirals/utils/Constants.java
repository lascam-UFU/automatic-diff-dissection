package fr.inria.spirals.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fermadeiral
 */
public class Constants {

    public static final Map<String, BugInfo> BUG_ID_T0_INFO_MAP = initBugIdToInfo();

    private static Map initBugIdToInfo() {
        Map<String, BugInfo> tmpBugIdToInfoMap = new HashMap<>();

        BugInfo bugInfo = new BugInfo("Chart 1");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/chart_1/buggy-version").getPath());
        bugInfo.setFixedSourceDirectoryPath(Constants.class.getResource("/chart_1/fixed-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/chart_1/chart_1.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Chart 4");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/chart_4/buggy-version").getPath());
        bugInfo.setFixedSourceDirectoryPath(Constants.class.getResource("/chart_4/fixed-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/chart_4/chart_4.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Chart 14");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/chart_14/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/chart_14/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Chart 15");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/chart_15/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/chart_15/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Chart 18");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/chart_18/buggy-version").getPath());
        bugInfo.setFixedSourceDirectoryPath(Constants.class.getResource("/chart_18/fixed-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/chart_18/chart_18.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Chart 25");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/chart_25/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/chart_25/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 23");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_23/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_23/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 24");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_24/buggy-version").getPath());
        bugInfo.setFixedSourceDirectoryPath(Constants.class.getResource("/closure_24/fixed-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_24/closure_24.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 26");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_26/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_26/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 64");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_64/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_64/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 114");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_114/buggy-version").getPath());
        bugInfo.setFixedSourceDirectoryPath(Constants.class.getResource("/closure_114/fixed-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_114/closure_114.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 125");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_125/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_125/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 76");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_76/buggy-version").getPath());
        bugInfo.setFixedSourceDirectoryPath(Constants.class.getResource("/closure_76/fixed-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_76/closure_76.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Lang 33");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/lang_33/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/lang_33/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Math 4");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/math_4/buggy-version").getPath());
        bugInfo.setFixedSourceDirectoryPath(Constants.class.getResource("/math_4/fixed-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/math_4/math_4.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Math 68");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/math_68/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/math_68/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Time 12");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/time_12/buggy-version").getPath());
        bugInfo.setFixedSourceDirectoryPath(Constants.class.getResource("/time_12/fixed-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/time_12/time_12.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Time 23");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/time_23/buggy-version").getPath());
        bugInfo.setFixedSourceDirectoryPath(Constants.class.getResource("/time_23/fixed-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/time_23/time_23.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Mockito 4");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/mockito_4/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/mockito_4/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Mockito 21");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/mockito_21/buggy-version").getPath());
        bugInfo.setFixedSourceDirectoryPath(Constants.class.getResource("/mockito_21/fixed-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/mockito_21/mockito_21.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Mockito 22");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/mockito_22/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/mockito_22/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Jackrabbit 002c5845");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/jackrabbit-oak_002c5845/buggy-version").getPath());
        //bugInfo.setFixedSourceDirectoryPath(Constants.class.getResource("/jackrabbit-oak_002c5845/fixed-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/jackrabbit-oak_002c5845/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Jackrabbit 999097e1");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/jackrabbit-oak_999097e1/buggy-version").getPath());
        //bugInfo.setFixedSourceDirectoryPath(Constants.class.getResource("/jackrabbit-oak_002c5845/fixed-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/jackrabbit-oak_999097e1/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Wicket 34634266");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/wicket_34634266/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/wicket_34634266/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        return tmpBugIdToInfoMap;
    }

}
