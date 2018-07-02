package add.entities;

/**
 * Created by fermadeiral
 */
public class Metrics extends Feature {

    @FeatureAnnotation(key = "nbFiles", name = "# Files")
    private int nbFiles = 0;

    @FeatureAnnotation(key = "nbModifiedClasses", name = "# Modified Classes")
    private int nbModifiedClasses = 0;

    @FeatureAnnotation(key = "nbModifiedMethods", name = "# Modified Methods")
    private int nbModifiedMethods = 0;

    @FeatureAnnotation(key = "addedLines", name = "Added Lines")
    private int addedLines = 0;

    @FeatureAnnotation(key = "removedLines", name = "Removed Lines")
    private int removedLines = 0;

    @FeatureAnnotation(key = "modifiedLines", name = "Modified Lines")
    private int modifiedLines = 0;

    @FeatureAnnotation(key = "patchSize", name = "Patch Size")
    private int patchSize = 0;

    @FeatureAnnotation(key = "nbChunks", name = "# Chunks")
    private int nbChunks = 0;

    @FeatureAnnotation(key = "spreadingAllLines", name = "Spreading All Lines")
    private int spreadingAllLines = 0;

    @FeatureAnnotation(key = "spreadingCodeOnly", name = "Spreading Code Only")
    private int spreadingCodeOnly = 0;

}
