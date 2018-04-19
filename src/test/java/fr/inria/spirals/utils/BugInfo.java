package fr.inria.spirals.utils;

/**
 * Created by fermadeiral
 */
public class BugInfo {

    private String bugId;
    private String buggySourceDirectoryPath;
    private String fixedSourceDirectoryPath;
    private String diffPath;

    BugInfo(String bugId) {
        this.bugId = bugId;
    }

    public String getBugId() {
        return bugId;
    }

    public String getBuggySourceDirectoryPath() {
        return buggySourceDirectoryPath;
    }

    public void setBuggySourceDirectoryPath(String buggySourceDirectoryPath) {
        this.buggySourceDirectoryPath = buggySourceDirectoryPath;
    }

    public String getFixedSourceDirectoryPath() {
        return fixedSourceDirectoryPath;
    }

    public void setFixedSourceDirectoryPath(String fixedSourceDirectoryPath) {
        this.fixedSourceDirectoryPath = fixedSourceDirectoryPath;
    }

    public String getDiffPath() {
        return diffPath;
    }

    public void setDiffPath(String diffPath) {
        this.diffPath = diffPath;
    }

}
