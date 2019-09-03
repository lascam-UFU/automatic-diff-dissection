package add.features.diffanalyzer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jgit.diff.Edit;

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

    public List<Change> getCombinedChanges() {
        List<Change> combinedChanges = new ArrayList<>();
        for (Change change : this.getNewChanges()) {
            if (change.getType() == Edit.Type.INSERT.name() || change.getType() == Edit.Type.REPLACE.name()) {
                combinedChanges.add(change);
            }
        }
        for (Change change : this.getOldChanges()) {
            if (change.getType() == Edit.Type.DELETE.name()) {
                combinedChanges.add(change);
            }
        }
        return combinedChanges;
    }

}
