package fr.inria.spirals.utils;

import fr.inria.spirals.main.Config;

/**
 * Created by fermadeiral
 */
public class TestUtils {

    public static void setupConfig(String bugId) {
        BugInfo bugInfo = Constants.BUG_ID_T0_INFO_MAP.get(bugId);

        Config config = Config.getInstance();
        config.setBugId(bugId);
        config.setBuggySourceDirectoryPath(bugInfo.getBuggySourceDirectoryPath());
        config.setFixedSourceDirectoryPath(bugInfo.getFixedSourceDirectoryPath());
        config.setDiffPath(bugInfo.getDiffPath());
    }

}
