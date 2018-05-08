package fr.inria.spirals.features.detector.spoon.filter;

import spoon.reflect.code.CtCase;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtThrow;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.visitor.Filter;

/**
 * Created by fermadeiral
 */
public class ThrowInsideConditionalFilter implements Filter<CtThrow> {

    private CtElement ctElement;

    public ThrowInsideConditionalFilter(CtElement ctElement) {
        this.ctElement = ctElement;
    }

    @Override
    public boolean matches(CtThrow ctThrow) {
        if (ctElement instanceof CtIf && ctThrow.getParent(CtIf.class) == ctElement) {
            return true;
        }
        if (ctElement instanceof CtCase && ctThrow.getParent(CtCase.class) == ctElement) {
            return true;
        }
        return false;
    }

}
