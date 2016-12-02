package fr.inria.spirals.features;

import org.eclipse.jgit.diff.Edit;
import org.eclipse.jgit.diff.EditList;
import org.eclipse.jgit.patch.FileHeader;
import org.eclipse.jgit.patch.HunkHeader;
import org.eclipse.jgit.patch.Patch;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class DiffAnalyzer {

	private int nbFiles;

	public Changes analyze(String diffPath) {
		Changes changes = new Changes();
		Patch patch = new Patch();
		try {
			patch.parse(new FileInputStream(diffPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.nbFiles = patch.getFiles().size();
		for (int i = 0; i < nbFiles; i++) {
			FileHeader fileHeader = patch.getFiles().get(i);

			List<? extends HunkHeader> hunks = fileHeader.getHunks();
			for (int j = 0; j < hunks.size(); j++) {
				HunkHeader hunkHeader = hunks.get(j);
				EditList edits = hunkHeader.toEditList();
				for (int k = 0; k < edits.size(); k++) {
					Edit edit = edits.get(k);
					changes.addOldChange(new Change(edit.getType().name(), fileHeader.getOldPath().trim(), edit.getBeginA() + 1, edit.getEndA(), edit.getLengthA()));
					changes.addNewChange(new Change(edit.getType().name(), fileHeader.getNewPath().trim(), edit.getBeginB() + 1, edit.getEndB(), edit.getLengthB()));
				}
			}
		}
		return changes;
	}

	// Helper method for get the file content
	private List<String> fileToLines(String filename) {
		List<String> lines = new LinkedList<String>();
		String line = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			while ((line = in.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	public int getNbFiles() {
		return nbFiles;
	}
}