package fr.inria.spirals.entities;

import fr.inria.spirals.main.Constants;

/**
 * Created by fermadeiral
 */
public class Metrics extends Metric {

    private int nbFiles;
    private int addedLines;
    private int removedLines;
    private int modifiedLines;
    private int patchSize;
    private int nbChunks;
    private int spreadingAllLines;
    private int spreadingCodeOnly;

    public Metrics() {
        this.nbFiles = 0;
        this.addedLines = 0;
        this.removedLines = 0;
        this.modifiedLines = 0;
        this.patchSize = 0;
        this.nbChunks = 0;
        this.spreadingAllLines = 0;
        this.spreadingCodeOnly = 0;
    }

    public int getNbFiles() {
        return nbFiles;
    }

    public void setNbFiles(int nbFiles) {
        this.nbFiles = nbFiles;
    }

    public int getAddedLines() {
        return addedLines;
    }

    public void setAddedLines(int addedLines) {
        this.addedLines = addedLines;
    }

    public int getRemovedLines() {
        return removedLines;
    }

    public void setRemovedLines(int removedLines) {
        this.removedLines = removedLines;
    }

    public int getModifiedLines() {
        return modifiedLines;
    }

    public void setModifiedLines(int modifiedLines) {
        this.modifiedLines = modifiedLines;
    }

    public int getPatchSize() {
        return patchSize;
    }

    public void setPatchSize(int patchSize) {
        this.patchSize = patchSize;
    }

    public int getNbChunks() {
        return nbChunks;
    }

    public void setNbChunks(int nbChunks) {
        this.nbChunks = nbChunks;
    }

    public int getSpreadingAllLines() {
        return spreadingAllLines;
    }

    public void setSpreadingAllLines(int spreadingAllLines) {
        this.spreadingAllLines = spreadingAllLines;
    }

    public int getSpreadingCodeOnly() {
        return spreadingCodeOnly;
    }

    public void setSpreadingCodeOnly(int spreadingCodeOnly) {
        this.spreadingCodeOnly = spreadingCodeOnly;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Files: "+this.nbFiles).append(Constants.CSV_SEPARATOR);

        sb.append("Added Lines: "+this.addedLines).append(Constants.CSV_SEPARATOR);
        sb.append("Removed Lines: "+this.removedLines).append(Constants.CSV_SEPARATOR);
        sb.append("Modified Lines: "+this.modifiedLines).append(Constants.CSV_SEPARATOR);
        sb.append("Patch Size: "+this.patchSize).append(Constants.CSV_SEPARATOR);

        sb.append("Chunks: "+this.nbChunks).append(Constants.CSV_SEPARATOR);

        sb.append("Spreading All Lines: "+this.spreadingAllLines).append(Constants.CSV_SEPARATOR);
        sb.append("Spreading Code Only: "+this.spreadingCodeOnly).append(Constants.CSV_SEPARATOR);

        return sb.toString();
    }

}
