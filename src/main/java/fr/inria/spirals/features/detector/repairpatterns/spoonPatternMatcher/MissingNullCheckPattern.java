package fr.inria.spirals.features.detector.repairpatterns.spoonPatternMatcher;

import fr.inria.spirals.entities.FeatureAnnotation;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtExpression;
import spoon.template.TemplateParameter;

/**
 * Created by tdurieux
 */
public class MissingNullCheckPattern {
    public TemplateParameter<CtExpression> _col_;
    public TemplateParameter<CtBlock> _body_;

    @FeatureAnnotation(key = "missNullCheckN")
    public void notNull() {
        if (_col_.S() != null)
            _body_.S();
    }

    @FeatureAnnotation(key = "missNullCheckP")
    public void isNull() {
        if (_col_.S() == null)
            _body_.S();
    }

}
