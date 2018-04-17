package fr.inria.spirals.features.analyzer;

import fr.inria.spirals.entities.Change;
import fr.inria.spirals.entities.Changes;
import org.eclipse.jgit.diff.Edit;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by fermadeiral
 */
public class DiffAnalyzerTest {

    @Test
    public void testMethodAnalyze() {
        String diffPath = DiffAnalyzerTest.class.getResource("/closure_24/closure_24.diff").getPath();

        DiffAnalyzer diffAnalyzer = new DiffAnalyzer(diffPath);
        Changes changes = diffAnalyzer.analyze();

        assertEquals(1, diffAnalyzer.getNbFiles());
        assertEquals(4, changes.getCombinedChanges().size());
    }

    @Test
    public void testMethodGetOriginalFilesToReturnOneFile() {
        String diffPath = DiffAnalyzerTest.class.getResource("/chart_1/chart_1.diff").getPath();
        String buggySourcePath = DiffAnalyzerTest.class.getResource("/chart_1/buggy-version").getPath();
        List<String> expectedBuggyFilePaths = new ArrayList<>(
                Arrays.asList(buggySourcePath+"/source/org/jfree/chart/renderer/category/AbstractCategoryItemRenderer.java"));

        DiffAnalyzer diffAnalyzer = new DiffAnalyzer(diffPath);
        Map<String, List<String>> buggyFiles = diffAnalyzer.getOriginalFiles(buggySourcePath);

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
        String diffPath = DiffAnalyzerTest.class.getResource("/chart_18/chart_18.diff").getPath();
        String buggySourcePath = DiffAnalyzerTest.class.getResource("/chart_18/buggy-version").getPath();
        List<String> expectedBuggyFilePaths = new ArrayList<>(
                Arrays.asList(buggySourcePath+"/source/org/jfree/data/DefaultKeyedValues.java",
                        buggySourcePath+"/source/org/jfree/data/DefaultKeyedValues2D.java"));

        DiffAnalyzer diffAnalyzer = new DiffAnalyzer(diffPath);
        Map<String, List<String>> buggyFiles = diffAnalyzer.getOriginalFiles(buggySourcePath);

        List<String> actualBuggyFilePaths = new ArrayList<>();
        Iterator<String> ite = buggyFiles.keySet().iterator();
        while (ite.hasNext()) {
            actualBuggyFilePaths.add(ite.next());
        }

        assertEquals(expectedBuggyFilePaths.size(), actualBuggyFilePaths.size());
        assertTrue(actualBuggyFilePaths.containsAll(expectedBuggyFilePaths));
    }

}
