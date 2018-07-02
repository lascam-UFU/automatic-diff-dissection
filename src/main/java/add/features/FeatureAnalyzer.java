package add.features;

import add.entities.Feature;
import add.main.Config;

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
