package fr.inria.spirals.features;

import fr.inria.spirals.entities.RepairActions;
import fr.inria.spirals.main.Constants;

/**
 * Created by tdurieux
 */
public class ExtractorResults {
    private RepairActions oldRepairActions;
    private RepairActions newRepairActions;
    private int nbFiles;
    private String project;
    private String bugId;

    public ExtractorResults(RepairActions oldRepairActions, RepairActions newRepairActions) {
        this.oldRepairActions = oldRepairActions;
        this.newRepairActions = newRepairActions;
    }

    public RepairActions getNewRepairActions() {
        return newRepairActions;
    }

    public RepairActions getOldRepairActions() {
        return oldRepairActions;
    }

    public int getNbFiles() {
        return nbFiles;
    }

    public void setNbFiles(int nbFiles) {
        this.nbFiles = nbFiles;
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

    public String toCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(project).append(Constants.CSV_SEPARATOR);
        sb.append(bugId).append(Constants.CSV_SEPARATOR);
        sb.append(nbFiles).append(Constants.CSV_SEPARATOR);
        // nb removed lines
        sb.append(getOldRepairActions().getNbChange()).append(Constants.CSV_SEPARATOR);
        // nb added lines
        sb.append(getNewRepairActions().getNbChange()).append(Constants.CSV_SEPARATOR);

        sb.append(getOldRepairActions().toCSV());
        sb.append(getNewRepairActions().toCSV());
        return sb.toString();
    }

}
