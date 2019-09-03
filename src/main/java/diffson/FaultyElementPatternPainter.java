package diffson;

import java.util.List;
import java.util.stream.Collectors;

import com.github.gumtreediff.tree.ITree;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import add.entities.PatternInstance;
import add.entities.PropertyPair;
import fr.inria.coming.utils.MapList;
import gumtree.spoon.builder.SpoonGumTreeBuilder;
import gumtree.spoon.builder.jsonsupport.NodePainter;
import spoon.reflect.declaration.CtElement;

public class FaultyElementPatternPainter implements NodePainter {

    MapList<String, String> nodesAffectedByPattern = new MapList<>();
    MapList<String, PatternInstance> nodesAffectedByPatternInstances = new MapList<>();
    String label = "susp";

    public FaultyElementPatternPainter(List<PatternInstance> instances) {
        // Collect all nodes and get the operator
    //    Boolean includeMetadata = PDDConfigurationProperties.getPropertyBoolean("include_pattern_metadata");

        Boolean includeMetadata = true;
        
        for (PatternInstance patternInstance : instances) {
            for (CtElement susp : patternInstance.getFaulty()) {
                String patternLabel = createPatternLabel(includeMetadata, patternInstance);
                String key = getKey(susp);
                if (!nodesAffectedByPattern.keySet().contains(key)
                        || !nodesAffectedByPattern.get(key).contains(patternLabel)) {
                    nodesAffectedByPattern.add(key, patternLabel);
                    nodesAffectedByPatternInstances.add(key, patternInstance);

                }

            }
        }
    }

    public String createPatternLabel(Boolean includeMetadata, PatternInstance patternInstance) {

        try {
            return "susp_" + patternInstance.getPatternName()
            // if include instance metadata (i.e., sub-category of patterns)
                    + ((// includeMetadata &&
                    !patternInstance.getMetadata().isEmpty()) ? ("_" + patternInstance.getMetadata().stream()
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
    public void paint(ITree tree, JsonObject jsontree) {

        CtElement ctelement = (CtElement) tree.getMetadata(SpoonGumTreeBuilder.SPOON_OBJECT);

        // Workaround
        if (jsontree.get("type").getAsString().equals("Modifiers")||jsontree.get("type").getAsString().equals("Modifier"))
            return;

        boolean found = paint(jsontree, ctelement);

        if (!found) {
            CtElement ctelementdsr = (CtElement) tree.getMetadata(SpoonGumTreeBuilder.SPOON_OBJECT_DEST);
            if (ctelementdsr != null)
                paint(jsontree, ctelementdsr);
        }
    }

    private boolean paint(JsonObject jsontree, CtElement ctelement) {
        boolean found = false;
        if (nodesAffectedByPattern.containsKey(getKey(ctelement))) {

            JsonArray labels = new JsonArray();
            List<String> patternsOfElement = nodesAffectedByPattern.get(getKey(ctelement));
            for (String pattern : patternsOfElement) {
                JsonPrimitive prim = new JsonPrimitive(pattern);
                labels.add(prim);

            }
            jsontree.add(this.label, labels);
            found = true;
        }
        return found;
    }

}
