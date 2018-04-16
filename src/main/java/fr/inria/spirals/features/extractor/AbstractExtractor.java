package fr.inria.spirals.features.extractor;

/**
 * Created by fermadeiral
 */
public abstract class AbstractExtractor {

    protected String buggySourcePath;
    protected String fixedSourcePath;
    protected String diffPath;
    protected String project;
    protected String bugId;

    AbstractExtractor(String buggySourcePath, String diffPath) {
        this.buggySourcePath = buggySourcePath;
        this.diffPath = diffPath;
    }

    AbstractExtractor(String buggySourcePath, String fixedSourcePath, String diffPath) {
        this(buggySourcePath, diffPath);
        this.fixedSourcePath = fixedSourcePath;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProject() {
        return project;
    }

    public void setBugId(String bugId) {
        this.bugId = bugId;
    }

    public String getBugId() {
        return bugId;
    }

}
