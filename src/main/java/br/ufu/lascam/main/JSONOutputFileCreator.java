package br.ufu.lascam.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;

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
