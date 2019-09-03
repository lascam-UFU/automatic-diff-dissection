package add.entities;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import add.main.Config;
import add.main.Constants;

/**
 * Created by fermadeiral
 */
public class FeatureList {

    private List<Feature> featureList;
    private Config config;

    public FeatureList(Config config) {
        this.config = config;
        this.featureList = new ArrayList<>();
    }

    public void add(Feature feature) {
        this.featureList.add(feature);
        feature.setConfig(this.config);
    }

    public String toCSV() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < featureList.size(); i++) {
            Feature feature = featureList.get(i);
            for (String featureName : feature.getFeatureNames()) {
                output.append(featureName + Constants.CSV_SEPARATOR);
            }
        }
        output.append(Constants.LINE_BREAK);
        for (int i = 0; i < featureList.size(); i++) {
            Feature feature = featureList.get(i);
            for (String featureName : feature.getFeatureNames()) {
                int counter = feature.getFeatureCounter(featureName);
                output.append(counter + Constants.CSV_SEPARATOR);
            }
        }
        return output.toString();
    }

    public JSONObject toJson() {
        JSONObject mergedJSON = new JSONObject();
        for (int i = 0; i < featureList.size(); i++) {
            Feature feature = featureList.get(i);
            JSONObject jsonObject = feature.toJson();
            for (String key : JSONObject.getNames(jsonObject)) {
                mergedJSON.put(key, jsonObject.get(key));
            }
        }
        return mergedJSON;
    }

    @Override
    public String toString() {
        return toJson().toString(2);
    }

}
