package diffson;

import java.util.List;

import com.github.gumtreediff.tree.ITree;
import com.google.gson.JsonObject;

import add.features.detector.spoon.LogicalExpressionAnalyzer;
import gumtree.spoon.builder.SpoonGumTreeBuilder;
import gumtree.spoon.builder.jsonsupport.NodePainter;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtExpression;
import spoon.reflect.declaration.CtElement;

/**
 * 
 * @author Matias Martinez
 *
 */
public class ReturnTypePainter implements NodePainter {
    
    List<CtExpression> allrootlogicalexpers;
    
    List<CtExpression> allExpressions;
    
    List<CtBinaryOperator> allBinOperators;

    public ReturnTypePainter(CtElement faultyLine) {
        allrootlogicalexpers = LogicalExpressionAnalyzer.getAllRootLogicalExpressions(faultyLine);
        allExpressions = LogicalExpressionAnalyzer.getAllExpressions(faultyLine);
        allBinOperators = LogicalExpressionAnalyzer.getAllBinaryOperators(faultyLine);
    }
    
    public List<CtExpression> getRootLogicalExpressions () {
        return this.allrootlogicalexpers;
    }
    
    public List<CtExpression> getAllExpressions () {
        return this.allExpressions;
    }

    public List<CtBinaryOperator> getAllBinaryOperators () {
        return this.allBinOperators;
    }
    
    @Override
    public void paint(ITree tree, JsonObject jsontree) {

        CtElement ctelement = (CtElement) tree.getMetadata(SpoonGumTreeBuilder.SPOON_OBJECT);
        if (ctelement != null && ctelement instanceof CtExpression) {
            String type = "java.lang.Object";
            CtExpression exp = (CtExpression) ctelement;
            if (LogicalExpressionAnalyzer.isBooleanExpression(exp)) {
                 type = Boolean.class.getCanonicalName();
            }
            else
                if (exp.getType() != null)
                    type = exp.getType().getQualifiedName();
            
            jsontree.addProperty("return_type", type);
            
            for(int index=0; index<allExpressions.size(); index++) {
                CtExpression specificExpression = allExpressions.get(index);
                if(specificExpression.equals(exp)) {
                    jsontree.addProperty("index_of_exper", "expression_"+Integer.toString(index));
                    break;
                }
            }
            
            for(int index=0; index<allBinOperators.size(); index++) {
                CtBinaryOperator specificBinOperator = allBinOperators.get(index);
                if(specificBinOperator.equals(exp)) {
                    jsontree.addProperty("index_of_binary_operator", "BinOperator_"+Integer.toString(index));
                    break;
                }
            }
            
            if(type.toLowerCase().equals("boolean") || type.toLowerCase().equals("java.lang.boolean")) {
                
                for(int index=0; index<allrootlogicalexpers.size(); index++) {
                    CtExpression specificlogicalexper=allrootlogicalexpers.get(index);
                    if(specificlogicalexper.equals(exp)) {
                        jsontree.addProperty("index_of_logical_exper", "logical_expression_"+Integer.toString(index));
                        break;
                    }
                }
            }
        }

    }
}
