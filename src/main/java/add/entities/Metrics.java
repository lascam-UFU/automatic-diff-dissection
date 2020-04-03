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

    @FeatureAnnotation(key = "addedLinesAllLines", name = "Added Lines All Lines")
    private int addedLinesAllLines = 0;

    @FeatureAnnotation(key = "removedLinesAllLines", name = "Removed Lines All Lines")
    private int removedLinesAllLines = 0;

    @FeatureAnnotation(key = "modifiedLinesAllLines", name = "Modified Lines All Lines")
    private int modifiedLinesAllLines = 0;

    @FeatureAnnotation(key = "patchSizeAllLines", name = "Patch Size All Lines")
    private int patchSizeAllLines = 0;

    @FeatureAnnotation(key = "addedLinesCodeOnly", name = "Added Lines Code Only")
    private int addedLinesCodeOnly = 0;

    @FeatureAnnotation(key = "removedLinesCodeOnly", name = "Removed Lines Code Only")
    private int removedLinesCodeOnly = 0;

    @FeatureAnnotation(key = "modifiedLinesCodeOnly", name = "Modified Lines Code Only")
    private int modifiedLinesCodeOnly = 0;

    @FeatureAnnotation(key = "patchSizeCodeOnly", name = "Patch Size Code Only")
    private int patchSizeCodeOnly = 0;

    @FeatureAnnotation(key = "nbChunks", name = "# Chunks")
    private int nbChunks = 0;

    @FeatureAnnotation(key = "spreadingAllLines", name = "Spreading All Lines")
    private int spreadingAllLines = 0;

    @FeatureAnnotation(key = "spreadingCodeOnly", name = "Spreading Code Only")
    private int spreadingCodeOnly = 0;

    // new features from Matias & Zhongxing
    @FeatureAnnotation(key = "addedLines", name = "Added Lines")
    private int addedLines = 0;

    @FeatureAnnotation(key = "removedLines", name = "Removed Lines")
    private int removedLines = 0;

    @FeatureAnnotation(key = "modifiedLines", name = "Modified Lines")
    private int modifiedLines = 0;

    @FeatureAnnotation(key = "patchSize", name = "Patch Size")
    private int patchSize = 0;
}
