package fr.inria.spirals.entities;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class Metric {
    public void incrementMetric(String key) {
        try {
            Field field  = this.getClass().getDeclaredField(key);
            field.setAccessible(true);
            int value = (int) field.get(this);
            field.set(this, value + 1);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new IllegalArgumentException("Metric not found: " + key, e);
        }
    }

    public int getMetric(String key) {
        try {
            Field field = field = this.getClass().getDeclaredField(key);
            field.setAccessible(true);
            return (int) field.get(this);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new IllegalArgumentException("Metric not found", e);
        }
    }

    public List<String> getMetricNames() {
        List<String> output = new ArrayList<>();
        Field[] declaredFields = this.getClass().getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            MetricAnnotation annotation = declaredFields[i].getAnnotation(MetricAnnotation.class);
            if (annotation != null) {
                output.add(annotation.key());
            }
        }
        return output;
    }

    public String toCSV() {
        StringBuilder output = new StringBuilder();
        for (String metricName: getMetricNames()) {
            output.append(metricName + "\t");
        }
        for (String metricName: getMetricNames()) {
            int metric = getMetric(metricName);
            output.append(metric + "\t");
        }
        output.append("\n");
        return output.toString();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (String metricName: getMetricNames()) {
            int metric = getMetric(metricName);
            if (metric != 0) {
                output.append(metricName + " " + metric + "\n");
            }
        }
        return output.toString();
    }
}
