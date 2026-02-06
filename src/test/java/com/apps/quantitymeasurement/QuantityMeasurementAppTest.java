package com.apps.quantitymeasurement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {
    @Test
    public void testFeetEquality(){
        Length l1 = new Length(1, Length.LengthUnit.FEET);
        Length l2 = new Length(1, Length.LengthUnit.FEET);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    public void testInchesEquality(){
        Length l1 = new Length(12, Length.LengthUnit.INCHES);
        Length l2 = new Length(12, Length.LengthUnit.INCHES);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    public void testFeetInchesComparison(){
        Length l1 = new Length(1, Length.LengthUnit.FEET);
        Length l2 = new Length(12, Length.LengthUnit.INCHES);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    public void testInchesToFeetComparison(){
        Length l1 = new Length(12, Length.LengthUnit.INCHES);
        Length l2 = new Length(1, Length.LengthUnit.FEET);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    public void testFeetInequality(){
        Length l1 = new Length(1, Length.LengthUnit.FEET);
        Length l2 = new Length(12, Length.LengthUnit.FEET);

        assertFalse(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    public void testInchesInequality(){
        Length l1 = new Length(1, Length.LengthUnit.INCHES);
        Length l2 = new Length(12, Length.LengthUnit.INCHES);

        assertFalse(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    public void testNullValue(){
        Length l1 = new Length(1, Length.LengthUnit.INCHES);

        assertFalse(QuantityMeasurementApp.demonstrateLengthEquality(l1, null));
    }

    @Test
    public void testCrossUnitInequality(){
        Length l1 = new Length(12, Length.LengthUnit.FEET);
        Length l2 = new Length(1, Length.LengthUnit.INCHES);

        assertFalse(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    public void testMultipleFeetComparison(){
        Length l1 = new Length(1, Length.LengthUnit.FEET);
        Length l2 = new Length(1, Length.LengthUnit.FEET);
        Length l3 = new Length(1, Length.LengthUnit.FEET);

        assertAll(
                () -> assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2)),
                () -> assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(l2, l3)),
                () -> assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(l1, l3))
        );
    }
}
