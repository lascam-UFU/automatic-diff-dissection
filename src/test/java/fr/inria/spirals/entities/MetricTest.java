package fr.inria.spirals.entities;

import org.junit.Test;

import static org.junit.Assert.*;

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
        actions.getMetric("assignExpChange");
        assertEquals(1, actions.getMetric("assignExpChange"));
    }
}