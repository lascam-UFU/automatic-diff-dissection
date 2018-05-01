package fr.inria.spirals.features.detector.spoon;

import spoon.reflect.declaration.CtVariable;

/**
 * Created by fermadeiral
 */
public class RepairPatternUtils {

    public static boolean isNewVariable(CtVariable variable) {
        if (variable.getMetadata("new") != null) {
            return true;
        }
        return false;
    }

}
