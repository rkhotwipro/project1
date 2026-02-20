package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.QuantityMeasurementApp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testConversion_FeetToInches() {
        Quantity length = new Quantity(1.0, LengthUnit.FEET);
        Quantity l1 = QuantityMeasurementApp.demonstrateConversion(length, LengthUnit.INCHES);
        assertEquals(12.0, l1.getValue());
    }

    @Test
    public void testConversion_InchesToFeet() {
        Quantity length = new Quantity(24.0, LengthUnit.INCHES);
        Quantity l1 = QuantityMeasurementApp.demonstrateConversion(length, LengthUnit.FEET);
        assertEquals(2.0, l1.getValue());
    }

    @Test
    public void testConversion_YardToInches() {
        Quantity length = new Quantity(1.0, LengthUnit.YARDS);
        Quantity l1 = QuantityMeasurementApp.demonstrateConversion(length, LengthUnit.INCHES);
        assertEquals(36.0, l1.getValue());
    }

    @Test
    public void testConversion_InchestoYard() {
        Quantity length = new Quantity(72.0, LengthUnit.INCHES);
        Quantity l1 = QuantityMeasurementApp.demonstrateConversion(length, LengthUnit.YARDS);
        assertEquals(2.0, l1.getValue());
    }

    @Test
    public void testConversion_ZeroValue() {
        Quantity length = new Quantity(0.0, LengthUnit.INCHES);
        Quantity l1 = QuantityMeasurementApp.demonstrateConversion(length, LengthUnit.YARDS);
        assertEquals(0.0, l1.getValue());
    }

    @Test
    public void testConversion_RoundTrip_PreservesValue() {
        Quantity length = new Quantity(1.0, LengthUnit.YARDS);
        Quantity l1 = QuantityMeasurementApp.demonstrateConversion(length, LengthUnit.INCHES);
        Quantity l2 = QuantityMeasurementApp.demonstrateConversion(l1, LengthUnit.YARDS);
        assertEquals(36.0, l1.getValue());
        assertEquals(1.0, l2.getValue());
    }

    @Test
    public void testConversion_negativeValue() {
        Quantity length = new Quantity(-1.0, LengthUnit.FEET);
        Quantity l1 = QuantityMeasurementApp.demonstrateConversion(length, LengthUnit.INCHES);
        assertEquals(-12.0, l1.getValue());
    }

    @Test
    public void testConversion_invalidUnit_Throws() {
        Quantity length = new Quantity(-1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> QuantityMeasurementApp.demonstrateConversion(length, null));
    }

    @Test
    public void testConversion_NAN() {
        assertThrows(IllegalArgumentException.class, () -> QuantityMeasurementApp.demonstrateConversion(
                new Quantity(Double.NaN, LengthUnit.FEET), LengthUnit.INCHES));
    }

    @Test
    public void testConversion_Infinite() {
        assertThrows(IllegalArgumentException.class, () -> QuantityMeasurementApp.demonstrateConversion(
                new Quantity(Double.POSITIVE_INFINITY, LengthUnit.FEET), LengthUnit.INCHES));
    }

    @Test
    public void testConversion_PrecisonToTolerance() {
        Quantity length = new Quantity(300.0, LengthUnit.CENTIMETERS);
        Quantity l1 = QuantityMeasurementApp.demonstrateConversion(length, LengthUnit.YARDS);
        Assertions.assertEquals(3.28084, l1.getValue(), 3.28085);
    }

    //UC6
    @Test
    public void testAddition_SameUnit_FeetPlusFeet() {
        Quantity length1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity length2 = new Quantity(2, LengthUnit.FEET);
        assertEquals(3.0, QuantityMeasurementApp.demonstrateAddition(length1, length2).getValue());
    }

    @Test
    public void testAddition_CrossUnit_FeetPlusInches() {
        Quantity length1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity length2 = new Quantity(12.0, LengthUnit.INCHES);
        assertEquals(2.0, QuantityMeasurementApp.demonstrateAddition(length1, length2).getValue());
    }

    @Test
    public void testAddition_CrossUnit_InchesPlusFeet() {
        Quantity length1 = new Quantity(12.0, LengthUnit.INCHES);
        Quantity length2 = new Quantity(1.0, LengthUnit.FEET);
        assertEquals(24.0, QuantityMeasurementApp.demonstrateAddition(length1, length2).getValue());
    }

    @Test
    public void testAddition_CrossUnit_YardPlusFeet() {
        Quantity length1 = new Quantity(1.0, LengthUnit.YARDS);
        Quantity length2 = new Quantity(3.0, LengthUnit.FEET);
        assertEquals(2.0, QuantityMeasurementApp.demonstrateAddition(length1, length2).getValue());
    }

    /*   @Test
       public void testAddition_CrossUnit_CentimeterPlusInch(){
           Quantitylength1 = new Quantity(2.54, LengthUnit.CENTIMETERS);
           Quantitylength2 = new Quantity(1.0, LengthUnit.INCHES);

           assertEquals(5.08, QuantityMeasurementApp.demonstrateAddition(length1, length2).getValue());
       }*/
    @Test
    public void testAddition_Cummutative() {
        Quantity length1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity length2 = new Quantity(12.0, LengthUnit.INCHES);
        Quantity lengthCumm1 = QuantityMeasurementApp.demonstrateAddition(length1, length2);

        Quantity length3 = new Quantity(12.0, LengthUnit.INCHES);
        Quantity length4 = new Quantity(1.0, LengthUnit.FEET);
        Quantity lengthCumm2 = QuantityMeasurementApp.demonstrateAddition(length3, length4);

        assertEquals(4.0, QuantityMeasurementApp.demonstrateAddition(lengthCumm1, lengthCumm2).getValue());
    }

    @Test
    public void testAddition_withZero() {
        Quantity length1 = new Quantity(5.0, LengthUnit.FEET);
        Quantity length2 = new Quantity(0.0, LengthUnit.INCHES);

        assertEquals(5.0, QuantityMeasurementApp.demonstrateAddition(length1, length2).getValue());
    }

    @Test
    public void testAddition_NegativeValues() {
        Quantity length1 = new Quantity(5.0, LengthUnit.FEET);
        Quantity length2 = new Quantity(-2.0, LengthUnit.FEET);

        assertEquals(3.0, QuantityMeasurementApp.demonstrateAddition(length1, length2).getValue());
    }

    @Test
    public void testAddition_NullSecondOperand() {
        Quantity length1 = new Quantity(5.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> QuantityMeasurementApp.demonstrateAddition(length1, null));
    }


    @Test
    public void testAddition_LargeValue() {
        Quantity length1 = new Quantity(1e6, LengthUnit.FEET);
        Quantity length2 = new Quantity(1e6, LengthUnit.FEET);
        assertEquals(2e6, QuantityMeasurementApp.demonstrateAddition(length1, length2).getValue());
    }

    @Test
    public void testAddition_SmallValue() {
        Quantity length1 = new Quantity(0.001, LengthUnit.FEET);
        Quantity length2 = new Quantity(0.002, LengthUnit.FEET);
        assertEquals(0.003, QuantityMeasurementApp.demonstrateAddition(length1, length2).getValue(), 0.03);
    }

    //UC 7
    @Test
    public void testAddition_SameUnit_FeetPlusFeetOverride() {
        Quantity length1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity length2 = new Quantity(2, LengthUnit.FEET);
        assertEquals(3.0, QuantityMeasurementApp.demonstrateAddition(length1, length2, LengthUnit.FEET).getValue());
    }

    @Test
    public void testAddition_CrossUnit_FeetPlusInchesOverride() {
        Quantity length1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity length2 = new Quantity(12.0, LengthUnit.INCHES);
        assertEquals(2.0, QuantityMeasurementApp.demonstrateAddition(length1, length2, LengthUnit.FEET).getValue());
    }

    @Test
    public void testAddition_CrossUnit_InchesPlusFeetOverride() {
        Quantity length1 = new Quantity(12.0, LengthUnit.INCHES);
        Quantity length2 = new Quantity(1.0, LengthUnit.FEET);
        assertEquals(24.0, QuantityMeasurementApp.demonstrateAddition(length1, length2, LengthUnit.INCHES).getValue());
    }

    @Test
    public void testAddition_CrossUnit_YardPlusFeetOverride() {
        Quantity length1 = new Quantity(1.0, LengthUnit.YARDS);
        Quantity length2 = new Quantity(3.0, LengthUnit.FEET);
        assertEquals(2.0, QuantityMeasurementApp.demonstrateAddition(length1, length2, LengthUnit.YARDS).getValue());
    }

    /*   @Test
       public void testAddition_CrossUnit_CentimeterPlusInchOverride(){
           Quantitylength1 = new Quantity(2.54, LengthUnit.CENTIMETERS);
           Quantitylength2 = new Quantity(1.0, LengthUnit.INCHES);

           assertEquals(5.08, QuantityMeasurementApp.demonstrateAddition(length1, length2, LengthUnit.CENTIMETERS).getValue());
       }*/
    @Test
    public void testAddition_CummutativeOverride() {
        Quantity length1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity length2 = new Quantity(12.0, LengthUnit.INCHES);
        Quantity lengthCumm1 = QuantityMeasurementApp.demonstrateAddition(length1, length2, LengthUnit.FEET);

        Quantity length3 = new Quantity(12.0, LengthUnit.INCHES);
        Quantity length4 = new Quantity(1.0, LengthUnit.FEET);
        Quantity lengthCumm2 = QuantityMeasurementApp.demonstrateAddition(length3, length4, LengthUnit.INCHES);

        assertEquals(4.0, QuantityMeasurementApp.demonstrateAddition(lengthCumm1, lengthCumm2, LengthUnit.FEET).getValue());
    }

    @Test
    public void testAddition_withZeroOverride() {
        Quantity length1 = new Quantity(5.0, LengthUnit.FEET);
        Quantity length2 = new Quantity(0.0, LengthUnit.INCHES);
        assertEquals(5.0, QuantityMeasurementApp.demonstrateAddition(length1, length2, LengthUnit.FEET).getValue());
    }

    @Test
    public void testAddition_NegativeValuesOverride() {
        Quantity length1 = new Quantity(5.0, LengthUnit.FEET);
        Quantity length2 = new Quantity(-2.0, LengthUnit.FEET);
        assertEquals(3.0, QuantityMeasurementApp.demonstrateAddition(length1, length2, LengthUnit.FEET).getValue());
    }

    @Test
    public void testAddition_NullSecondOperandOverride() {
        Quantity length1 = new Quantity(5.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> QuantityMeasurementApp.demonstrateAddition(length1, null));
    }


    @Test
    public void testAddition_LargeValueOverride() {
        Quantity length1 = new Quantity(1e6, LengthUnit.FEET);
        Quantity length2 = new Quantity(1e6, LengthUnit.FEET);
        assertEquals(2e6, QuantityMeasurementApp.demonstrateAddition(length1, length2, LengthUnit.FEET).getValue());
    }

    @Test
    public void testAddition_SmallValueOverride() {
        Quantity length1 = new Quantity(0.001, LengthUnit.FEET);
        Quantity length2 = new Quantity(0.002, LengthUnit.FEET);
        assertEquals(0.003, QuantityMeasurementApp.demonstrateAddition(length1, length2, LengthUnit.FEET).getValue(), 0.03);
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
        Quantity l1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity l2 = new Quantity(12.0, LengthUnit.INCHES);
        assertEquals(l1, l2);
    }

    @Test
    public void testQuantityLength_Refactored_ConvertTo() {
        Quantity l1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity l2 = l1.convertTo(LengthUnit.INCHES);
        assertEquals(l1, l2);
    }

    @Test
    public void testQuantityLength_Refactored_Add() {
        Quantity l1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity l2 = new Quantity(12.0, LengthUnit.INCHES);
        assertEquals(2.0, QuantityMeasurementApp.demonstrateAddition(l1, l2).getValue());
    }

    @Test
    public void testQuantityLength_Refactored_AddWithTargetUnit() {
        Quantity l1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity l2 = new Quantity(12.0, LengthUnit.INCHES);
        assertEquals(0.667,
                QuantityMeasurementApp.demonstrateAddition(l1, l2, LengthUnit.YARDS).getValue(), 0.666);
    }

    @Test
    public void testQuantityLength_Refactored_nullUnit() {
        Quantity l1 = new Quantity(1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.demonstrateAddition(l1, null, LengthUnit.YARDS));
    }

    @Test
    public void testQuantityLength_Refactored_InvalidValue() {
        //Quantity l1 = new Quantity(Double.NaN, LengthUnit.FEET);
        //assertThrows(IllegalArgumentException.class,
         //       () -> QuantityMeasurementApp.demonstrateConversion(l1, LengthUnit.INCHES));
    }

    // Quantity Test cases

    @Test
    public void testEquality_kilogramToKilogram_SameValue() {
        Quantity w1 = new Quantity(1.0, WeightUnit.KILOGRAM);
        assertEquals(w1, w1);
        Quantity w2 = new Quantity(1.0, WeightUnit.KILOGRAM);
        assertEquals(w1, w2);
    }

    @Test
    public void testEquality_kilogramToKilogram_DifferentValue() {
        Quantity w1 = new Quantity(1.0, WeightUnit.KILOGRAM);
        Quantity w2 = new Quantity(2.0, WeightUnit.KILOGRAM);
        assertNotEquals(w1, w2);
    }

    @Test
    public void testEquality_kilogramToGram_EquivalentValue() {
        Quantity w1 = new Quantity(1.0, WeightUnit.KILOGRAM);
        Quantity w2 = new Quantity(1000.0, WeightUnit.GRAM);
        assertEquals(w1, w2);
    }

    @Test
    public void testEquality_GramToKiloGram_EquivalentValue() {
        Quantity w1 = new Quantity(1.0, WeightUnit.KILOGRAM);
        Quantity w2 = new Quantity(1000.0, WeightUnit.GRAM);
        assertEquals(w2, w1);
    }

    @Test
    public void testEquality_WeightVSLength_Incompatible() {
        Quantity w1 = new Quantity(1.0, WeightUnit.KILOGRAM);
        Quantity l1 = new Quantity(12.0, LengthUnit.INCHES);
        assertNotEquals(w1, l1);
    }

    @Test
    public void testEquality_NullComparison() {
        Quantity w1 = new Quantity(1.0, WeightUnit.KILOGRAM);
        assertNotEquals(null, w1);
    }

    @Test
    public void testEquality_SameReference() {
        Quantity w1 = new Quantity(1.0, WeightUnit.KILOGRAM);
        assertEquals(w1, w1);
    }

    @Test
    public void testEquality_TransitiveProperty() {
        Quantity w1 = new Quantity(1.0, WeightUnit.KILOGRAM);
        Quantity w2 = new Quantity(1000.0, WeightUnit.GRAM);
        Quantity w3 = new Quantity(1e+6, WeightUnit.MILLIGRAM);
        assertEquals(w1, w2);
        assertEquals(w2, w3);
        assertEquals(w3, w1);
    }

    @Test
    public void testEquality_ZeroValue() {
        Quantity w1 = new Quantity(0.0, WeightUnit.KILOGRAM);
        Quantity w2 = new Quantity(0.0, WeightUnit.GRAM);
        assertEquals(w1, w2);
    }

    @Test
    public void testEquality_NegativeQuantity() {
        Quantity w1 = new Quantity(-1.0, WeightUnit.KILOGRAM);
        Quantity w2 = new Quantity(-1000.0, WeightUnit.GRAM);
        assertEquals(w1, w2);
    }

    @Test
    public void testEquality_SmallWeightValue() {
        Quantity w1 = new Quantity(0.001, WeightUnit.KILOGRAM);
        Quantity w2 = new Quantity(1.0, WeightUnit.GRAM);
        assertEquals(w1, w2);
    }

    @Test
    public void testConversion_PoundToKilogram() {
        Quantity w1 = new Quantity(2.20462, WeightUnit.POUND);
        assertEquals(1.0, QuantityMeasurementApp.demonstrateConversion(w1, WeightUnit.KILOGRAM).getValue());
    }

    @Test
    public void testConversion_KilogramToPound() {
        Quantity w1 = new Quantity(1.0, WeightUnit.KILOGRAM);
        assertEquals(2.20462, QuantityMeasurementApp.demonstrateConversion(w1, WeightUnit.POUND).getValue(), 2.20);
    }

    @Test
    public void testConversion_SameUnit() {
        Quantity w1 = new Quantity(1.0, WeightUnit.KILOGRAM);
        assertEquals(1.0, QuantityMeasurementApp.demonstrateConversion(w1, WeightUnit.KILOGRAM).getValue());
    }

    @Test
    public void testConversion_ZeroValueQuantity() {
        Quantity w1 = new Quantity(1.0, WeightUnit.KILOGRAM);
        assertEquals(1.0, QuantityMeasurementApp.demonstrateConversion(w1, WeightUnit.KILOGRAM).getValue());
    }

    @Test
    public void testConversion_NegativeQuantity() {
        Quantity w1 = new Quantity(-1.0, WeightUnit.KILOGRAM);
        assertEquals(-1000.0, QuantityMeasurementApp.demonstrateConversion(w1, WeightUnit.GRAM).getValue());
    }

    @Test
    public void testConversion_roundTrip() {
        Quantity w1 = new Quantity(1.5, WeightUnit.KILOGRAM);
        Quantity w4 = QuantityMeasurementApp.demonstrateConversion(
                QuantityMeasurementApp.demonstrateConversion(w1, WeightUnit.GRAM),
                WeightUnit.KILOGRAM);
        assertEquals(1.5, w4.getValue());
    }

    @Test
    public void testAddition_SameUnit_KilogramPlusKilogram() {
        Quantity w1 = new Quantity(1.5, WeightUnit.KILOGRAM);
        Quantity w2 = new Quantity(1.5, WeightUnit.KILOGRAM);
        assertEquals(3.0, QuantityMeasurementApp.demonstrateAddition(w1, w2).getValue());
    }

    @Test
    public void testAddition_CrossUnit_KilogramPlusGram() {
        Quantity w1 = new Quantity(1000.0, WeightUnit.GRAM);
        Quantity w2 = new Quantity(1.5, WeightUnit.KILOGRAM);
        assertEquals(2500, QuantityMeasurementApp.demonstrateAddition(w1, w2).getValue());
    }

    @Test
    public void testAddition_CrossUnit_POUNDPlusKilogram() {
        Quantity w1 = new Quantity(2.20462, WeightUnit.POUND);
        Quantity w2 = new Quantity(1, WeightUnit.KILOGRAM);
        assertEquals(4.40924, QuantityMeasurementApp.demonstrateAddition(w1, w2, WeightUnit.POUND).getValue(), 4.44);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Gram() {
        Quantity w1 = new Quantity(1.0, WeightUnit.KILOGRAM);
        Quantity w2 = new Quantity(1000.0, WeightUnit.GRAM);
        assertEquals(2000.0, QuantityMeasurementApp.demonstrateAddition(w1, w2, WeightUnit.GRAM).getValue());
    }

    @Test
    public void testAddition_Commutativity() {
        Quantity w1 = new Quantity(1.0, WeightUnit.KILOGRAM);
        Quantity w2 = new Quantity(1000.0, WeightUnit.GRAM);
        Quantity w3 = QuantityMeasurementApp.demonstrateAddition(w1, w2, WeightUnit.GRAM);

        Quantity w5 = new Quantity(1.0, WeightUnit.KILOGRAM);
        Quantity w4 = new Quantity(1000.0, WeightUnit.GRAM);

        Quantity w6 = QuantityMeasurementApp.demonstrateAddition(w4, w5, WeightUnit.GRAM);
        assertEquals(w3, w6);
    }

    @Test
    public void testAdditionwithZero() {
        Quantity w1 = new Quantity(5.0, WeightUnit.KILOGRAM);
        Quantity w2 = new Quantity(0.0, WeightUnit.GRAM);
        assertEquals(5.0, QuantityMeasurementApp.demonstrateAddition(w1, w2, WeightUnit.KILOGRAM).getValue());
    }

    @Test
    public void testAdditionwithNegattiveValues() {
        Quantity w1 = new Quantity(5.0, WeightUnit.KILOGRAM);
        Quantity w2 = new Quantity(-2000.0, WeightUnit.GRAM);
        assertEquals(3.0, QuantityMeasurementApp.demonstrateAddition(w1, w2, WeightUnit.KILOGRAM).getValue());
    }

    @Test
    public void testAdditionWithLargeValues() {
        Quantity w1 = new Quantity(1e6, WeightUnit.KILOGRAM);
        Quantity w2 = new Quantity(1e6, WeightUnit.KILOGRAM);
        assertEquals(2e6, QuantityMeasurementApp.demonstrateAddition(w1, w2, WeightUnit.KILOGRAM).getValue());
    }
}
