package fr.inria.spirals.features.extractor;

import fr.inria.spirals.features.Change;
import fr.inria.spirals.features.Changes;
import fr.inria.spirals.features.DiffAnalyzer;
import fr.inria.spirals.main.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by tdurieux
 */
public class PositionExtractor extends AbstractExtractor {

	public PositionExtractor(String oldSourcePath, String newSourcePath, String diffPath) {
		super(oldSourcePath, newSourcePath, diffPath);
	}

	public void extract() {
		Changes changes = getChanges();
		getLocationAndSpreading(changes);
	}

	private Changes getChanges() {
		DiffAnalyzer diffAnalyzer = new DiffAnalyzer(diffPath);
		return diffAnalyzer.analyze();
	}

	/**
	 * Get the number of chunk in the patch
	 */
	public int nbChucks() {
		Changes changes = getChanges();

		int nbChuncks = 0;

		for (Change change : changes.getNewChanges()) {
			if (change.getLength() == 0) {
				continue;
			}
			if (change.getType().equals("INSERT")) {
				nbChuncks ++;
			} else if (change.getType().equals("REPLACE")) {
				nbChuncks ++;
			}
		}

		for (Change change : changes.getOldChanges()) {
			if (change.getLength() == 0) {
				continue;
			}
			if (change.getType().equals("DELETE")) {
				nbChuncks ++;
			}
		}

		return nbChuncks;
	}

