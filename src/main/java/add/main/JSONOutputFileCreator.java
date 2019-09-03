package add.main;

import java.io.FileWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by fermadeiral
 */
public class JSONOutputFileCreator {
    private static Logger LOGGER = LoggerFactory.getLogger(JSONOutputFileCreator.class);

    public static String FILE_EXTENSION = ".json";

    public static void writeJSONfile(String jsonString, Config config) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(config.getOutputDirectoryPath()+"/"+config.getBugId()+"_"+config.getLauncherMode().name().toLowerCase()+FILE_EXTENSION);
            fileWriter.write(jsonString);
            fileWriter.flush();
        } catch (Exception e) {
            LOGGER.error(e.toString());
        }
    }

}
