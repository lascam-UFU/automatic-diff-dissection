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

        bugInfo = new BugInfo("Chart 18");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/chart_18/buggy-version").getPath());
        bugInfo.setFixedSourceDirectoryPath(Constants.class.getResource("/chart_18/fixed-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/chart_18/chart_18.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 24");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_24/buggy-version").getPath());
        bugInfo.setFixedSourceDirectoryPath(Constants.class.getResource("/closure_24/fixed-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_24/closure_24.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 76");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_76/buggy-version").getPath());
        bugInfo.setFixedSourceDirectoryPath(Constants.class.getResource("/closure_76/fixed-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_76/closure_76.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Math 4");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/math_4/buggy-version").getPath());
        bugInfo.setFixedSourceDirectoryPath(Constants.class.getResource("/math_4/fixed-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/math_4/math_4.diff").getPath());
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

        return tmpBugIdToInfoMap;
    }

}
