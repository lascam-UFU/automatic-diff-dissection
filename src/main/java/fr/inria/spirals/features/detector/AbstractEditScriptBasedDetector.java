package fr.inria.spirals.features.detector;

import fr.inria.spirals.entities.Metric;
import gumtree.spoon.diff.Diff;

/**
 * Created by fermadeiral
 */
public abstract class AbstractEditScriptBasedDetector {

    protected Diff editScript;

    protected AbstractEditScriptBasedDetector() {
        AstExtractor extractor = new AstExtractor();
        this.editScript = extractor.extract();
    }

    protected abstract Metric detect();

}
