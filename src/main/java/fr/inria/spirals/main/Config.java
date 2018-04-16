package fr.inria.spirals.main;

/**
 * Created by fermadeiral
 */
public class Config {

    private static Config INSTANCE;

    private String project;
    private String bugId;
    private String outputDirectoryPath;
    private String buggySourceDirectoryPath;
    private String fixedSourceDirectoryPath;
    private String diffPath;
    private String launcherMode;

    private Config() {}

    public static Config getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Config();
        }
        return INSTANCE;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getBugId() {
        return bugId;
    }

    public void setBugId(String bugId) {
        this.bugId = bugId;
    }

    public String getOutputDirectoryPath() {
        return outputDirectoryPath;
    }

    public void setOutputDirectoryPath(String outputDirectoryPath) {
        this.outputDirectoryPath = outputDirectoryPath;
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

    public String getLauncherMode() {
        return launcherMode;
    }

    public void setLauncherMode(String launcherMode) {
        this.launcherMode = launcherMode;
    }

}
