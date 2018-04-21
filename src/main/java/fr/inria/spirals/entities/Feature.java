package fr.inria.spirals.entities;

import fr.inria.spirals.main.Config;
import fr.inria.spirals.main.Constants;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tdurieux
 */
public abstract class Feature {

    private Config config;

    public void setConfig(Config config) {
        this.config = config;
    }

    public void incrementFeatureCounter(String key) {
        try {
            Field field  = this.getClass().getDeclaredField(key);
            field.setAccessible(true);
            int value = (int) field.get(this);
            field.set(this, value + 1);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new IllegalArgumentException("Feature not found: " + key, e);
        }
    }

    public void setFeatureCounter(String key, int value) {
        try {
            Field field  = this.getClass().getDeclaredField(key);
            field.setAccessible(true);
            field.set(this, value);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new IllegalArgumentException("Feature not found: " + key, e);
        }
    }

    public int getFeatureCounter(String key) {
        try {
            Field field = field = this.getClass().getDeclaredField(key);
            field.setAccessible(true);
            return (int) field.get(this);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new IllegalArgumentException("Feature not found" + key, e);
        }
    }

    public List<String> getFeatureNames() {
        List<String> output = new ArrayList<>();
        Field[] declaredFields = this.getClass().getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            FeatureAnnotation annotation = declaredFields[i].getAnnotation(FeatureAnnotation.class);
            if (annotation != null) {
                output.add(annotation.key());
            }
        }
        return output;
    }

    public String toCSV() {
        StringBuilder output = new StringBuilder();
        for (String featureName : getFeatureNames()) {
            output.append(featureName + Constants.CSV_SEPARATOR);
        }
        output.append(Constants.LINE_BREAK);
        for (String featureName : getFeatureNames()) {
            int counter = getFeatureCounter(featureName);
            output.append(counter + Constants.CSV_SEPARATOR);
        }
        return output.toString();
    }

    @Override
    public String toString() {
        JSONObject jsonObjectFeatures = new JSONObject();
        for (String featureName: getFeatureNames()) {
            int counter = getFeatureCounter(featureName);
            jsonObjectFeatures.put(featureName, counter);
        }
        JSONObject json = new JSONObject();
        json.put("bugId", this.config.getBugId());
        json.put(this.getClass().getSimpleName(), jsonObjectFeatures);
        return json.toString(4);
    }
}
