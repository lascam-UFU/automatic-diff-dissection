package fr.inria.spirals.features.diffanalyzer;

import fr.inria.spirals.main.Config;
import fr.inria.spirals.utils.TestUtils;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by fermadeiral
 */
public class JGitBasedDiffAnalyzerTest {

    @Test
    public void testMethodAnalyze() {
        TestUtils.setupConfig("Closure 24");

        JGitBasedDiffAnalyzer jgitDiffAnalyzer = new JGitBasedDiffAnalyzer(Config.getInstance().getDiffPath());
        Changes changes = jgitDiffAnalyzer.analyze();

        assertEquals(1, jgitDiffAnalyzer.getNbFiles());
        assertEquals(4, changes.getCombinedChanges().size());
    }

    @Test
    public void testMethodGetOriginalFilesToReturnOneFile() {
        TestUtils.setupConfig("Chart 1");

        String buggySourcePath = Config.getInstance().getBuggySourceDirectoryPath();
        List<String> expectedBuggyFilePaths = new ArrayList<>(
                Arrays.asList(buggySourcePath+"/source/org/jfree/chart/renderer/category/AbstractCategoryItemRenderer.java"));

        JGitBasedDiffAnalyzer jgitDiffAnalyzer = new JGitBasedDiffAnalyzer(Config.getInstance().getDiffPath());
        Map<String, List<String>> buggyFiles = jgitDiffAnalyzer.getOriginalFiles(buggySourcePath);

        List<String> actualBuggyFilePaths = new ArrayList<>();
        Iterator<String> ite = buggyFiles.keySet().iterator();
        while (ite.hasNext()) {
            actualBuggyFilePaths.add(ite.next());
        }

        assertEquals(expectedBuggyFilePaths.size(), actualBuggyFilePaths.size());
        assertTrue(actualBuggyFilePaths.containsAll(expectedBuggyFilePaths));
    }

    @Test
    public void testMethodGetOriginalFilesToReturnMoreThanOneFile() {
        TestUtils.setupConfig("Chart 18");

        String buggySourcePath = Config.getInstance().getBuggySourceDirectoryPath();
        List<String> expectedBuggyFilePaths = new ArrayList<>(
                Arrays.asList(buggySourcePath+"/source/org/jfree/data/DefaultKeyedValues.java",
                        buggySourcePath+"/source/org/jfree/data/DefaultKeyedValues2D.java"));

        JGitBasedDiffAnalyzer jgitDiffAnalyzer = new JGitBasedDiffAnalyzer(Config.getInstance().getDiffPath());
        Map<String, List<String>> buggyFiles = jgitDiffAnalyzer.getOriginalFiles(buggySourcePath);

        List<String> actualBuggyFilePaths = new ArrayList<>();
        Iterator<String> ite = buggyFiles.keySet().iterator();
        while (ite.hasNext()) {
            actualBuggyFilePaths.add(ite.next());
        }

        assertEquals(expectedBuggyFilePaths.size(), actualBuggyFilePaths.size());
        assertTrue(actualBuggyFilePaths.containsAll(expectedBuggyFilePaths));
    }

}
