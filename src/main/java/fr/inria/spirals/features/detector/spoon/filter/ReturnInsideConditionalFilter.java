package fr.inria.spirals.features.detector.spoon.filter;

import spoon.reflect.code.*;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.visitor.Filter;

/**
 * Created by fermadeiral
 */
public class ReturnInsideConditionalFilter implements Filter<CtReturn> {

    private CtElement ctElement;

    public ReturnInsideConditionalFilter(CtElement ctElement) {
        this.ctElement = ctElement;
    }

    @Override
    public boolean matches(CtReturn ctReturn) {
        if (this.ctElement instanceof CtIf && ctReturn.getParent(CtIf.class) == this.ctElement) {
            return true;
        }
        if (this.ctElement instanceof CtCase && ctReturn.getParent(CtCase.class) == this.ctElement) {
            return true;
        }
        return false;
    }

}
