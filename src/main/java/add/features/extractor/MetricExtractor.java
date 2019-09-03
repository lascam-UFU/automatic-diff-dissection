package add.features.extractor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import add.entities.Metrics;
import add.features.FeatureAnalyzer;
import add.features.diffanalyzer.Change;
import add.features.diffanalyzer.Changes;
import add.features.diffanalyzer.JGitBasedDiffAnalyzer;
import add.main.Config;

/**
 * Created by tdurieux
 */
public class MetricExtractor extends FeatureAnalyzer {

    private Metrics metrics;

    public MetricExtractor(Config config) {
        super(config);
        this.metrics = new Metrics();
    }

    @Override
    public Metrics analyze() {
        JGitBasedDiffAnalyzer jgitDiffAnalyzer = new JGitBasedDiffAnalyzer(this.config.getDiffPath());

        Changes changes = jgitDiffAnalyzer.analyze();

        this.metrics.setFeatureCounter("nbFiles", jgitDiffAnalyzer.getNbFiles());

        this.computeNbModifiedClassesAndMethods(changes, jgitDiffAnalyzer);

        this.computePatchSize(changes);

        this.computeNbChunks(changes);

        this.computeSpreading(changes, jgitDiffAnalyzer);

        return metrics;
    }

    public void computeNbModifiedClassesAndMethods(Changes changes, JGitBasedDiffAnalyzer jgitDiffAnalyzer) {
        int nbModifiedClasses = 0;
        int nbModifiedMethods = 0;

        Pattern packagePattern = Pattern.compile("package\\s+([\\w\\.]+);");
        Pattern classDefinitionPattern = Pattern.compile("(^.*class\\s+((\\w+)(\\s*<[\\w.,\\s]+>)?)(\\s+extends\\s+((\\w+)(\\s*<[\\w.,\\s]+>)?))?(\\s+implements\\s+(((\\w+\\s*(<[\\w.,\\s]+>)?)[,\\s]*)+))?\\s*\\{)", Pattern.MULTILINE);
        Pattern methodDefinitionPattern = Pattern.compile("(((public|private|protected|static|final|native|synchronized|abstract|transient)+\\s)+[\\$_\\w\\<\\>\\w\\s\\[\\]]*\\s+[\\$_\\w]+\\([^\\)]*\\)?\\s*)", Pattern.MULTILINE);

        Map<String, List<String>> modifiedClassesAndMethods = new HashMap<>();

        Map<String, List<String>> patchedFiles = jgitDiffAnalyzer.getPatchedFiles(this.config.getBuggySourceDirectoryPath());

        for (Change change : changes.getNewChanges()) {
            String changedFile = change.getFile();
            int changedLine = change.getLine();

            List<String> newFileContent = null;
            for (String patchedFile : patchedFiles.keySet()) {
                if (patchedFile.endsWith(changedFile)) {
                    newFileContent = patchedFiles.get(patchedFile);
                    break;
                }
            }
            if (newFileContent == null) {
                continue;
            }
            String inputToMatcher = this.putFileContentInString(newFileContent, changedLine - 1);

            String packageName = "";
            Matcher matcher = packagePattern.matcher(inputToMatcher);
            while (matcher.find()) {
                packageName = matcher.group(1);
            }

            Map<String, Integer> classes = new HashMap<>();
            matcher = classDefinitionPattern.matcher(inputToMatcher);
            while (matcher.find()) {
                classes.put(packageName+"."+matcher.group(2), matcher.end(2));
            }

            Map<String, Integer> methods = new HashMap<>();
            matcher = methodDefinitionPattern.matcher(inputToMatcher);
            while (matcher.find()) {
                methods.put(matcher.group(1), matcher.end(1));
            }

            if (!classes.isEmpty()) {
                String closestClass = null;
                for (Map.Entry<String, Integer> entry : classes.entrySet()) {
                    if (closestClass == null || (closestClass != null && entry.getValue() > classes.get(closestClass))) {
                        closestClass = entry.getKey();
                    }
                }
                if (!modifiedClassesAndMethods.containsKey(closestClass)) {
                    modifiedClassesAndMethods.put(closestClass, new ArrayList<>());
                }
                if (!methods.isEmpty()) {
                    String closestMethod = null;
                    for (Map.Entry<String, Integer> entry : methods.entrySet()) {
                        if (closestMethod == null || (closestMethod != null && entry.getValue() > methods.get(closestMethod))) {
                            closestMethod = entry.getKey();
                        }
                    }
                    if (!modifiedClassesAndMethods.get(closestClass).contains(closestMethod)) {
                        modifiedClassesAndMethods.get(closestClass).add(closestMethod);
                    }
                }
            }
        }

        nbModifiedClasses = modifiedClassesAndMethods.keySet().size();
        for (List<String> methods : modifiedClassesAndMethods.values()) {
            nbModifiedMethods += methods.size();
        }

        this.metrics.setFeatureCounter("nbModifiedClasses", nbModifiedClasses);
        this.metrics.setFeatureCounter("nbModifiedMethods", nbModifiedMethods);
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

        this.metrics.setFeatureCounter("addedLines", patchAddedLines);
        this.metrics.setFeatureCounter("removedLines", patchRemovedLines);
        this.metrics.setFeatureCounter("modifiedLines", patchModifiedLines);
        this.metrics.setFeatureCounter("patchSize", patchAddedLines + patchRemovedLines + patchModifiedLines);
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

        this.metrics.setFeatureCounter("nbChunks", nbChunks);
    }

