package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.QuantityMeasurementApp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {


    @Test
    public void testConversion_FeetToInches(){
        Length length = new Length(1.0, LengthUnit.FEET);
        Length l1 = QuantityMeasurementApp.demonstrateLengthConversion(length, LengthUnit.INCHES);
        assertEquals(12.0, l1.getValue());
    }

    @Test
    public void testConversion_InchesToFeet(){
        Length length = new Length(24.0, LengthUnit.INCHES);
        Length l1 = QuantityMeasurementApp.demonstrateLengthConversion(length, LengthUnit.FEET);
        assertEquals(2.0, l1.getValue());
    }

    @Test
    public void testConversion_YardToInches(){
        Length length = new Length(1.0, LengthUnit.YARDS);
        Length l1 = QuantityMeasurementApp.demonstrateLengthConversion(length, LengthUnit.INCHES);
        assertEquals(36.0, l1.getValue());
    }

    @Test
    public void testConversion_InchestoYard(){
        Length length = new Length(72.0, LengthUnit.INCHES);
        Length l1 = QuantityMeasurementApp.demonstrateLengthConversion(length, LengthUnit.YARDS);
        assertEquals(2.0, l1.getValue());
    }

    @Test
    public void testConversion_ZeroValue(){
        Length length = new Length(0.0, LengthUnit.INCHES);
        Length l1 = QuantityMeasurementApp.demonstrateLengthConversion(length, LengthUnit.YARDS);
        assertEquals(0.0, l1.getValue());
    }

    @Test
    public void testConversion_RoundTrip_PreservesValue(){
        Length length = new Length(1.0, LengthUnit.YARDS);
        Length l1 = QuantityMeasurementApp.demonstrateLengthConversion(length, LengthUnit.INCHES);
        Length l2 = QuantityMeasurementApp.demonstrateLengthConversion(l1, LengthUnit.YARDS);
        assertEquals(36.0, l1.getValue());
        assertEquals(1.0, l2.getValue());
    }

    @Test
    public void testConversion_negativeValue(){
        Length length = new Length(-1.0, LengthUnit.FEET);
        Length l1 = QuantityMeasurementApp.demonstrateLengthConversion(length, LengthUnit.INCHES);
        assertEquals(-12.0, l1.getValue());
    }

    @Test
    public void testConversion_invalidUnit_Throws(){
        Length length = new Length(-1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, ()->QuantityMeasurementApp.demonstrateLengthConversion(length, null));
    }

    @Test
    public void testConversion_NAN(){
        assertThrows(IllegalArgumentException.class, ()->QuantityMeasurementApp.demonstrateLengthConversion(
                new Length(Double.NaN, LengthUnit.FEET), LengthUnit.INCHES));
    }

    @Test
    public void testConversion_Infinite(){
        assertThrows(IllegalArgumentException.class, ()->QuantityMeasurementApp.demonstrateLengthConversion(
                new Length(Double.POSITIVE_INFINITY, LengthUnit.FEET), LengthUnit.INCHES));
    }

    @Test
    public void testConversion_PrecisonToTolerance(){
        Length length = new Length(300.0, LengthUnit.CENTIMETERS);
        Length l1 = QuantityMeasurementApp.demonstrateLengthConversion(length, LengthUnit.YARDS);
         Assertions.assertEquals(3.28084, l1.getValue(), 3.28085);
    }

    //UC6

    @Test
    public void testAddition_SameUnit_FeetPlusFeet(){
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(2, LengthUnit.FEET);

        assertEquals(3.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2).getValue());

    }

    @Test
    public void testAddition_CrossUnit_FeetPlusInches(){
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);

        assertEquals(2.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2).getValue());
    }

    @Test
    public void testAddition_CrossUnit_InchesPlusFeet(){
        Length length1 = new Length(12.0, LengthUnit.INCHES);
        Length length2 = new Length(1.0, LengthUnit.FEET);

        assertEquals(24.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2).getValue());
    }

    @Test
    public void testAddition_CrossUnit_YardPlusFeet(){
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
    public void testAddition_Cummutative(){
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);

        Length lengthCumm1 = QuantityMeasurementApp.demonstrateLengthAddition(length1, length2);

        Length length3 = new Length(12.0, LengthUnit.INCHES);
        Length length4 = new Length(1.0, LengthUnit.FEET);

        Length lengthCumm2 = QuantityMeasurementApp.demonstrateLengthAddition(length3, length4);

        assertEquals(4.0, QuantityMeasurementApp.demonstrateLengthAddition(lengthCumm1, lengthCumm2).getValue());
    }

    @Test
    public void testAddition_withZero(){
        Length length1 = new Length(5.0, LengthUnit.FEET);
        Length length2 = new Length(0.0, LengthUnit.INCHES);

        assertEquals(5.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2).getValue());
    }

    @Test
    public void testAddition_NegativeValues(){
        Length length1 = new Length(5.0, LengthUnit.FEET);
        Length length2 = new Length(-2.0, LengthUnit.FEET);

        assertEquals(3.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2).getValue());
    }

    @Test
    public void testAddition_NullSecondOperand(){
        Length length1 = new Length(5.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> QuantityMeasurementApp.demonstrateLengthAddition(length1, null));

    }


    @Test
    public void testAddition_LargeValue(){
        Length length1 = new Length(1e6, LengthUnit.FEET);
        Length length2 = new Length(1e6, LengthUnit.FEET);
        assertEquals(2e6 , QuantityMeasurementApp.demonstrateLengthAddition(length1, length2).getValue());

    }

    @Test
    public void testAddition_SmallValue(){
        Length length1 = new Length(0.001, LengthUnit.FEET);
        Length length2 = new Length(0.002, LengthUnit.FEET);
        assertEquals(0.003 , QuantityMeasurementApp.demonstrateLengthAddition(length1, length2).getValue(), 0.03);

    }

    //UC 7
    @Test
    public void testAddition_SameUnit_FeetPlusFeetOverride(){
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(2, LengthUnit.FEET);

        assertEquals(3.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.FEET).getValue());

    }


    @Test
    public void testAddition_CrossUnit_FeetPlusInchesOverride(){
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);

        assertEquals(2.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.FEET).getValue());
    }

    @Test
    public void testAddition_CrossUnit_InchesPlusFeetOverride(){
        Length length1 = new Length(12.0, LengthUnit.INCHES);
        Length length2 = new Length(1.0, LengthUnit.FEET);

        assertEquals(24.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.INCHES).getValue());
    }

    @Test
    public void testAddition_CrossUnit_YardPlusFeetOverride(){
        Length length1 = new Length(1.0, LengthUnit.YARDS);
        Length length2 = new Length(3.0, LengthUnit.FEET);

        assertEquals(2.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.YARDS).getValue());
    }

 /*   @Test
    public void testAddition_CrossUnit_CentimeterPlusInchOverride(){
        Length length1 = new Length(2.54, LengthUnit.CENTIMETERS);
        Length length2 = new Length(1.0, LengthUnit.INCHES);

        assertEquals(5.08, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.CENTIMETERS).getValue());
    }*/
    @Test
    public void testAddition_CummutativeOverride(){
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);

        Length lengthCumm1 = QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.FEET);

        Length length3 = new Length(12.0, LengthUnit.INCHES);
        Length length4 = new Length(1.0, LengthUnit.FEET);

        Length lengthCumm2 = QuantityMeasurementApp.demonstrateLengthAddition(length3, length4, LengthUnit.INCHES);

        assertEquals(4.0, QuantityMeasurementApp.demonstrateLengthAddition(lengthCumm1, lengthCumm2, LengthUnit.FEET).getValue());
    }

    @Test
    public void testAddition_withZeroOverride(){
        Length length1 = new Length(5.0, LengthUnit.FEET);
        Length length2 = new Length(0.0, LengthUnit.INCHES);

        assertEquals(5.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.FEET).getValue());
    }

    @Test
    public void testAddition_NegativeValuesOverride(){
        Length length1 = new Length(5.0, LengthUnit.FEET);
        Length length2 = new Length(-2.0, LengthUnit.FEET);

        assertEquals(3.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.FEET).getValue());
    }

    @Test
    public void testAddition_NullSecondOperandOverride(){
        Length length1 = new Length(5.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> QuantityMeasurementApp.demonstrateLengthAddition(length1, null));

    }


    @Test
    public void testAddition_LargeValueOverride(){
        Length length1 = new Length(1e6, LengthUnit.FEET);
        Length length2 = new Length(1e6, LengthUnit.FEET);
        assertEquals(2e6 , QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.FEET).getValue());

    }

    @Test
    public void testAddition_SmallValueOverride(){
        Length length1 = new Length(0.001, LengthUnit.FEET);
        Length length2 = new Length(0.002, LengthUnit.FEET);
        assertEquals(0.003 , QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.FEET).getValue(), 0.03);

    }

    @Test
    public void theLengthUnitEnum_FeetConstant(){
        assertEquals(12.0, LengthUnit.FEET.getConversionFactor());
    }

    @Test
    public void theLengthUnitEnum_InchesConstant(){
        assertEquals(1.0, LengthUnit.INCHES.getConversionFactor());
    }

    @Test
    public void theLengthUnitEnum_YardsConstant(){
        assertEquals(36.0, LengthUnit.YARDS.getConversionFactor());
    }

    @Test
    public void theLengthUnitEnum_CentimetersConstant(){
        assertEquals(0.393701, LengthUnit.CENTIMETERS.getConversionFactor());
    }

    @Test
    public void testConvertToBaseUnit_InchesToInches(){
        assertEquals(2.0, LengthUnit.INCHES.convertToBaseUnit(2.0));
    }

    @Test
    public void testConvertToBaseUnit_InchesToFeet(){
        assertEquals(12.0, LengthUnit.FEET.convertToBaseUnit(1.0));
    }

    @Test
    public void testConvertToBaseUnit_YardsToInches(){
        assertEquals(36.0, LengthUnit.YARDS.convertToBaseUnit(1.0));
    }

    @Test
    public void testConvertToBaseUnit_CentimetersToInches(){
        assertEquals(12.0, LengthUnit.CENTIMETERS.convertToBaseUnit(30.48));
    }

    @Test
    public void testQuantityLength_Refactored_Equality(){
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
                ()-> QuantityMeasurementApp.demonstrateLengthAddition(l1, null, LengthUnit.YARDS) );
    }

    @Test
    public void testQuantityLength_Refactored_InvalidValue() {
        Length l1 = new Length(Double.NaN, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                ()-> QuantityMeasurementApp.demonstrateLengthConversion(l1, LengthUnit.INCHES) );
    }

    // Weight Test cases

    @Test
    public void testEquality_kilogramToKilogram_SameValue(){
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        assertEquals(w1, w1);

        Weight w2 = new Weight(1.0, WeightUnit.KILOGRAM);
        assertEquals(w1, w2);
    }

    @Test
    public void testEquality_kilogramToKilogram_DifferentValue(){
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(2.0, WeightUnit.KILOGRAM);
        assertNotEquals(w1, w2);
    }

    @Test
    public void testEquality_kilogramToGram_EquivalentValue(){
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000.0, WeightUnit.GRAM);
        assertEquals(w1, w2);
    }

    @Test
    public void testEquality_GramToKiloGram_EquivalentValue(){
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000.0, WeightUnit.GRAM);
        assertEquals(w2, w1);
    }

    @Test
    public void testEquality_WeightVSLength_Incompatible(){
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Length l1 = new Length(12.0, LengthUnit.INCHES);

        assertNotEquals(w1, l1);
    }

    @Test
    public void testEquality_NullComparison(){
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        assertNotEquals(null, w1);
    }

    @Test
    public void testEquality_SameReference(){
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        assertEquals(w1, w1);
    }

    @Test
    public void testEquality_TransitiveProperty(){
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000.0, WeightUnit.GRAM);
        Weight w3 = new Weight(1e+6, WeightUnit.MILLIGRAM);
        assertEquals(w1, w2);
        assertEquals(w2, w3);
        assertEquals(w3, w1);
    }

    @Test
    public void testEquality_ZeroValue(){
        Weight w1 = new Weight(0.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(0.0, WeightUnit.GRAM);
        assertEquals(w1, w2);
    }

    @Test
    public void testEquality_NegativeWeight(){
        Weight w1 = new Weight(-1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(-1000.0, WeightUnit.GRAM);
        assertEquals(w1, w2);
    }

    @Test
    public void testEquality_SmallWeightValue(){
        Weight w1 = new Weight(0.001, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1.0, WeightUnit.GRAM);
        assertEquals(w1, w2);
    }

    @Test
    public void testConversion_PoundToKilogram(){
        Weight w1 = new Weight(2.20462, WeightUnit.POUND);
        assertEquals(1.0, QuantityMeasurementApp.demonstrateWeightConversion(w1, WeightUnit.KILOGRAM).getValue());
    }

    @Test
    public void testConversion_KilogramToPound(){
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        assertEquals(2.20462, QuantityMeasurementApp.demonstrateWeightConversion(w1, WeightUnit.POUND).getValue(), 2.20);
    }

    @Test
    public void testConversion_SameUnit(){
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        assertEquals(1.0, QuantityMeasurementApp.demonstrateWeightConversion(w1, WeightUnit.KILOGRAM).getValue());
    }

    @Test
    public void testConversion_ZeroValueWeight(){
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        assertEquals(1.0, QuantityMeasurementApp.demonstrateWeightConversion(w1, WeightUnit.KILOGRAM).getValue());
    }

    @Test
    public void testConversion_NegativeWeight(){
        Weight w1 = new Weight(-1.0, WeightUnit.KILOGRAM);
        assertEquals(-1000.0, QuantityMeasurementApp.demonstrateWeightConversion(w1, WeightUnit.GRAM).getValue());
    }

    @Test
    public void testConversion_roundTrip(){
        Weight w1 = new Weight(1.5, WeightUnit.KILOGRAM);
        Weight w4 = QuantityMeasurementApp.demonstrateWeightConversion(
                                                    QuantityMeasurementApp.demonstrateWeightConversion(w1, WeightUnit.GRAM),
                                                    WeightUnit.KILOGRAM);
        assertEquals(1.5, w4.getValue());
    }

    @Test
    public void testAddition_SameUnit_KilogramPlusKilogram(){
        Weight w1 = new Weight(1.5, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1.5, WeightUnit.KILOGRAM);

        assertEquals(3.0, QuantityMeasurementApp.demonstrateWeightAddition(w1, w2).getValue());
    }

    @Test
    public void testAddition_CrossUnit_KilogramPlusGram(){
        Weight w1 = new Weight(1000.0, WeightUnit.GRAM);
        Weight w2 = new Weight(1.5, WeightUnit.KILOGRAM);

        assertEquals(2.5, QuantityMeasurementApp.demonstrateWeightAddition(w1, w2).getValue());
    }

    @Test
    public void testAddition_CrossUnit_POUNDPlusKilogram(){
        Weight w1 = new Weight(2.20462, WeightUnit.POUND);
        Weight w2 = new Weight(1, WeightUnit.KILOGRAM);

        assertEquals(4.40924, QuantityMeasurementApp.demonstrateWeightAddition(w1, w2, WeightUnit.POUND).getValue(), 4.44);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Gram(){
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000.0, WeightUnit.GRAM);

        assertEquals(2000.0, QuantityMeasurementApp.demonstrateWeightAddition(w1, w2, WeightUnit.GRAM).getValue());
    }

    @Test
    public void testAddition_Commutativity(){
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000.0, WeightUnit.GRAM);
        Weight w3 = QuantityMeasurementApp.demonstrateWeightAddition(w1, w2, WeightUnit.GRAM);

        Weight w5 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w4 = new Weight(1000.0, WeightUnit.GRAM);

        Weight w6 = QuantityMeasurementApp.demonstrateWeightAddition(w4, w5, WeightUnit.GRAM);
        assertEquals(w3, w6 );
    }

    @Test
    public void testAdditionwithZero(){
        Weight w1 = new Weight(5.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(0.0, WeightUnit.GRAM);

        assertEquals(5.0, QuantityMeasurementApp.demonstrateWeightAddition(w1, w2, WeightUnit.KILOGRAM).getValue());
    }

    @Test
    public void testAdditionwithNegattiveValues(){
        Weight w1 = new Weight(5.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(-2000.0, WeightUnit.GRAM);

        assertEquals(3.0, QuantityMeasurementApp.demonstrateWeightAddition(w1, w2, WeightUnit.KILOGRAM).getValue());
    }

    @Test
    public void testAdditionWithLargeValues(){
        Weight w1 = new Weight(1e6, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1e6, WeightUnit.KILOGRAM);

        assertEquals(2e6, QuantityMeasurementApp.demonstrateWeightAddition(w1, w2, WeightUnit.KILOGRAM).getValue());
    }
}
