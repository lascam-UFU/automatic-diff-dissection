package add.features.detector.spoon.filter;

import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.visitor.Filter;

/**
 * Created by fermadeiral
 */
public class NullCheckFilter implements Filter<CtBinaryOperator> {

    public NullCheckFilter() {}

    @Override
    public boolean matches(CtBinaryOperator ctBinaryOperator) {
        BinaryOperatorKind operatorKind = ctBinaryOperator.getKind();
        if ((operatorKind.equals(BinaryOperatorKind.EQ) || operatorKind.equals(BinaryOperatorKind.NE)) &&
                ((ctBinaryOperator.getLeftHandOperand().toString().equals("null") || ctBinaryOperator.getRightHandOperand().toString().equals("null")))) {
            return true;
        }
        return false;
    }
}
