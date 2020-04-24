package diffson;

import add.features.detector.spoon.LogicalExpressionAnalyzer;
import com.github.gumtreediff.tree.ITree;
import com.google.gson.JsonObject;
import gumtree.spoon.builder.SpoonGumTreeBuilder;
import gumtree.spoon.builder.jsonsupport.NodePainter;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtExpression;
import spoon.reflect.declaration.CtElement;

import java.util.List;

/**
 * @author Matias Martinez
 */
public class ReturnTypePainter implements NodePainter {

    private List<CtExpression> allRootLogicalExpressions;
    private List<CtExpression> allExpressions;
    private List<CtBinaryOperator> allBinOperators;

    public ReturnTypePainter(CtElement faultyLine) {
        allRootLogicalExpressions = LogicalExpressionAnalyzer.getAllRootLogicalExpressions(faultyLine);
        allExpressions = LogicalExpressionAnalyzer.getAllExpressions(faultyLine);
        allBinOperators = LogicalExpressionAnalyzer.getAllBinaryOperators(faultyLine);
    }

    public List<CtExpression> getRootLogicalExpressions() {
        return this.allRootLogicalExpressions;
    }

    public List<CtExpression> getAllExpressions() {
        return this.allExpressions;
    }

    public List<CtBinaryOperator> getAllBinaryOperators() {
        return this.allBinOperators;
    }

    @Override
    public void paint(ITree tree, JsonObject jsontree) {

        CtElement ctelement = (CtElement) tree.getMetadata(SpoonGumTreeBuilder.SPOON_OBJECT);
        if (ctelement instanceof CtExpression) {
            String type = Object.class.getCanonicalName();
            CtExpression exp = (CtExpression) ctelement;
            if (LogicalExpressionAnalyzer.isBooleanExpression(exp)) {
                type = Boolean.class.getCanonicalName();
            } else if (exp.getType() != null) {
                type = exp.getType().getQualifiedName();
            }

            jsontree.addProperty("return_type", type);

            for (int index = 0; index < allExpressions.size(); index++) {
                CtExpression specificExpression = allExpressions.get(index);
                if (specificExpression.equals(exp)) {
                    jsontree.addProperty("index_of_exper", "expression_" + Integer.toString(index));
                    break;
                }
            }

            for (int index = 0; index < allBinOperators.size(); index++) {
                CtBinaryOperator specificBinOperator = allBinOperators.get(index);
                if (specificBinOperator.equals(exp)) {
                    jsontree.addProperty("index_of_binary_operator", "BinOperator_" + Integer.toString(index));
                    break;
                }
            }

            if (type.toLowerCase().equals("boolean") || type.toLowerCase().equals("java.lang.boolean")) {
                for (int index = 0; index < allRootLogicalExpressions.size(); index++) {
                    CtExpression specificLogicalexpression = allRootLogicalExpressions.get(index);
                    if (specificLogicalexpression.equals(exp)) {
                        jsontree.addProperty("index_of_logical_exper", "logical_expression_" + Integer.toString(index));
                        break;
                    }
                }
            }
        }

    }
}
