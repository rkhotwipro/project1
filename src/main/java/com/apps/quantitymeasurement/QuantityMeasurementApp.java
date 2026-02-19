package com.apps.quantitymeasurement;

import javax.xml.transform.sax.SAXSource;

public class QuantityMeasurementApp {

    public static <U extends Imeasurable> boolean demonstrateEquality(Quantity<U> quantity1, Quantity<U> quantity2) {
        return quantity1.equals(quantity2);
    }

    public static <U extends Imeasurable> boolean demonstrateComparison(Quantity<U> quantity1, Quantity<U> quantity2) {
        return demonstrateEquality(quantity1, quantity2);
    }

    public static <U extends Imeasurable> Quantity<U> demonstrateConversion(double value, U fromUnit, U toUnit) {
        if (fromUnit == null || toUnit == null) {
            throw new IllegalArgumentException("Unit must not be null");
        }
        Quantity<U> quantity = new Quantity<U>(value, fromUnit);
        return quantity.convertTo(toUnit);
    }

    public static <U extends Imeasurable> Quantity<U> demonstrateConversion(Quantity<U> quantity, U toUnit) {
        if (toUnit == null || Double.isNaN(quantity.getValue())) {
            throw new IllegalArgumentException("Unit must not be null");
        }
        return quantity.convertTo(toUnit);
    }

    public static <U extends Imeasurable> Quantity<U> demonstrateAddition(Quantity<U> q1, Quantity<U> q2) {
        return q1.add(q2);
    }

    public static <U extends Imeasurable> Quantity<U> demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U targetUnit) {
        return q1.add(q2, targetUnit);
    }

    public static void main(String[] args) {
        Quantity l1 = new Quantity<LengthUnit>(1, LengthUnit.FEET);
        Quantity l2 = new Quantity<LengthUnit>(12, LengthUnit.INCHES);

        Quantity w1 = new Quantity<WeightUnit>(1000.0, WeightUnit.GRAM);
        Quantity w2 = new Quantity<WeightUnit>(1.0, WeightUnit.KILOGRAM);
        System.out.println("demonstrateEquality : " + demonstrateEquality(w1, w2));
        System.out.println("demonstrateComparison : " + demonstrateComparison(w1, w2));
    }
}
