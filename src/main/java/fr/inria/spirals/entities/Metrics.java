package fr.inria.spirals.entities;

/**
 * Created by fermadeiral
 */
public class Metrics extends Metric {

    @MetricAnnotation(key = "nbFiles", name = "# Files")
    private int nbFiles = 0;

    @MetricAnnotation(key = "addedLines", name = "Added Lines")
    private int addedLines = 0;

    @MetricAnnotation(key = "removedLines", name = "Removed Lines")
    private int removedLines = 0;

    @MetricAnnotation(key = "modifiedLines", name = "Modified Lines")
    private int modifiedLines = 0;

    @MetricAnnotation(key = "patchSize", name = "Patch Size")
    private int patchSize = 0;

    @MetricAnnotation(key = "nbChunks", name = "# Chunks")
    private int nbChunks = 0;

    @MetricAnnotation(key = "spreadingAllLines", name = "Spreading All Lines")
    private int spreadingAllLines = 0;

    @MetricAnnotation(key = "spreadingCodeOnly", name = "Spreading Code Only")
    private int spreadingCodeOnly = 0;

}
