package fr.inria.spirals.features;

import fr.inria.spirals.entities.Feature;
import fr.inria.spirals.main.Config;

/**
 * Created by fermadeiral
 */
public abstract class FeatureAnalyzer {

    protected Config config;

    protected FeatureAnalyzer(Config config) {
        this.config = config;
    }

    public abstract Feature analyze();

}
