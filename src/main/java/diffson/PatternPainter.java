package diffson;

import java.util.List;

import com.github.gumtreediff.tree.ITree;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import fr.inria.coming.utils.MapList;
import gumtree.spoon.builder.jsonsupport.NodePainter;
import gumtree.spoon.diff.operations.Operation;

/**
 * 
 * @author Matias Martinez
 *
 */
public class PatternPainter implements NodePainter {

    MapList<ITree, String> nodesAffectedByPattern = new MapList<>();
    String label;

    public PatternPainter(MapList<Operation, String> patternsPerOp, String label) {
        // Collect all nodes and get the operator
        this.label = label;
        for (Operation operation : patternsPerOp.keySet()) {
            List<String> patterns = patternsPerOp.get(operation);
            for (String pattern : patterns) {

                nodesAffectedByPattern.add(operation.getAction().getNode(), pattern);

            }
        }
    }

    @Override
    public void paint(ITree tree, JsonObject jsontree) {

        if (nodesAffectedByPattern.containsKey(tree)) {

            JsonArray arr = new JsonArray();
            List<String> ps = nodesAffectedByPattern.get(tree);
            for (String p : ps) {
                JsonPrimitive prim = new JsonPrimitive(p);
                arr.add(prim);
            }
            jsontree.add(this.label, arr);
        }
    }

}
