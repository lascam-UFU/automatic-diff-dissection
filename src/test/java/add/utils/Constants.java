package add.utils;

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
        bugInfo.setDiffPath(Constants.class.getResource("/chart_1/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Chart 4");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/chart_4/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/chart_4/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Chart 5");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/chart_5/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/chart_5/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Chart 8");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/chart_8/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/chart_8/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Chart 10");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/chart_10/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/chart_10/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Chart 12");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/chart_12/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/chart_12/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Chart 14");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/chart_14/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/chart_14/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Chart 15");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/chart_15/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/chart_15/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Chart 16");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/chart_16/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/chart_16/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Chart 17");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/chart_17/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/chart_17/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Chart 18");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/chart_18/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/chart_18/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Chart 21");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/chart_21/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/chart_21/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Chart 25");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/chart_25/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/chart_25/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 1");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_1/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_1/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 2");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_2/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_2/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 3");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_3/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_3/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 4");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_4/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_4/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 5");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_5/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_5/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 6");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_6/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_6/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 11");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_11/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_11/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 13");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_13/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_13/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 14");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_14/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_14/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 19");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_19/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_19/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 20");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_20/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_20/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 23");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_23/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_23/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 24");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_24/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_24/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 26");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_26/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_26/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 30");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_30/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_30/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 31");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_31/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_31/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 35");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_35/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_35/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 37");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_37/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_37/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 40");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_40/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_40/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 44");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_44/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_44/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 47");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_47/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_47/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 55");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_55/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_55/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 60");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_60/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_60/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 61");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_61/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_61/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 64");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_64/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_64/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 66");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_66/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_66/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 68");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_68/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_68/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 76");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_76/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_76/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 80");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_80/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_80/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 81");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_81/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_81/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 83");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_83/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_83/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 94");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_94/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_94/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 102");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_102/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_102/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 104");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_104/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_104/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 109");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_109/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_109/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 110");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_110/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_110/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 111");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_111/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_111/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 114");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_114/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_114/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 117");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_117/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_117/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 120");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_120/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_120/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 121");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_121/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_121/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 124");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_124/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_124/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 125");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_125/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_125/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Closure 131");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/closure_131/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/closure_131/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Lang 8");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/lang_8/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/lang_8/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Lang 13");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/lang_13/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/lang_13/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Lang 17");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/lang_17/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/lang_17/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Lang 26");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/lang_26/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/lang_26/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Lang 31");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/lang_31/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/lang_31/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Lang 33");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/lang_33/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/lang_33/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Lang 38");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/lang_38/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/lang_38/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Lang 45");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/lang_45/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/lang_45/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Lang 49");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/lang_49/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/lang_49/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Lang 53");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/lang_53/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/lang_53/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Math 4");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/math_4/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/math_4/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Math 7");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/math_7/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/math_7/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Math 9");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/math_9/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/math_9/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Math 12");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/math_12/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/math_12/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Math 15");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/math_15/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/math_15/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Math 27");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/math_27/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/math_27/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Math 33");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/math_33/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/math_33/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Math 46");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/math_46/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/math_46/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Math 48");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/math_48/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/math_48/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Math 50");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/math_50/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/math_50/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Math 58");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/math_58/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/math_58/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Math 60");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/math_60/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/math_60/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Math 64");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/math_64/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/math_64/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Math 68");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/math_68/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/math_68/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Math 76");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/math_76/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/math_76/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Math 86");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/math_86/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/math_86/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Math 104");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/math_104/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/math_104/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Math 105");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/math_105/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/math_105/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Mockito 4");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/mockito_4/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/mockito_4/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Mockito 14");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/mockito_14/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/mockito_14/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Mockito 21");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/mockito_21/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/mockito_21/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Mockito 22");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/mockito_22/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/mockito_22/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Mockito 23");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/mockito_23/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/mockito_23/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Time 7");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/time_7/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/time_7/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Time 8");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/time_8/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/time_8/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Time 10");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/time_10/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/time_10/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Time 12");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/time_12/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/time_12/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Time 17");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/time_17/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/time_17/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Time 18");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/time_18/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/time_18/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Time 23");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/time_23/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/time_23/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Jackrabbit 002c5845");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/jackrabbit-oak_002c5845/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/jackrabbit-oak_002c5845/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Jackrabbit 999097e1");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/jackrabbit-oak_999097e1/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/jackrabbit-oak_999097e1/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        bugInfo = new BugInfo("Wicket 34634266");
        bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource("/wicket_34634266/buggy-version").getPath());
        bugInfo.setDiffPath(Constants.class.getResource("/wicket_34634266/path.diff").getPath());
        tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);

        return tmpBugIdToInfoMap;
    }

}
