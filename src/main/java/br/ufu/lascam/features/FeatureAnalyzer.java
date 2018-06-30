package br.ufu.lascam.features;

import br.ufu.lascam.entities.Feature;
import br.ufu.lascam.main.Config;

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
