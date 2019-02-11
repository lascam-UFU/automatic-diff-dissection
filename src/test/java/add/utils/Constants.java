package add.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fermadeiral
 */
public class Constants {

    public static final Map<String, BugInfo> BUG_ID_T0_INFO_MAP = initBugIdToInfo();

    private static Map initBugIdToInfo() {
        Map<String, BugInfo> tmpBugIdToInfoMap = new HashMap<>();

        File file = new File(Constants.class.getResource("/patches").getPath());

        try {
            Files.list(file.toPath()).forEach(path -> {
                String patch = path.getFileName().toString();
                BugInfo bugInfo = new BugInfo(patch);
                String pathToPatch = "/" + file.getName() + "/" + patch;
                bugInfo.setBuggySourceDirectoryPath(Constants.class.getResource( pathToPatch + "/buggy-version").getPath());
                bugInfo.setDiffPath(Constants.class.getResource(pathToPatch + "/path.diff").getPath());
                tmpBugIdToInfoMap.put(bugInfo.getBugId(), bugInfo);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tmpBugIdToInfoMap;
    }

}
