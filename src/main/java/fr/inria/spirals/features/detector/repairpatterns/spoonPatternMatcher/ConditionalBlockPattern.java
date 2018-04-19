package fr.inria.spirals.features.detector.repairpatterns.spoonPatternMatcher;

import fr.inria.spirals.entities.FeatureAnnotation;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtExpression;
import spoon.template.TemplateParameter;

/**
 * Created by tdurieux
 */
public class ConditionalBlockPattern {
    public TemplateParameter<Boolean> _bool_;
    public TemplateParameter<Exception> _ex_;
    public TemplateParameter<CtExpression> _col_;
    public TemplateParameter<CtBlock> _body_;

    @FeatureAnnotation(key = "condBlockRet")
    public Object conditionalBlockReturn() {
        if (_bool_.S()) {
            return _col_.S();
        }
        return null;
    }
    @FeatureAnnotation(key = "condBlockExc")
    public void conditionalBlockException() throws Exception {
        if (_bool_.S()) {
            throw _ex_.S();
        }
    }

    @FeatureAnnotation(key = "condBlockOthers")
    public void condBlockOthers() {
        if (_bool_.S()) _body_.S();
    }

}
