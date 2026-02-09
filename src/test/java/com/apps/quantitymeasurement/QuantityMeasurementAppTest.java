package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {
    @Test
    public void testFeetEquality() {
        QuantityLength l1 = new QuantityLength(1, QuantityLength.LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(1, QuantityLength.LengthUnit.FEET);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    public void testInchesEquality() {
        QuantityLength l1 = new QuantityLength(12, QuantityLength.LengthUnit.INCHES);
        QuantityLength l2 = new QuantityLength(12, QuantityLength.LengthUnit.INCHES);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    public void testFeetInchesComparison() {
        QuantityLength l1 = new QuantityLength(1, QuantityLength.LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12, QuantityLength.LengthUnit.INCHES);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    public void testInchesToFeetComparison() {
        QuantityLength l1 = new QuantityLength(12, QuantityLength.LengthUnit.INCHES);
        QuantityLength l2 = new QuantityLength(1, QuantityLength.LengthUnit.FEET);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    public void testFeetInequality() {
        QuantityLength l1 = new QuantityLength(1, QuantityLength.LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12, QuantityLength.LengthUnit.FEET);

        assertFalse(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    public void testInchesInequality() {
        QuantityLength l1 = new QuantityLength(1, QuantityLength.LengthUnit.INCHES);
        QuantityLength l2 = new QuantityLength(12, QuantityLength.LengthUnit.INCHES);

        assertFalse(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    public void testNullValue() {
        QuantityLength l1 = new QuantityLength(1, QuantityLength.LengthUnit.INCHES);

        assertFalse(QuantityMeasurementApp.demonstrateLengthEquality(l1, null));
    }

    @Test
    public void testCrossUnitInequality() {
        QuantityLength l1 = new QuantityLength(12, QuantityLength.LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(1, QuantityLength.LengthUnit.INCHES);

        assertFalse(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    public void testMultipleFeetComparison() {
        QuantityLength l1 = new QuantityLength(1, QuantityLength.LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(1, QuantityLength.LengthUnit.FEET);
        QuantityLength l3 = new QuantityLength(1, QuantityLength.LengthUnit.FEET);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(l2, l3));
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(l1, l3));
    }

    //UC 4

    @Test
    public void testEquality_YardToYard_SameValue() {
        QuantityLength l1 = new QuantityLength(1.0, QuantityLength.LengthUnit.YARDS);
        QuantityLength l2 = new QuantityLength(1.0, QuantityLength.LengthUnit.YARDS);

        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(l1, l2));
    }

    @Test
    public void testEquality_YardToYard_DifferentValue() {
        QuantityLength l1 = new QuantityLength(1.0, QuantityLength.LengthUnit.YARDS);
        QuantityLength l2 = new QuantityLength(2.0, QuantityLength.LengthUnit.YARDS);

        assertFalse(QuantityMeasurementApp.demonstrateLengthComparison(l1, l2));
    }

    @Test
    public void testEquality_YardToFeet_EquivalantValue() {
        QuantityLength l1 = new QuantityLength(1.0, QuantityLength.LengthUnit.YARDS);
        QuantityLength l2 = new QuantityLength(3.0, QuantityLength.LengthUnit.FEET);

        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(l1, l2));
    }

    @Test
    public void testEquality_FeetToYard_EquivalantValue() {
        QuantityLength l1 = new QuantityLength(3.0, QuantityLength.LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(1.0, QuantityLength.LengthUnit.YARDS);

        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(l1, l2));
    }

    @Test
    public void testEquality_YardToInches_EquivalantValue() {
        QuantityLength l1 = new QuantityLength(36.0, QuantityLength.LengthUnit.INCHES);
        QuantityLength l2 = new QuantityLength(1.0, QuantityLength.LengthUnit.YARDS);

        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(l1, l2));
    }

    @Test
    public void testEquality_InchestoYard_EquivalantValue() {
        QuantityLength l1 = new QuantityLength(36.0, QuantityLength.LengthUnit.INCHES);
        QuantityLength l2 = new QuantityLength(1.0, QuantityLength.LengthUnit.YARDS);

        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(l1, l2));
    }

    @Test
    public void testEquality_CentimetersToInches_EquivalantValue() {
        QuantityLength l1 = new QuantityLength(1.0, QuantityLength.LengthUnit.CENTIMETERS);
        QuantityLength l2 = new QuantityLength(0.393701, QuantityLength.LengthUnit.INCHES);

        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(l1, l2));
    }

    @Test
    public void testEquality_CentimetersToFeet_NonEquivalantValue() {
        QuantityLength l1 = new QuantityLength(1.0, QuantityLength.LengthUnit.CENTIMETERS);
        QuantityLength l2 = new QuantityLength(1.0, QuantityLength.LengthUnit.FEET);

        assertFalse(QuantityMeasurementApp.demonstrateLengthComparison(l1, l2));
    }

    @Test
    public void testEquality_MultiUnit_TransitiveProperty() {
        QuantityLength l1 = new QuantityLength(3.0, QuantityLength.LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(36.0, QuantityLength.LengthUnit.INCHES);
        QuantityLength l3 = new QuantityLength(1, QuantityLength.LengthUnit.YARDS);

        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(l1, l2));
        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(l2, l3));
        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(l1, l3));
    }


    @Test
    public void testEquality_YardWithNullComparison() {
        QuantityLength l1 = new QuantityLength(1.0, QuantityLength.LengthUnit.YARDS);
        QuantityLength l2 = null;

        assertFalse(QuantityMeasurementApp.demonstrateLengthComparison(l1, l2));
    }

    @Test
    public void testEquality_YardSameReference() {
        QuantityLength l1 = new QuantityLength(1.0, QuantityLength.LengthUnit.YARDS);
        QuantityLength l2 = l1;

        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(l1, l2));
    }

    @Test
    public void testEquality_CentimeterSameReference() {
        QuantityLength l1 = new QuantityLength(1.0, QuantityLength.LengthUnit.CENTIMETERS);
        QuantityLength l2 = l1;

        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(l1, l2));
    }

    @Test
    public void testEquality_CentimeterWithNullComparison() {
        QuantityLength l1 = new QuantityLength(1.0, QuantityLength.LengthUnit.CENTIMETERS);
        QuantityLength l2 = null;

        assertFalse(QuantityMeasurementApp.demonstrateLengthComparison(l1, l2));
    }

    @Test
    public void testEquality_AllUnit_ComplexScenario() {
        QuantityLength l1 = new QuantityLength(2.0, QuantityLength.LengthUnit.YARDS);
        QuantityLength l2 = new QuantityLength(6.0, QuantityLength.LengthUnit.FEET);
        QuantityLength l3 = new QuantityLength(72, QuantityLength.LengthUnit.INCHES);
        QuantityLength l4 = new QuantityLength(182.88, QuantityLength.LengthUnit.CENTIMETERS);

        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(l1, l2));
        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(l1, l3));
        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(l1, l4));

        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(l4, l2));
        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(l4, l3));
        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(l3, l1));
    }
}
