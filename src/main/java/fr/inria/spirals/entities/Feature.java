package fr.inria.spirals.entities;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tdurieux
 */
public abstract class Feature {
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
        for (String featureName: getFeatureNames()) {
            output.append(featureName + "\t");
        }
        for (String featureName: getFeatureNames()) {
            int counter = getFeatureCounter(featureName);
            output.append(counter + "\t");
        }
        output.append("\n");
        return output.toString();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (String featureName: getFeatureNames()) {
            int counter = getFeatureCounter(featureName);
            if (counter != 0) {
                output.append(featureName + " " + counter + "\n");
            }
        }
        return output.toString();
    }
}
