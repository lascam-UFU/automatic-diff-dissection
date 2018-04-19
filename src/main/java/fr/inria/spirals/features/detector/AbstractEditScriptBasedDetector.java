package fr.inria.spirals.features.detector;

import fr.inria.spirals.entities.Metric;
import fr.inria.spirals.features.extractor.AstExtractor;
import fr.inria.spirals.main.Config;
import gumtree.spoon.diff.Diff;

/**
 * Created by fermadeiral
 */
public abstract class AbstractEditScriptBasedDetector {

    protected Diff editScript;

    AbstractEditScriptBasedDetector() {
        AstExtractor extractor = new AstExtractor(Config.getInstance().getBuggySourceDirectoryPath(), Config.getInstance().getDiffPath());
        this.editScript = extractor.extract();
    }

    abstract Metric detect();

}
