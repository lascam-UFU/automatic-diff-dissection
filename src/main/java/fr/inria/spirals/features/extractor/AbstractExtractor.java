package fr.inria.spirals.features.extractor;

/**
 * Created by fermadeiral
 */
public abstract class AbstractExtractor {

    protected String buggySourcePath;
    protected String fixedSourcePath;
    protected String diffPath;

    AbstractExtractor(String buggySourcePath, String diffPath) {
        this.buggySourcePath = buggySourcePath;
        this.diffPath = diffPath;
    }

    AbstractExtractor(String buggySourcePath, String fixedSourcePath, String diffPath) {
        this(buggySourcePath, diffPath);
        this.fixedSourcePath = fixedSourcePath;
    }

}
