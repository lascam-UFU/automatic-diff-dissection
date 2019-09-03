package add.entities;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import add.main.Config;
import add.main.Constants;

/**
 * Created by tdurieux
 */
public abstract class Feature {

    private Config config;

    @SuppressWarnings("rawtypes")

    public void setConfig(Config config) {
        this.config = config;
    }

    public void incrementFeatureCounter(String key) {
        try {
            Field field = this.getClass().getDeclaredField(key);
            field.setAccessible(true);
            int value = (int) field.get(this);
            field.set(this, value + 1);

        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new IllegalArgumentException("Feature not found: " + key, e);
        }
    }

    public void setFeatureCounter(String key, int value) {
        try {
            Field field = this.getClass().getDeclaredField(key);
            field.setAccessible(true);
            field.set(this, value);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new IllegalArgumentException("Feature not found: " + key, e);
        }
    }

    public int getFeatureCounter(String key) {
        try {
            Field field = this.getClass().getDeclaredField(key);
            if (int.class.isAssignableFrom(field.getType())) {
                field.setAccessible(true);
                return (int) field.get(this);
            }
            throw new IllegalArgumentException("Feature not found" + key);
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

    public JSONObject toJson() {
        JSONObject jsonObjectFeatures = new JSONObject();
        for (String featureName : getFeatureNames()) {
            int counter = getFeatureCounter(featureName);
            jsonObjectFeatures.put(featureName, counter);
        }
        JSONObject json = new JSONObject();
        if (config != null) {
            json.put("bugId", this.config.getBugId());
        }
        json.put(Character.toLowerCase(this.getClass().getSimpleName().charAt(0))
                + this.getClass().getSimpleName().substring(1), jsonObjectFeatures);
        return json;
    }

    @Override
    public String toString() {
        return toJson().toString(2);
    }

}
