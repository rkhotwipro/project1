package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    public static class Foot{
        private final double value1;
        private final double value2;


        public Foot(double value1, double value2) {
            this.value1 = value1;
            this.value2 = value2;
        }
    }

    @Test
    public void testFESameValue(){
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.11, 2.22);
        QuantityMeasurementApp.Feet feet2 = new QuantityMeasurementApp.Feet(1.11, 2.22);

        //same value
        assertEquals(feet1, feet2);
    }

    @Test
    public void testFEDifferentValue(){
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0, 2.0);
        QuantityMeasurementApp.Feet f3 = new QuantityMeasurementApp.Feet(3.0, 4.0);

        assertNotEquals(f1, f3); // different value
    }

    @Test
    public void testFENullCheck(){
        QuantityMeasurementApp.Feet f1 = null;
        assertNull(f1, "feet object is null");
    }

    @Test
    public void testFESameReference(){
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0, 2.0);
        QuantityMeasurementApp.Feet f2 = f1;

        assertSame(f1, f2);
    }

    @Test
    public void testFEDifferentClass(){
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0, 2.0);
        Foot foot = new Foot(1.0, 2.0);

        assertNotSame(f1, foot, "two classes are different"); // different classes
    }

}
