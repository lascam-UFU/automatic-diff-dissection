package fr.inria.spirals.features.pattern.spoonPatternMatcher;

import fr.inria.spirals.entities.MetricAnnotation;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtExpression;
import spoon.template.TemplateParameter;

/**
 * Created by tdurieux
 */
public class MissingNullCheckPattern {
    public TemplateParameter<Boolean> _bool_;
    public TemplateParameter<Exception> _ex_;
    public TemplateParameter<CtExpression> _col_;
    public TemplateParameter<CtBlock> _body_;

    @MetricAnnotation(key = "condBlockRet")
    public Object conditionalBlockReturn() {
        if (_bool_.S()) {
            return _col_.S();
        }
        return null;
    }
    @MetricAnnotation(key = "condBlockExc")
    public void conditionalBlockException() throws Exception {
        if (_bool_.S()) {
            throw _ex_.S();
        }
    }

    @MetricAnnotation(key = "wrapsIf")
    public void wrapCond() {
        if (_bool_.S()) {}
    }

    @MetricAnnotation(key = "condBlockOthers")
    public void condBlockOthers() {
        if (_bool_.S()) _body_.S();
    }

    @MetricAnnotation(key = "wrapsIfElse")
    public void wrapsIfElse() {
        if (_bool_.S())
            _body_.S();
        else
            _body_.S();
    }

    @MetricAnnotation(key = "wrapsLoop")
    public void wrapsWhile() {
        while (_bool_.S()) {}
    }

    @MetricAnnotation(key = "wrapsLoop")
    public void wrapsFor() {
        for (_ex_.S();_bool_.S();_ex_.S()) {}
    }

    @MetricAnnotation(key = "missNullCheckN")
    public void notNull() {
        if (_col_.S() != null)
            _body_.S();
    }

    @MetricAnnotation(key = "missNullCheckP")
    public void isNull() {
        if (_col_.S() == null)
            _body_.S();
    }
}
