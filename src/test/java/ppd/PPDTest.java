package ppd;

import add.entities.RepairPatterns;
import add.features.detector.repairpatterns.RepairPatternDetector;
import add.main.Config;
import add.utils.Constants;
import add.utils.TestUtils;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PPDTest {

    @Test
    public void PPDTest_VEM2018() {
        Logger all = (Logger) LoggerFactory.getLogger("add");
        all.setLevel(Level.ERROR);

        DecimalFormat df = new DecimalFormat("#.##");

        File file = new File(Constants.class.getResource("/vem_2018_results").getPath());
        JSONArray dissectionResults = null;
        try {
            dissectionResults = new JSONArray(new String(Files.readAllBytes(Paths.get(Constants.class.getResource("/" + file.getName() + "/defects4j-bugs.json").getPath())), java.nio.charset.StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, List<String>> mapPatternToBugsFoundNow = new HashMap<>();
        Map<String, List<String>> mapPatternToBugsFoundVEM = new HashMap<>();
        Map<String, List<String>> mapPatternToBugsFoundDissection = new HashMap<>();

        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            String vemResultFile = files[i].getName();

            if (vemResultFile.equals("defects4j-bugs.json")) {
                continue;
            }

            String bugId = vemResultFile.replace("_all.json", "");

            Config config = TestUtils.setupConfig(bugId);

            RepairPatternDetector detector = new RepairPatternDetector(config);
            RepairPatterns repairPatterns = detector.analyze();

            for (String pattern : repairPatterns.getFeatureNames()) {
                if (repairPatterns.getFeatureCounter(pattern) > 0) {
                    if (!mapPatternToBugsFoundNow.containsKey(pattern)) {
                        mapPatternToBugsFoundNow.put(pattern, new ArrayList<>());
                    }
                    mapPatternToBugsFoundNow.get(pattern).add(bugId);
                }
            }


            try {
                String vemResult = new String(Files.readAllBytes(Paths.get(Constants.class.getResource("/" + file.getName() + "/" + vemResultFile).getPath())), java.nio.charset.StandardCharsets.UTF_8);

                JSONObject vemPatterns = new JSONObject(vemResult).getJSONObject("repairPatterns");
                for (String pattern : vemPatterns.keySet()) {
                    if (vemPatterns.getInt(pattern) > 0) {
                        if (!mapPatternToBugsFoundVEM.containsKey(pattern)) {
                            mapPatternToBugsFoundVEM.put(pattern, new ArrayList<>());
                        }
                        mapPatternToBugsFoundVEM.get(pattern).add(bugId);
                    }

                }

                for (int j = 0; j < dissectionResults.length(); j++) {
                    JSONObject obj = dissectionResults.getJSONObject(j);
                    if ((obj.getString("project").toLowerCase() + "_" + obj.getInt("bugId")).equals(bugId)) {
                        JSONArray dissectionPatterns = obj.getJSONArray("repairPatterns");
                        for (int k = 0; k < dissectionPatterns.length(); k++) {
                            String pattern = dissectionPatterns.getString(k);
                            if (!mapPatternToBugsFoundDissection.containsKey(pattern)) {
                                mapPatternToBugsFoundDissection.put(pattern, new ArrayList<>());
                            }
                            mapPatternToBugsFoundDissection.get(pattern).add(bugId);
                        }
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n\n%% Precision and recall from VEM version compared to dissection data");
        for (String pattern : mapPatternToBugsFoundVEM.keySet()) {
            List<String> VEM = mapPatternToBugsFoundVEM.get(pattern);
            List<String> dissection = mapPatternToBugsFoundDissection.get(pattern);

            int matchVEMWithDissection = 0;
            for (String bug : dissection) {
                if (VEM.contains(bug)) {
                    matchVEMWithDissection++;
                }
            }
            System.out.println("Pattern " + pattern
                    + " - precision " + (df.format((double) matchVEMWithDissection * 100 / VEM.size()))
                    + "% recall " + (df.format((double) matchVEMWithDissection * 100 / dissection.size()) + "%"));
        }

        System.out.println("\n\n%% Precision and recall from current version compared to dissection data");
        for (String pattern : mapPatternToBugsFoundVEM.keySet()) {
            List<String> current = mapPatternToBugsFoundNow.get(pattern);
            List<String> dissection = mapPatternToBugsFoundDissection.get(pattern);

            int matchCurrentWithDissection = 0;
            for (String bug : dissection) {
                if (current.contains(bug)) {
                    matchCurrentWithDissection++;
                }
            }
            System.out.println("Pattern " + pattern
                    + " - precision " + (df.format((double) matchCurrentWithDissection * 100 / current.size()))
                    + "% recall " + (df.format((double) matchCurrentWithDissection * 100 / dissection.size()) + "%"));
        }

        int falsePositiveIncreased = 0;
        int falseNegativeIncreased = 0;
        int falsePositiveDecreased = 0;
        int falseNegativeDecreased = 0;
        System.out.println("\n\n%% Changes in detection");
        for (String pattern : mapPatternToBugsFoundVEM.keySet()) {
            List<String> current = mapPatternToBugsFoundNow.get(pattern);
            List<String> VEM = mapPatternToBugsFoundVEM.get(pattern);
            List<String> dissection = mapPatternToBugsFoundDissection.get(pattern);

            for (String bug : dissection) {
                if (current.contains(bug)) {
                    if (!VEM.contains(bug)) {
                        falseNegativeDecreased++;
                        System.out.println("[" + pattern + "] found in " + bug + " (OK)");
                    }
                } else {
                    if (VEM.contains(bug)) {
                        falseNegativeIncreased++;
                        System.out.println("[" + pattern + "] not found in " + bug);
                    }
                }
            }
            for (String bug : current) {
                if (!dissection.contains(bug) && !VEM.contains(bug)) {
                    falsePositiveIncreased++;
                    System.out.println("[" + pattern + "] found in " + bug);
                }
            }
            for (String bug : VEM) {
                if (!dissection.contains(bug) && !current.contains(bug)) {
                    falsePositiveDecreased++;
                    System.out.println("[" + pattern + "] not found in " + bug + " (OK)");
                }
            }
        }
        System.out.println("\nImprovements:");
        System.out.println("False positive decreased: " + falsePositiveDecreased);
        System.out.println("False negative decreased: " + falseNegativeDecreased);
        System.out.println("\nLost:");
        System.out.println("False positive increased: " + falsePositiveIncreased);
        System.out.println("False negative increased: " + falseNegativeIncreased + "\n");
    }

}
