package add.utils;

/**
 * Created by fermadeiral
 */
public class BugInfo {

    private String bugId;
    private String buggySourceDirectoryPath;
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

    public String getDiffPath() {
        return diffPath;
    }

    public void setDiffPath(String diffPath) {
        this.diffPath = diffPath;
    }

}
