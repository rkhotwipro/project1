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

    //UC6

    @Test
    public void testAddition_SameUnit_FeetPlusFeet(){
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(2, Length.LengthUnit.FEET);

        assertEquals(3.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2).getValue());

    }

    @Test
    public void testAddition_CrossUnit_FeetPlusInches(){
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0, Length.LengthUnit.INCHES);

        assertEquals(2.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2).getValue());
    }

    @Test
    public void testAddition_CrossUnit_InchesPlusFeet(){
        Length length1 = new Length(12.0, Length.LengthUnit.INCHES);
        Length length2 = new Length(1.0, Length.LengthUnit.FEET);

        assertEquals(24.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2).getValue());
    }

    @Test
    public void testAddition_CrossUnit_YardPlusFeet(){
        Length length1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length length2 = new Length(3.0, Length.LengthUnit.FEET);

        assertEquals(2.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2).getValue());
    }

    @Test
    public void testAddition_CrossUnit_CentimeterPlusInch(){
        Length length1 = new Length(2.54, Length.LengthUnit.CENTIMETERS);
        Length length2 = new Length(1.0, Length.LengthUnit.INCHES);

        assertEquals(5.08, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2).getValue());
    }
    @Test
    public void testAddition_Cummutative(){
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length lengthCumm1 = QuantityMeasurementApp.demonstrateLengthAddition(length1, length2);

        Length length3 = new Length(12.0, Length.LengthUnit.INCHES);
        Length length4 = new Length(1.0, Length.LengthUnit.FEET);

        Length lengthCumm2 = QuantityMeasurementApp.demonstrateLengthAddition(length3, length4);

        assertEquals(4.0, QuantityMeasurementApp.demonstrateLengthAddition(lengthCumm1, lengthCumm2).getValue());
    }

    @Test
    public void testAddition_withZero(){
        Length length1 = new Length(5.0, Length.LengthUnit.FEET);
        Length length2 = new Length(0.0, Length.LengthUnit.INCHES);

        assertEquals(5.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2).getValue());
    }

    @Test
    public void testAddition_NegativeValues(){
        Length length1 = new Length(5.0, Length.LengthUnit.FEET);
        Length length2 = new Length(-2.0, Length.LengthUnit.FEET);

        assertEquals(3.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2).getValue());
    }

    @Test
    public void testAddition_NullSecondOperand(){
        Length length1 = new Length(5.0, Length.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> QuantityMeasurementApp.demonstrateLengthAddition(length1, null));

    }


    @Test
    public void testAddition_LargeValue(){
        Length length1 = new Length(1e6, Length.LengthUnit.FEET);
        Length length2 = new Length(1e6, Length.LengthUnit.FEET);
        assertEquals(2e6 , QuantityMeasurementApp.demonstrateLengthAddition(length1, length2).getValue());

    }

    @Test
    public void testAddition_SmallValue(){
        Length length1 = new Length(0.001, Length.LengthUnit.FEET);
        Length length2 = new Length(0.002, Length.LengthUnit.FEET);
        assertEquals(0.003 , QuantityMeasurementApp.demonstrateLengthAddition(length1, length2).getValue(), 0.03);

    }

    //UC 7
    @Test
    public void testAddition_SameUnit_FeetPlusFeetOverride(){
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(2, Length.LengthUnit.FEET);

        assertEquals(3.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, Length.LengthUnit.FEET).getValue());

    }


    @Test
    public void testAddition_CrossUnit_FeetPlusInchesOverride(){
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0, Length.LengthUnit.INCHES);

        assertEquals(2.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, Length.LengthUnit.FEET).getValue());
    }

    @Test
    public void testAddition_CrossUnit_InchesPlusFeetOverride(){
        Length length1 = new Length(12.0, Length.LengthUnit.INCHES);
        Length length2 = new Length(1.0, Length.LengthUnit.FEET);

        assertEquals(24.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, Length.LengthUnit.INCHES).getValue());
    }

    @Test
    public void testAddition_CrossUnit_YardPlusFeetOverride(){
        Length length1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length length2 = new Length(3.0, Length.LengthUnit.FEET);

        assertEquals(2.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, Length.LengthUnit.YARDS).getValue());
    }

    @Test
    public void testAddition_CrossUnit_CentimeterPlusInchOverride(){
        Length length1 = new Length(2.54, Length.LengthUnit.CENTIMETERS);
        Length length2 = new Length(1.0, Length.LengthUnit.INCHES);

        assertEquals(5.08, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, Length.LengthUnit.CENTIMETERS).getValue());
    }
    @Test
    public void testAddition_CummutativeOverride(){
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length lengthCumm1 = QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, Length.LengthUnit.FEET);

        Length length3 = new Length(12.0, Length.LengthUnit.INCHES);
        Length length4 = new Length(1.0, Length.LengthUnit.FEET);

        Length lengthCumm2 = QuantityMeasurementApp.demonstrateLengthAddition(length3, length4, Length.LengthUnit.INCHES);

        assertEquals(4.0, QuantityMeasurementApp.demonstrateLengthAddition(lengthCumm1, lengthCumm2, Length.LengthUnit.FEET).getValue());
    }

    @Test
    public void testAddition_withZeroOverride(){
        Length length1 = new Length(5.0, Length.LengthUnit.FEET);
        Length length2 = new Length(0.0, Length.LengthUnit.INCHES);

        assertEquals(5.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, Length.LengthUnit.FEET).getValue());
    }

    @Test
    public void testAddition_NegativeValuesOverride(){
        Length length1 = new Length(5.0, Length.LengthUnit.FEET);
        Length length2 = new Length(-2.0, Length.LengthUnit.FEET);

        assertEquals(3.0, QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, Length.LengthUnit.FEET).getValue());
    }

    @Test
    public void testAddition_NullSecondOperandOverride(){
        Length length1 = new Length(5.0, Length.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> QuantityMeasurementApp.demonstrateLengthAddition(length1, null));

    }


    @Test
    public void testAddition_LargeValueOverride(){
        Length length1 = new Length(1e6, Length.LengthUnit.FEET);
        Length length2 = new Length(1e6, Length.LengthUnit.FEET);
        assertEquals(2e6 , QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, Length.LengthUnit.FEET).getValue());

    }

    @Test
    public void testAddition_SmallValueOverride(){
        Length length1 = new Length(0.001, Length.LengthUnit.FEET);
        Length length2 = new Length(0.002, Length.LengthUnit.FEET);
        assertEquals(0.003 , QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, Length.LengthUnit.FEET).getValue(), 0.03);

    }
}
