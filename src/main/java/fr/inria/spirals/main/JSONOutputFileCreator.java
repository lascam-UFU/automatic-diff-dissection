package fr.inria.spirals.main;

import java.io.FileWriter;

/**
 * Created by fermadeiral
 */
public class JSONOutputFileCreator {

    public static String FILE_EXTENSION = ".json";

    public static void writeJSONfile(String jsonString, Config config) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(config.getOutputDirectoryPath()+"/"+config.getBugId()+"_"+config.getLauncherMode().name().toLowerCase()+FILE_EXTENSION);
            fileWriter.write(jsonString);
            fileWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
