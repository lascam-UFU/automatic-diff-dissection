package fr.inria.spirals.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tdurieux
 */
public class Changes {
	List<Change> oldChanges = new ArrayList<>();
	List<Change> newChanges = new ArrayList<>();

	public void addNewChange(Change change) {
		newChanges.add(change);
	}

	public List<Change> getNewChanges() {
		return newChanges;
	}

	public void addOldChange(Change change) {
		oldChanges.add(change);
	}

	public List<Change> getOldChanges() {
		return oldChanges;
	}

	public List<String> getChangedOldFiles() {
		Set<String> files = new HashSet<>();
		for (int i = 0; i < oldChanges.size(); i++) {
			Change change = oldChanges.get(i);
			files.add(change.getFile());
		}
		return new ArrayList<>(files);
	}

	public List<String> getChangedNewFiles() {
		Set<String> files = new HashSet<>();
		for (int i = 0; i < newChanges.size(); i++) {
			Change change = newChanges.get(i);
			files.add(change.getFile());
		}
		return new ArrayList<>(files);
	}
}