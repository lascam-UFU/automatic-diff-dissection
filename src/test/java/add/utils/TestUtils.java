package add.utils;

import add.main.Config;

/**
 * Created by fermadeiral
 */
public class TestUtils {

    public static Config setupConfig(String bugId) {
        BugInfo bugInfo = Constants.BUG_ID_T0_INFO_MAP.get(bugId);

        Config config = new Config();
        config.setBugId(bugId);
        config.setBuggySourceDirectoryPath(bugInfo.getBuggySourceDirectoryPath());
        config.setDiffPath(bugInfo.getDiffPath());

        return config;
    }

}
