package fr.inria.spirals.features.detector.repairpatterns.spoonPatternMatcher;

import fr.inria.spirals.entities.FeatureAnnotation;
import spoon.reflect.code.CtBlock;
import spoon.template.TemplateParameter;

/**
 * Created by tdurieux
 */
public class WrapsWithPattern {
    public TemplateParameter<Boolean> _bool_;
    public TemplateParameter<Exception> _ex_;
    public TemplateParameter<CtBlock> _body_;

    @FeatureAnnotation(key = "wrapsIf")
    public void wrapCond() {
        if (_bool_.S()) {}
    }

    @FeatureAnnotation(key = "wrapsIfElse")
    public void wrapsIfElse() {
        if (_bool_.S())
            _body_.S();
        else
            _body_.S();
    }

    @FeatureAnnotation(key = "wrapsLoop")
    public void wrapsWhile() {
        while (_bool_.S()) {}
    }

    @FeatureAnnotation(key = "wrapsLoop")
    public void wrapsFor() {
        for (_ex_.S();_bool_.S();_ex_.S()) {}
    }

}
