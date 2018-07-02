package add.main;

/**
 * Created by fermadeiral
 */
public class Config {

    private LauncherMode launcherMode;
    private String bugId;
    private String buggySourceDirectoryPath;
    private String diffPath;
    private String outputDirectoryPath;

    public Config() {}

    public LauncherMode getLauncherMode() {
        return launcherMode;
    }

    public void setLauncherMode(LauncherMode launcherMode) {
        this.launcherMode = launcherMode;
    }

    public String getBugId() {
        return bugId;
    }

    public void setBugId(String bugId) {
        this.bugId = bugId;
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

    public String getOutputDirectoryPath() {
        return outputDirectoryPath;
    }

    public void setOutputDirectoryPath(String outputDirectoryPath) {
        this.outputDirectoryPath = outputDirectoryPath;
    }

}
