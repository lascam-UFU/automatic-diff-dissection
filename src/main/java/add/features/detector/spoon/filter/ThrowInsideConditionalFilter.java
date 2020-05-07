package add.features.detector.spoon.filter;

import spoon.reflect.code.CtBlock;
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
        CtElement ctElementParent = ctThrow.getParent();
        while (!(ctElementParent instanceof CtIf) && !(ctElementParent instanceof CtCase) && !(ctElementParent instanceof CtBlock)) {
            ctElementParent = ctElementParent.getParent();
        }
        if (ctElementParent == this.ctElement) {
            return true;
        }
        return false;
    }

}
