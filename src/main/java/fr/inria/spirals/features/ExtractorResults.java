package fr.inria.spirals.features;

import fr.inria.spirals.main.Constants;

/**
 * Created by tdurieux
 */
public class ExtractorResults {
	private ChangeAnalyze oldAnalyze;
	private ChangeAnalyze newAnalyze;
	private int nbFiles;
	private String project;
	private String bugId;

	public ExtractorResults(ChangeAnalyze oldAnalyze, ChangeAnalyze newAnalyze) {
		this.oldAnalyze = oldAnalyze;
		this.newAnalyze = newAnalyze;
	}

	public ChangeAnalyze getNewAnalyze() {
		return newAnalyze;
	}

	public ChangeAnalyze getOldAnalyze() {
		return oldAnalyze;
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
		sb.append(getOldAnalyze().getNbChange()).append(Constants.CSV_SEPARATOR);
		// nb added lines
		sb.append(getNewAnalyze().getNbChange()).append(Constants.CSV_SEPARATOR);

		sb.append(getOldAnalyze().toCSV());
		sb.append(getNewAnalyze().toCSV());
		return sb.toString();
	}
}
