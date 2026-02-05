package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    public static class Foot {
        private final double value1;
        private final double value2;


        public Foot(double value1, double value2) {
            this.value1 = value1;
            this.value2 = value2;
        }
    }

    @Test

    public void testFeetEquality_SameValue() {

        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0, 2.0);
        QuantityMeasurementApp.Feet f2 = new QuantityMeasurementApp.Feet(1.0, 2.0);

        assertEquals(f1, f2); //same value

    }

    @Test
    public void testFeetEquality_DifferentValue() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0, 2.0);
        QuantityMeasurementApp.Feet f3 = new QuantityMeasurementApp.Feet(3.0, 4.0);

        assertNotEquals(f1, f3); // different value
    }

    @Test
    public void testFeetEquality_NullComparision() {
        QuantityMeasurementApp.Feet f1 = null;
        assertNull(f1, "object is null"); // null check
    }

    @Test
    public void testFeetEquality_SameReference() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0, 2.0);
        QuantityMeasurementApp.Feet f2 = f1;

        assertSame(f1, f2); // same reference
    }

    @Test
    public void testFeetEquality_DifferentClass() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0, 2.0);
        Foot foot = new Foot(1.0, 2.0);

        assertNotSame(f1, foot, "not same class"); // different classes
    }

    @Test
    public void testInchesEquality_SameValue() {
        QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(1.0, 2.0);
        QuantityMeasurementApp.Inches i2 = new QuantityMeasurementApp.Inches(1.0, 2.0);

        assertEquals(i1, i2);
    }

    @Test
    public void testInchesEquality_DifferentValue() {
        QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(1.0, 2.0);
        QuantityMeasurementApp.Inches i2 = new QuantityMeasurementApp.Inches(2.0, 3.0);

        assertNotEquals(i1, i2);
    }

    @Test
    public void testInchesEquality_NullComparison() {
        QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(1.0, 2.0);

        assertNotEquals(null, i1);
    }

    @Test
    public void testInchesEquality_SameReference() {
        QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(1.0, 2.0);
        QuantityMeasurementApp.Inches i2 = i1;

        assertEquals(i1, i2);
    }

    @Test
    public void testInchesEquality_DifferentReference() {
        QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(1.0, 2.0);
        Foot f1 = new Foot(1.0, 2.0);

        assertNotEquals(i1, f1);
    }

}
