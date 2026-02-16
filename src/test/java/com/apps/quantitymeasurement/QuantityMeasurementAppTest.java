package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.QuantityMeasurementApp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {


    @Test
    public void testConversion_FeetToInches() {
        Length length = new Length(1.0, LengthUnit.FEET);
        Length l1 = QuantityMeasurementApp.demonstrateLengthConversion(length, LengthUnit.INCHES);
        assertEquals(12.0, l1.getValue());
    }

    @Test
    public void testConversion_InchesToFeet() {
        Length length = new Length(24.0, LengthUnit.INCHES);
        Length l1 = QuantityMeasurementApp.demonstrateLengthConversion(length, LengthUnit.FEET);
        assertEquals(2.0, l1.getValue());
    }

    @Test
    public void testConversion_YardToInches() {
        Length length = new Length(1.0, LengthUnit.YARDS);
        Length l1 = QuantityMeasurementApp.demonstrateLengthConversion(length, LengthUnit.INCHES);
        assertEquals(36.0, l1.getValue());
    }

    @Test
    public void testConversion_InchestoYard() {
        Length length = new Length(72.0, LengthUnit.INCHES);
        Length l1 = QuantityMeasurementApp.demonstrateLengthConversion(length, LengthUnit.YARDS);
        assertEquals(2.0, l1.getValue());
    }

    @Test
    public void testConversion_ZeroValue() {
        Length length = new Length(0.0, LengthUnit.INCHES);
        Length l1 = QuantityMeasurementApp.demonstrateLengthConversion(length, LengthUnit.YARDS);
        assertEquals(0.0, l1.getValue());
    }

    @Test
    public void testConversion_RoundTrip_PreservesValue() {
        Length length = new Length(1.0, LengthUnit.YARDS);
        Length l1 = QuantityMeasurementApp.demonstrateLengthConversion(length, LengthUnit.INCHES);
        Length l2 = QuantityMeasurementApp.demonstrateLengthConversion(l1, LengthUnit.YARDS);
        assertEquals(36.0, l1.getValue());
        assertEquals(1.0, l2.getValue());
    }

    @Test
    public void testConversion_negativeValue() {
        Length length = new Length(-1.0, LengthUnit.FEET);
        Length l1 = QuantityMeasurementApp.demonstrateLengthConversion(length, LengthUnit.INCHES);
        assertEquals(-12.0, l1.getValue());
    }

    @Test
    public void testConversion_invalidUnit_Throws() {
        Length length = new Length(-1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> QuantityMeasurementApp.demonstrateLengthConversion(length, null));
    }

    @Test
    public void testConversion_NAN() {
        assertThrows(IllegalArgumentException.class, () -> QuantityMeasurementApp.demonstrateLengthConversion(
                new Length(Double.NaN, LengthUnit.FEET), LengthUnit.INCHES));
    }

    @Test
    public void testConversion_Infinite() {
        assertThrows(IllegalArgumentException.class, () -> QuantityMeasurementApp.demonstrateLengthConversion(
                new Length(Double.POSITIVE_INFINITY, LengthUnit.FEET), LengthUnit.INCHES));
    }

    @Test
    public void testConversion_PrecisonToTolerance() {
        Length length = new Length(300.0, LengthUnit.CENTIMETERS);
        Length l1 = QuantityMeasurementApp.demonstrateLengthConversion(length, LengthUnit.YARDS);
        Assertions.assertEquals(3.28084, l1.getValue(), 0.001);
    }

    //UC6

    @Test
    public void testAddition_SameUnit_FeetPlusFeet() {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(2, LengthUnit.FEET);
        assertEquals(3.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2).getValue());
    }

    @Test
    public void testAddition_CrossUnit_FeetPlusInches() {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);

        assertEquals(2.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2).getValue());
    }

    @Test
    public void testAddition_CrossUnit_InchesPlusFeet() {
        Length length1 = new Length(12.0, LengthUnit.INCHES);
        Length length2 = new Length(1.0, LengthUnit.FEET);

        assertEquals(24.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2).getValue());
    }

    @Test
    public void testAddition_CrossUnit_YardPlusFeet() {
        Length length1 = new Length(1.0, LengthUnit.YARDS);
        Length length2 = new Length(3.0, LengthUnit.FEET);
        assertEquals(2.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2).getValue());
    }

    /*   @Test
       public void testAddition_CrossUnit_CentimeterPlusInch(){
           Length length1 = new Length(2.54, LengthUnit.CENTIMETERS);
           Length length2 = new Length(1.0, LengthUnit.INCHES);

           assertEquals(5.08, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2).getValue());
       }*/
    @Test
    public void testAddition_Cummutative() {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        Length lengthCumm1 = QuantityMeasurementApp.demonstrateLengthAddition(length1, length2);

        Length length3 = new Length(12.0, LengthUnit.INCHES);
        Length length4 = new Length(1.0, LengthUnit.FEET);
        Length lengthCumm2 = QuantityMeasurementApp.demonstrateLengthAddition(length3, length4);

        assertEquals(4.0, QuantityMeasurementApp.demonstrateLengthAddition(lengthCumm1, lengthCumm2).getValue());
    }

    @Test
    public void testAddition_withZero() {
        Length length1 = new Length(5.0, LengthUnit.FEET);
        Length length2 = new Length(0.0, LengthUnit.INCHES);
        assertEquals(5.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2).getValue());
    }

    @Test
    public void testAddition_NegativeValues() {
        Length length1 = new Length(5.0, LengthUnit.FEET);
        Length length2 = new Length(-2.0, LengthUnit.FEET);

        assertEquals(3.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2).getValue());
    }

    @Test
    public void testAddition_NullSecondOperand() {
        Length length1 = new Length(5.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> QuantityMeasurementApp.demonstrateLengthAddition(length1, null));
    }


    @Test
    public void testAddition_LargeValue() {
        Length length1 = new Length(1e6, LengthUnit.FEET);
        Length length2 = new Length(1e6, LengthUnit.FEET);
        assertEquals(2e6, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2).getValue());

    }

    @Test
    public void testAddition_SmallValue() {
        Length length1 = new Length(0.001, LengthUnit.FEET);
        Length length2 = new Length(0.002, LengthUnit.FEET);
        assertEquals(0.003, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2).getValue(), 0.03);

    }

    //UC 7
    @Test
    public void testAddition_SameUnit_FeetPlusFeetOverride() {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(2, LengthUnit.FEET);
        assertEquals(3.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.FEET).getValue());
    }


    @Test
    public void testAddition_CrossUnit_FeetPlusInchesOverride() {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        assertEquals(2.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.FEET).getValue());
    }

    @Test
    public void testAddition_CrossUnit_InchesPlusFeetOverride() {
        Length length1 = new Length(12.0, LengthUnit.INCHES);
        Length length2 = new Length(1.0, LengthUnit.FEET);
        assertEquals(24.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.INCHES).getValue());
    }

    @Test
    public void testAddition_CrossUnit_YardPlusFeetOverride() {
        Length length1 = new Length(1.0, LengthUnit.YARDS);
        Length length2 = new Length(3.0, LengthUnit.FEET);
        assertEquals(2.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.YARDS).getValue());
    }

    @Test
    public void testAddition_CommutativeOverride() {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        Length lengthComm1 = QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.FEET);

        Length length3 = new Length(12.0, LengthUnit.INCHES);
        Length length4 = new Length(1.0, LengthUnit.FEET);
        Length lengthComm2 = QuantityMeasurementApp.demonstrateLengthAddition(length3, length4, LengthUnit.INCHES);

        assertEquals(4.0, QuantityMeasurementApp.demonstrateLengthAddition(lengthComm1, lengthComm2, LengthUnit.FEET).getValue());
    }

    @Test
    public void testAddition_withZeroOverride() {
        Length length1 = new Length(5.0, LengthUnit.FEET);
        Length length2 = new Length(0.0, LengthUnit.INCHES);
        assertEquals(5.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.FEET).getValue());
    }

    @Test
    public void testAddition_NegativeValuesOverride() {
        Length length1 = new Length(5.0, LengthUnit.FEET);
        Length length2 = new Length(-2.0, LengthUnit.FEET);
        assertEquals(3.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.FEET).getValue());
    }

    @Test
    public void testAddition_NullSecondOperandOverride() {
        Length length1 = new Length(5.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> QuantityMeasurementApp.demonstrateLengthAddition(length1, null));
    }


    @Test
    public void testAddition_LargeValueOverride() {
        Length length1 = new Length(1e6, LengthUnit.FEET);
        Length length2 = new Length(1e6, LengthUnit.FEET);
        assertEquals(2e6, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.FEET).getValue());
    }

    @Test
    public void testAddition_SmallValueOverride() {
        Length length1 = new Length(0.001, LengthUnit.FEET);
        Length length2 = new Length(0.002, LengthUnit.FEET);
        assertEquals(0.003, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.FEET).getValue(), 0.03);
    }

    @Test
    public void theLengthUnitEnum_FeetConstant() {
        assertEquals(12.0, LengthUnit.FEET.getConversionFactor());
    }

    @Test
    public void theLengthUnitEnum_InchesConstant() {
        assertEquals(1.0, LengthUnit.INCHES.getConversionFactor());
    }

    @Test
    public void theLengthUnitEnum_YardsConstant() {
        assertEquals(36.0, LengthUnit.YARDS.getConversionFactor());
    }

    @Test
    public void theLengthUnitEnum_CentimetersConstant() {
        assertEquals(0.393701, LengthUnit.CENTIMETERS.getConversionFactor());
    }

    @Test
    public void testConvertToBaseUnit_InchesToInches() {
        assertEquals(2.0, LengthUnit.INCHES.convertToBaseUnit(2.0));
    }

    @Test
    public void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(12.0, LengthUnit.FEET.convertToBaseUnit(1.0));
    }

    @Test
    public void testConvertToBaseUnit_YardsToInches() {
        assertEquals(36.0, LengthUnit.YARDS.convertToBaseUnit(1.0));
    }

    @Test
    public void testConvertToBaseUnit_CentimetersToInches() {
        assertEquals(12.0, LengthUnit.CENTIMETERS.convertToBaseUnit(30.48));
    }

    @Test
    public void testQuantityLength_Refactored_Equality() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        assertEquals(l1, l2);
    }

    @Test
    public void testQuantityLength_Refactored_ConvertTo() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = l1.convertTo(LengthUnit.INCHES);
        assertEquals(l1, l2);
    }

    @Test
    public void testQuantityLength_Refactored_Add() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        assertEquals(2.0, QuantityMeasurementApp.demonstrateLengthAddition(l1, l2).getValue());
    }

    @Test
    public void testQuantityLength_Refactored_AddWithTargetUnit() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        assertEquals(0.667,
                QuantityMeasurementApp.demonstrateLengthAddition(l1, l2, LengthUnit.YARDS).getValue(), 0.666);
    }

    @Test
    public void testQuantityLength_Refactored_nullUnit() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.demonstrateLengthAddition(l1, null, LengthUnit.YARDS));
    }

    @Test
    public void testQuantityLength_Refactored_InvalidValue() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.demonstrateLengthConversion(new Length(Double.NaN, LengthUnit.FEET), LengthUnit.INCHES));
    }
}
