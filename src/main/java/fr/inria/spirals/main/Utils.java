package fr.inria.spirals.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Utils {

    public static String getFullPath(String projectRoot, String fileName) {
        if (!fileName.startsWith("/")) {
            fileName = "/" + fileName;
        }
        if (!fileName.contains(projectRoot)) {
            fileName = projectRoot + fileName;
        }
        return fileName;
    }

    public static List<String> fileToLines(String filename) {
        List<String> lines = new LinkedList<String>();
        String line = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            while ((line = in.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

}