	/**
	 * The limit of a patch
	 */
	public void getLimitOfPatch() {
		Changes changes = getChanges();

		Map<String, int[]> newLimits = getLimitOfPatch(changes.getNewChanges());
		Map<String, int[]> oldLimits = getLimitOfPatch(changes.getOldChanges());

		Set<String> files = new HashSet<>();
		files.addAll(newLimits.keySet());
		files.addAll(oldLimits.keySet());

		StringBuilder sb = new StringBuilder();

		for (String file : files) {
			int[] newfileLimits = newLimits.get(file);
			int[] oldfileLimits = oldLimits.get(file);

			sb.append(project).append(Constants.CSV_SEPARATOR);
			sb.append(bugId).append(Constants.CSV_SEPARATOR);
			sb.append(file).append(Constants.CSV_SEPARATOR);

			if (newfileLimits != null) {
				sb.append(newfileLimits[0]).append(Constants.CSV_SEPARATOR);
				sb.append(newfileLimits[1]).append(Constants.CSV_SEPARATOR);
			} else {
				sb.append(-1).append(Constants.CSV_SEPARATOR);
				sb.append(-1).append(Constants.CSV_SEPARATOR);
			}
			if (oldfileLimits != null) {
				sb.append(oldfileLimits[0]).append(Constants.CSV_SEPARATOR);
				sb.append(oldfileLimits[1]).append(Constants.CSV_SEPARATOR);
			} else {
				sb.append(-1).append(Constants.CSV_SEPARATOR);
				sb.append(-1).append(Constants.CSV_SEPARATOR);
			}

			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private Map<String, int[]> getLimitOfPatch(List<Change> cs) {
		Map<String, int[]> limits = new HashMap<>();

		for (Change c : cs) {
			String file = c.getClassName();

			if (!limits.containsKey(file)) {
				limits.put(file, new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE});
			}
			int[] fileLimit = limits.get(file);
			int min = fileLimit[0];
			int max = fileLimit[1];
			if (min > c.getLine()) {
				fileLimit[0] = c.getLine();
			}
			if (max < c.getEndLine()) {
				fileLimit[1] = c.getEndLine();
			}
		}

		return limits;
	}

	/**
	 * Count the number of line added, removed and modified in the patch
	 */
	public void countAddRemoveModify() {
		Changes changes = getChanges();

		int nbAdded = 0;
		int nbRemoved = 0;
		int nbModified = 0;

		for (Change change : changes.getNewChanges()) {
			if (change.getLength() == 0) {
				continue;
			}
			int changedLine = change.getEndLine() - change.getLine() + 1;
			switch (change.getType()) {
			case "REPLACE":
				int associateChangedLine = change.getAssociateChange().getEndLine() - change.getAssociateChange().getLine() + 1;
				nbModified += Math.max(changedLine, associateChangedLine);
				break;
			case "INSERT":
				nbAdded += changedLine;
				break;
			}
		}

		for (Change change : changes.getOldChanges()) {
			if (change.getLength() == 0) {
				continue;
			}
			int changedLine = change.getEndLine() - change.getLine() + 1;
			switch (change.getType()) {
			case "DELETE":
				nbRemoved += changedLine;
				break;
			}
		}





		int patchAddLines = 0;
		int patchModLines = 0;
		int patchRemLines = 0;

		for (Change change : changes.getNewChanges()) {
			int addLines = change.getLength();
			int remLines = change.getAssociateChange().getLength();

			int chunkModLines = 0;
			int chunkAddLines = 0;
			int chunkRemLines = 0;

			int diff = addLines - remLines;
			if (diff == 0) {
				chunkModLines = addLines;
			} else if (diff > 0 ) {
				chunkModLines = remLines;
				chunkAddLines = diff;
			} else {
				chunkModLines = addLines;
				chunkRemLines = remLines - addLines;
			}

			patchAddLines += chunkAddLines;
			patchModLines += chunkModLines;
			patchRemLines += chunkRemLines;
		}







		StringBuilder sb = new StringBuilder();
		sb.append(project).append(Constants.CSV_SEPARATOR);
		sb.append(bugId).append(Constants.CSV_SEPARATOR);


		sb.append(nbChucks()).append(Constants.CSV_SEPARATOR);


		sb.append(nbAdded).append(Constants.CSV_SEPARATOR);
		sb.append(nbRemoved).append(Constants.CSV_SEPARATOR);
		sb.append(nbModified).append(Constants.CSV_SEPARATOR);


		sb.append(patchAddLines).append(Constants.CSV_SEPARATOR);
		sb.append(patchRemLines).append(Constants.CSV_SEPARATOR);
		sb.append(patchModLines).append(Constants.CSV_SEPARATOR);

		sb.append(patchModLines + patchAddLines + patchRemLines).append(Constants.CSV_SEPARATOR);

		System.out.println(sb);
	}

	public void spreading2() {
		Changes changes = getChanges();

		Set<String> files = new HashSet<>();
		for (int i = 0; i < changes.getNewChanges().size(); i++) {
			Change change = changes.getNewChanges().get(i);
			files.add(change.getClassName());
		}
		for (int i = 0; i < changes.getOldChanges().size(); i++) {
			Change change = changes.getOldChanges().get(i);
			files.add(change.getClassName());
		}

		StringBuilder sb = new StringBuilder();



		for (String file : files) {
			int untouchedline = 0;
			int trimUntouchedLine = 0;
			int lastUntouchedline = 0;
			int lastTrimUntouchedLine = 0;

			sb.append(project).append(Constants.CSV_SEPARATOR);
			sb.append(bugId).append(Constants.CSV_SEPARATOR);
			sb.append(file).append(Constants.CSV_SEPARATOR);

			List<String> newFileContent = getFileContent(file, changes.getNewChanges(), this.fixedSourcePath);
			List<String> oldFileContent = getFileContent(file, changes.getOldChanges(), this.buggySourcePath);

			int lineDiff = 0;
			boolean first = false;

			int start = 0;
			for (int line = 0; line < oldFileContent.size(); line++) {
				String oldLine = oldFileContent.get(line);
				String newLine = newFileContent.get(line + lineDiff);

				if (oldLine.equals(newLine)) {
					if (!first) {
						continue;
					}
					untouchedline ++;
					if (!isComment(oldLine)) {
						trimUntouchedLine ++;
					}
					continue;
				}

				if (!first) {
					first = true;
				}

				lastUntouchedline = untouchedline;
				lastTrimUntouchedLine = trimUntouchedLine;
				Change change = getChange(changes, file, line + 1, line + lineDiff + 1);
				if (change.getType().equals("INSERT")) {
					lineDiff++;
					line --;
				} else if (change.getType().equals("DELETE")) {
					lineDiff--;
				} else if (change.getType().equals("REPLACE")) {
					if (change.getLength() != change.getAssociateChange().getLength()) {
						line += change.getLength() - 1;
						lineDiff += change.getAssociateChange().getLength() - change.getLength();
					}
				}
			}
			sb.append(lastUntouchedline).append(Constants.CSV_SEPARATOR);
			sb.append(lastTrimUntouchedLine).append(Constants.CSV_SEPARATOR);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private Change getChange(Changes changes, String file, int oldLine, int newLine) {
		for (int i = 0; i < changes.getOldChanges().size(); i++) {
			Change change = changes.getOldChanges().get(i);
			if (change.getLength() == 0) {
				continue;
			}
			if (change.getClassName().equals(file)) {
				if (change.getLine() <= oldLine && change.getEndLine() >= oldLine) {
					return change;
				}
			}
		}
		for (int i = 0; i < changes.getNewChanges().size(); i++) {
			Change change = changes.getNewChanges().get(i);
			if (change.getLength() == 0) {
				continue;
			}
			if (change.getClassName().equals(file)) {
				if (change.getLine() <= newLine && change.getEndLine() >= newLine) {
					return change;
				}
			}
		}
		return getChange(changes, file, oldLine - 1, newLine - 1);
	}

	public void spreading() {
		Changes changes = getChanges();


		Map<String, int[]> newLimits = getLimitOfPatch(changes.getNewChanges());
		Map<String, int[]> oldLimits = getLimitOfPatch(changes.getOldChanges());

		Set<String> files = new HashSet<>();
		files.addAll(newLimits.keySet());
		files.addAll(oldLimits.keySet());

		StringBuilder sb = new StringBuilder();

		for (String file : files) {
			int[] newfileLimits = newLimits.get(file);
			int[] oldfileLimits = oldLimits.get(file);

			sb.append(project).append(Constants.CSV_SEPARATOR);
			sb.append(bugId).append(Constants.CSV_SEPARATOR);
			sb.append(file).append(Constants.CSV_SEPARATOR);

			if (newfileLimits != null) {
				List<String> fileContent = getFileContent(file, changes.getNewChanges(), this.fixedSourcePath);

				int line = newfileLimits[0];
				int endLine = newfileLimits[1];

				int untouchedLine = endLine - line + 1;
				int trimUntouchedLine = endLine - line + 1;
				for (int i = line; i <= endLine; i++) {
					if (inChanges(file, i, changes.getNewChanges())) {
						untouchedLine --;
						trimUntouchedLine --;
					} else {
						String s = fileContent.get(i - 1).trim();
						if (isComment(s)) {
							trimUntouchedLine--;
						}
					}
				}
				sb.append(untouchedLine).append(Constants.CSV_SEPARATOR);
				sb.append(trimUntouchedLine).append(Constants.CSV_SEPARATOR);
			} else {
				sb.append(-1).append(Constants.CSV_SEPARATOR);
			}

			if (oldfileLimits != null) {
				List<String> fileContent = getFileContent(file, changes.getOldChanges(), this.buggySourcePath);

				int line = oldfileLimits[0];
				int endLine = oldfileLimits[1];

				int untouchedLine = endLine - line + 1;
				int trimUntouchedLine = endLine - line + 1;
				for (int i = line; i <= endLine; i++) {
					if (inChanges(file, i, changes.getOldChanges())) {
						untouchedLine --;
						trimUntouchedLine --;
					} else {
						String s = fileContent.get(i - 1).trim();
						if (isComment(s)) {
							trimUntouchedLine--;
						}
					}
				}
				sb.append(untouchedLine).append(Constants.CSV_SEPARATOR);
				sb.append(trimUntouchedLine).append(Constants.CSV_SEPARATOR);
			} else {
				sb.append(-1).append(Constants.CSV_SEPARATOR);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private boolean isComment(String s) {
		s = s.trim();
		return s.isEmpty() ||
				s.startsWith("//") ||
				s.startsWith("/*") ||
				s.startsWith("*/") ||
				s.startsWith("*");
	}

	private List<String> getFileContent(String filename, List<Change> changes, String root) {
		String pathFile = null;
		for (Change change : changes) {
			String file = change.getClassName();
			if (file.equals(filename)) {
				pathFile = change.getFile();
				break;
			}
		}

		try (BufferedReader br = new BufferedReader(new FileReader(new File("/" + pathFile)))) {
			List<String> output = new ArrayList<>();
			String line;
			while ((line = br.readLine()) != null) {
				output.add(line);
			}
			return output;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private boolean inChanges(String file, int line, List<Change> changes) {
		for (Change change : changes) {
			if (!change.getClassName().equals(file)) {
				continue;
			}
			if (change.getLength() == 0) {
				continue;
			}
			if (change.getLine() <= line && change.getEndLine() >= line) {
				return true;
			}
		}
		return false;
	}

	private void getLocationAndSpreading(Changes changes) {
		StringBuilder sb = new StringBuilder();

		Map<String, List<int[]>> locations = new HashMap<>();

		extractLocations(locations, changes.getNewChanges());
		// extractLocations(locations, changes.getOldChanges());

		sb.append("# ");
		sb.append(project);
		sb.append(" ");
		sb.append(bugId);
		sb.append("\n");
		sb.append("\n");
		boolean spreading = isSpreading(changes);
		if (spreading) {
			int nbChunk = changes.getNewChanges().size();
			sb.append(project);
			sb.append(" ");
			sb.append(bugId);
			sb.append(" is spreading on ");
			sb.append(locations.size());
			sb.append(" files and ");
			sb.append(nbChunk);
			sb.append(" chunks. \n\n");
		}
		for (String file : locations.keySet()) {
			File file1 = new File("/" + file);

			sb.append("## ");
			sb.append(file1.getName());
			sb.append("\n");
			sb.append("\n");

			try (LineNumberReader reader = new LineNumberReader(new FileReader(file1))) {
				while ((reader.readLine()) != null) ;
				sb.append("`");
				sb.append(file1.getName());
				sb.append("` contains `");
				sb.append(reader.getLineNumber());
				sb.append("` lines and ");
				sb.append(locations.get(file).size());
				sb.append(" chunks: \n");

				for (int i = 0; i < locations.get(file).size(); i++) {
					sb.append("\n");
					int[] interval = locations.get(file).get(i);
					sb.append("\t");
					sb.append(interval[0]);
					sb.append(" - ");
					sb.append(interval[1]);
				}
				sb.append("\n");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		sb.append("\n");
		System.out.println(sb.toString());
	}

	private boolean isSpreading(Changes changes) {
		return changes.getNewChanges().size() > 0;
	}

	private void extractLocations(Map<String, List<int[]>> locations, List<Change> changeList) {
		for (int i = 0; i < changeList.size(); i++) {
			Change change = changeList.get(i);

			String file = change.getFile();
			// file = file.substring(file.lastIndexOf("/"));
			if (!locations.containsKey(file)) {
				locations.put(file, new ArrayList<int[]>());
			}
			int[] interval = { change.getLine(), change.getEndLine() };
			if (!locations.get(file).contains(interval)) {
				locations.get(file).add(interval);
			}
		}
	}

}
