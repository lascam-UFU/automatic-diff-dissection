package fr.inria.spirals.features;

import fr.inria.spirals.entities.RepairActions;
import fr.inria.spirals.main.Config;
import fr.inria.spirals.main.Constants;

/**
 * Created by tdurieux
 */
public class ExtractorResults {
    private RepairActions oldRepairActions;
    private RepairActions newRepairActions;

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

    public String toCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(Config.getInstance().getProject()).append(Constants.CSV_SEPARATOR);
        sb.append(Config.getInstance().getBugId()).append(Constants.CSV_SEPARATOR);
        // nb removed lines
        sb.append(getOldRepairActions().getNbChange()).append(Constants.CSV_SEPARATOR);
        // nb added lines
        sb.append(getNewRepairActions().getNbChange()).append(Constants.CSV_SEPARATOR);

        sb.append(getOldRepairActions().toCSV());
        sb.append(getNewRepairActions().toCSV());
        return sb.toString();
    }

}
