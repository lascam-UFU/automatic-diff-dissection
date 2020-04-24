package diffson;

import add.entities.PatternInstance;
import add.entities.PropertyPair;
import com.github.gumtreediff.tree.ITree;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gumtree.spoon.builder.SpoonGumTreeBuilder;
import gumtree.spoon.builder.jsonsupport.NodePainter;
import spoon.reflect.declaration.CtElement;

import java.util.List;
import java.util.stream.Collectors;

public class FaultyElementPatternPainter implements NodePainter {

    private MapList<String, String> nodesAffectedByPattern = new MapList<>();
    private MapList<String, PatternInstance> nodesAffectedByPatternInstances = new MapList<>();
    private String label = "susp";

    public FaultyElementPatternPainter(List<PatternInstance> instances) {
        for (PatternInstance patternInstance : instances) {
            for (CtElement susp : patternInstance.getFaulty()) {
                String patternLabel = createPatternLabel(patternInstance);
                String key = getKey(susp);
                if (!nodesAffectedByPattern.containsKey(key) || !nodesAffectedByPattern.get(key).contains(patternLabel)) {
                    nodesAffectedByPattern.add(key, patternLabel);
                    nodesAffectedByPatternInstances.add(key, patternInstance);
                }
            }
        }
    }

    public String createPatternLabel(PatternInstance patternInstance) {
        try {
            return label + "_" + patternInstance.getPatternName()
                    // if include instance metadata (i.e., sub-category of patterns)
                    + ((!patternInstance.getMetadata().isEmpty()) ? ("_" + patternInstance.getMetadata().stream()
                    .map(PropertyPair::getValue).collect(Collectors.joining("_"))) : "");
        } catch (Exception e) {
            return "";
        }
    }

    private String getKey(CtElement susp) {
        try {
            return susp.toString() + "_" + susp.getPath();
        } catch (Exception e) {
            System.err.println("Problem Getting the key");
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void paint(ITree tree, JsonObject jsonTree) {
        CtElement ctelement = (CtElement) tree.getMetadata(SpoonGumTreeBuilder.SPOON_OBJECT);
        // Workaround
        if (jsonTree.get("type").getAsString().equals("Modifiers") || jsonTree.get("type").getAsString().equals("Modifier")) {
            return;
        }

        boolean found = paint(jsonTree, ctelement);
        if (!found) {
            CtElement ctElementDsr = (CtElement) tree.getMetadata(SpoonGumTreeBuilder.SPOON_OBJECT_DEST);
            if (ctElementDsr != null) {
                paint(jsonTree, ctElementDsr);
            }
        }
    }

    private boolean paint(JsonObject jsonTree, CtElement ctelement) {
        boolean found = false;
        if (nodesAffectedByPattern.containsKey(getKey(ctelement))) {
            JsonArray labels = new JsonArray();
            List<String> patternsOfElement = nodesAffectedByPattern.get(getKey(ctelement));
            for (String pattern : patternsOfElement) {
                JsonPrimitive prim = new JsonPrimitive(pattern);
                labels.add(prim);
            }
            jsonTree.add(this.label, labels);
            found = true;
        }
        return found;
    }
}
