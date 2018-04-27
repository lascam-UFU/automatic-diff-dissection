package fr.inria.spirals.main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by fermadeiral
 */
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
        String line;
        try {
            InputStream is = new FileInputStream(filename);
            int BUFFER_SIZE = 8192;

            BufferedReader in = new BufferedReader(new InputStreamReader(is, Charset.forName("ISO-8859-1")), BUFFER_SIZE);
            while ((line = in.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

}