    public void computeSpreading(Changes changes, JGitBasedDiffAnalyzer jgitDiffAnalyzer) {
        int spreadingAllLines = 0;
        int spreadingCodeOnly = 0;

        Set<String> files = new HashSet<>();
        Map<String, List<String>> originalFiles = jgitDiffAnalyzer.getOriginalFiles(this.config.getBuggySourceDirectoryPath());
        Map<String, List<String>> patchedFiles = jgitDiffAnalyzer.getPatchedFiles(this.config.getBuggySourceDirectoryPath());
        files.addAll(originalFiles.keySet());
        files.addAll(patchedFiles.keySet());

        for (String file : files) {
            int untouchedline = 0;
            int trimUntouchedLine = 0;
            int lastUntouchedline = 0;
            int lastTrimUntouchedLine = 0;

            List<String> newFileContent = patchedFiles.get(file);
            List<String> oldFileContent = originalFiles.get(file);

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

        this.metrics.setFeatureCounter("spreadingAllLines", spreadingAllLines);
        this.metrics.setFeatureCounter("spreadingCodeOnly", spreadingCodeOnly);
    }

    public Map<String, List<Integer>> getChangedFiles(Changes changes) {
        Map<String, List<Integer>> changedFile = new HashMap<>();
        for (int i = 0; i < changes.getOldChanges().size(); i++) {
            Change change = changes.getOldChanges().get(i);
            if (!changedFile.containsKey(change.getFile())) {
                changedFile.put(change.getFile(), new ArrayList<>());
            }
            if ("INSERT".equals(change.getType())) {
                changedFile.get(change.getFile()).add(change.getLine());
            } else {
                for (int j = change.getLine(); j <= change.getEndLine(); j++) {
                    changedFile.get(change.getFile()).add(j);
                }
            }
        }
        return changedFile;
    }

    private Change getChange(Changes changes, String file, int oldLine, int newLine) {
        for (int i = 0; i < changes.getOldChanges().size(); i++) {
            Change change = changes.getOldChanges().get(i);
            if (change.getLength() == 0) {
                continue;
            }
            String fileName = file.replace("/", ".");
            String className = change.getClassName() + ".java";
            if (fileName.endsWith(className)) {
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
            String fileName = file.replace("/", ".");
            String className = change.getClassName() + ".java";
            if (fileName.endsWith(className)) {
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

    private String putFileContentInString(List<String> fileContent, int lineLimit) {
        String content = "";
        String line;
        for (int i = 0; i < fileContent.size() && i < lineLimit; i++) {
            line = fileContent.get(i);
            content += line+"\n";
        }
        return content;
    }

}
