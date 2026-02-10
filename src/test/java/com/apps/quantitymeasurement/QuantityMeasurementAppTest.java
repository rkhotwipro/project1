package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.QuantityMeasurementApp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {


    @Test
    public void testConversion_FeetToInches(){
        Length length = new Length(1.0, Length.LengthUnit.FEET);
        Length l1 = QuantityMeasurementApp.demonstrateLengthConversion(length, Length.LengthUnit.INCHES);
        assertEquals(12.0, l1.getValue());
    }

    @Test
    public void testConversion_InchesToFeet(){
        Length length = new Length(24.0, Length.LengthUnit.INCHES);
        Length l1 = QuantityMeasurementApp.demonstrateLengthConversion(length, Length.LengthUnit.FEET);
        assertEquals(2.0, l1.getValue());
    }

    @Test
    public void testConversion_YardToInches(){
        Length length = new Length(1.0, Length.LengthUnit.YARDS);
        Length l1 = QuantityMeasurementApp.demonstrateLengthConversion(length, Length.LengthUnit.INCHES);
        assertEquals(36.0, l1.getValue());
    }

    @Test
    public void testConversion_InchestoYard(){
        Length length = new Length(72.0, Length.LengthUnit.INCHES);
        Length l1 = QuantityMeasurementApp.demonstrateLengthConversion(length, Length.LengthUnit.YARDS);
        assertEquals(2.0, l1.getValue());
    }

    @Test
    public void testConversion_ZeroValue(){
        Length length = new Length(0.0, Length.LengthUnit.INCHES);
        Length l1 = QuantityMeasurementApp.demonstrateLengthConversion(length, Length.LengthUnit.YARDS);
        assertEquals(0.0, l1.getValue());
    }

    @Test
    public void testConversion_RoundTrip_PreservesValue(){
        Length length = new Length(1.0, Length.LengthUnit.YARDS);
        Length l1 = QuantityMeasurementApp.demonstrateLengthConversion(length, Length.LengthUnit.INCHES);
        Length l2 = QuantityMeasurementApp.demonstrateLengthConversion(l1, Length.LengthUnit.YARDS);
        assertEquals(36.0, l1.getValue());
        assertEquals(1.0, l2.getValue());
    }

    @Test
    public void testConversion_negativeValue(){
        Length length = new Length(-1.0, Length.LengthUnit.FEET);
        Length l1 = QuantityMeasurementApp.demonstrateLengthConversion(length, Length.LengthUnit.INCHES);
        assertEquals(-12.0, l1.getValue());
    }

    @Test
    public void testConversion_invalidUnit_Throws(){
        Length length = new Length(-1.0, Length.LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, ()->QuantityMeasurementApp.demonstrateLengthConversion(length, null));
    }

    @Test
    public void testConversion_NAN(){
        assertThrows(IllegalArgumentException.class, ()->QuantityMeasurementApp.demonstrateLengthConversion(
                new Length(Double.NaN, Length.LengthUnit.FEET), Length.LengthUnit.INCHES));
    }

    @Test
    public void testConversion_Infinite(){
        assertThrows(IllegalArgumentException.class, ()->QuantityMeasurementApp.demonstrateLengthConversion(
                new Length(Double.POSITIVE_INFINITY, Length.LengthUnit.FEET), Length.LengthUnit.INCHES));
    }

    @Test
    public void testConversion_PrecisonToTolerance(){
        Length length = new Length(300.0, Length.LengthUnit.CENTIMETERS);
        Length l1 = QuantityMeasurementApp.demonstrateLengthConversion(length, Length.LengthUnit.YARDS);
         Assertions.assertEquals(3.28084, l1.getValue(), 3.28085);
    }
}
