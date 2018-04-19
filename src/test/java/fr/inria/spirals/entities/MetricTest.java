package fr.inria.spirals.entities;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tdurieux
 */
public class MetricTest {

    @Test
    public void testGetMetrics() {
        RepairActions actions = new RepairActions();
        assertEquals(50, actions.getMetricNames().size());
    }

    @Test
    public void testIncrementMetric() {
        RepairActions actions = new RepairActions();
        actions.incrementMetric("assignExpChange");
        assertEquals(1, actions.getMetric("assignExpChange"));
    }

    @Test
    public void testSetMetric() {
        Metrics metrics = new Metrics();
        metrics.setMetric("nbFiles", 1);
        assertEquals(1, metrics.getMetric("nbFiles"));
    }

}