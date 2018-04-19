package fr.inria.spirals.features.extractor;

import fr.inria.spirals.entities.Metrics;
import fr.inria.spirals.features.analyzer.Change;
import fr.inria.spirals.features.analyzer.Changes;
import fr.inria.spirals.features.analyzer.DiffAnalyzer;
import fr.inria.spirals.main.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tdurieux
 */
public class MetricExtractor extends AbstractExtractor {

    private Metrics metrics;

    public MetricExtractor(String oldSourcePath, String newSourcePath, String diffPath) {
        super(oldSourcePath, newSourcePath, diffPath);
        this.metrics = new Metrics();
    }

    public Metrics extract() {
        DiffAnalyzer diffAnalyzer = new DiffAnalyzer(diffPath);

        Changes changes = diffAnalyzer.analyze();

        this.metrics.setMetric("nbFiles", diffAnalyzer.getNbFiles());

        this.computePatchSize(changes);

        this.computeNbChunks(changes);

        this.computeSpreading(changes);

        return metrics;
    }

    /**
     * Count the number of lines added, removed and modified in the patch
     */
    public void computePatchSize(Changes changes) {
        int patchAddedLines = 0;
        int patchRemovedLines = 0;
        int patchModifiedLines = 0;

        for (Change change : changes.getNewChanges()) {
            int addedLines = change.getLength();
            int removedLines = change.getAssociateChange().getLength();

            int chunkAddedLines = 0;
            int chunkRemovedLines = 0;
            int chunkModifiedLines;

            int diff = addedLines - removedLines;
            if (diff == 0) {
                chunkModifiedLines = addedLines;
            } else if (diff > 0 ) {
                chunkModifiedLines = removedLines;
                chunkAddedLines = diff;
            } else {
                chunkModifiedLines = addedLines;
                chunkRemovedLines = removedLines - addedLines;
            }

            patchAddedLines += chunkAddedLines;
            patchRemovedLines += chunkRemovedLines;
            patchModifiedLines += chunkModifiedLines;
        }

        this.metrics.setMetric("addedLines", patchAddedLines);
        this.metrics.setMetric("removedLines", patchRemovedLines);
        this.metrics.setMetric("modifiedLines", patchModifiedLines);
        this.metrics.setMetric("patchSize", patchAddedLines + patchRemovedLines + patchModifiedLines);
    }

    /**
     * Compute the number of chunks in the patch
     */
    public void computeNbChunks(Changes changes) {
        int nbChunks = 0;

        for (Change change : changes.getNewChanges()) {
            if (change.getLength() == 0) {
                continue;
            }
            if (change.getType().equals("INSERT")) {
                nbChunks++;
            } else if (change.getType().equals("REPLACE")) {
                nbChunks++;
            }
        }

        for (Change change : changes.getOldChanges()) {
            if (change.getLength() == 0) {
                continue;
            }
            if (change.getType().equals("DELETE")) {
                nbChunks++;
            }
        }

        this.metrics.setMetric("nbChunks", nbChunks);
    }

    public void computeSpreading(Changes changes) {
        int spreadingAllLines = 0;
        int spreadingCodeOnly = 0;

        Set<String> files = new HashSet<>();
        for (int i = 0; i < changes.getNewChanges().size(); i++) {
            Change change = changes.getNewChanges().get(i);
            files.add(change.getClassName());
        }
        for (int i = 0; i < changes.getOldChanges().size(); i++) {
            Change change = changes.getOldChanges().get(i);
            files.add(change.getClassName());
        }

        for (String file : files) {
            int untouchedline = 0;
            int trimUntouchedLine = 0;
            int lastUntouchedline = 0;
            int lastTrimUntouchedLine = 0;

            List<String> newFileContent = getFileContent(file, changes.getNewChanges(), this.fixedSourcePath);
            List<String> oldFileContent = getFileContent(file, changes.getOldChanges(), this.buggySourcePath);

            int lineDiff = 0;
            boolean first = false;

            for (int line = 0; line < oldFileContent.size(); line++) {
                String oldLine = oldFileContent.get(line);
                String newLine = newFileContent.get(line + lineDiff);

                if (oldLine.equals(newLine)) {
                    if (!first) {
                        continue;
                    }
                    untouchedline++;
                    if (!isComment(oldLine)) {
                        trimUntouchedLine++;
                    }
                    continue;
                }

                if (!first) {
                    first = true;
                }

                lastUntouchedline = untouchedline;
                lastTrimUntouchedLine = trimUntouchedLine;
                Change change = getChange(changes, file, line + 1, line + lineDiff + 1);
                if (change.getType().equals("INSERT")) {
                    lineDiff++;
                    line--;
                } else if (change.getType().equals("DELETE")) {
                    lineDiff--;
                } else if (change.getType().equals("REPLACE")) {
                    if (change.getLength() != change.getAssociateChange().getLength()) {
                        line += change.getLength() - 1;
                        lineDiff += change.getAssociateChange().getLength() - change.getLength();
                    }
                }
            }

            spreadingAllLines += lastUntouchedline;
            spreadingCodeOnly += lastTrimUntouchedLine;
        }

        this.metrics.setMetric("spreadingAllLines", spreadingAllLines);
        this.metrics.setMetric("spreadingCodeOnly", spreadingCodeOnly);
    }

    private Change getChange(Changes changes, String file, int oldLine, int newLine) {
        for (int i = 0; i < changes.getOldChanges().size(); i++) {
            Change change = changes.getOldChanges().get(i);
            if (change.getLength() == 0) {
                continue;
            }
            if (change.getClassName().equals(file)) {
                if (change.getLine() <= oldLine && change.getEndLine() >= oldLine) {
                    return change;
                }
            }
        }
        for (int i = 0; i < changes.getNewChanges().size(); i++) {
            Change change = changes.getNewChanges().get(i);
            if (change.getLength() == 0) {
                continue;
            }
            if (change.getClassName().equals(file)) {
                if (change.getLine() <= newLine && change.getEndLine() >= newLine) {
                    return change;
                }
            }
        }
        return getChange(changes, file, oldLine - 1, newLine - 1);
    }

    private boolean isComment(String s) {
        s = s.trim();
        return s.isEmpty() ||
                s.startsWith("//") ||
                s.startsWith("/*") ||
                s.startsWith("*/") ||
                s.startsWith("*");
    }

    private List<String> getFileContent(String filename, List<Change> changes, String root) {
        String pathFile = null;
        for (Change change : changes) {
            String file = change.getClassName();
            if (file.equals(filename)) {
                pathFile = change.getFile();
                break;
            }
        }

        pathFile = Utils.getFullPath(root, pathFile);

        try (BufferedReader br = new BufferedReader(new FileReader(new File(pathFile)))) {
            List<String> output = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                output.add(line);
            }
            return output;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
