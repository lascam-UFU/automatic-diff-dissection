package fr.inria.spirals.features.detector;

import fr.inria.spirals.entities.Metric;
import fr.inria.spirals.features.extractor.AstExtractor;
import gumtree.spoon.diff.Diff;

/**
 * Created by fermadeiral
 */
public abstract class AbstractEditScriptBasedDetector {

    protected Diff editScript;

    AbstractEditScriptBasedDetector() {
        AstExtractor extractor = new AstExtractor();
        this.editScript = extractor.extract();
    }

    abstract Metric detect();

}
