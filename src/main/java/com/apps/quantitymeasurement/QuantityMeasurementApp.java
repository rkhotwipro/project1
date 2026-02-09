package com.apps.quantitymeasurement;

import java.util.Objects;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(QuantityLength length1, QuantityLength length2) {
        return length1.equals(length2);
    }

    public static void demonstrateFeetEquality() {
        QuantityLength l1 = new QuantityLength(1, QuantityLength.LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(1, QuantityLength.LengthUnit.FEET);

        System.out.println("Quantity (1, Foot) and Quantity(1, Foot) are Equal : " + demonstrateLengthEquality(l1, l2));
    }

    public static void demonstrateInchesEquality() {
        QuantityLength l1 = new QuantityLength(1, QuantityLength.LengthUnit.INCHES);
        QuantityLength l2 = new QuantityLength(1, QuantityLength.LengthUnit.INCHES);

        System.out.println("Quantity (1, Inches) and Quantity(1, Inches) are Equal : " + demonstrateLengthEquality(l1, l2));
    }

    public static void demonstrateFeetInchesComparison() {
        QuantityLength l1 = new QuantityLength(1, QuantityLength.LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12, QuantityLength.LengthUnit.INCHES);

        System.out.println("Quantity (1, Foot) and Quantity(12, Inches) are Equal : " + demonstrateLengthEquality(l1, l2));
    }

    public static boolean demonstrateLengthComparison(QuantityLength l1, QuantityLength l2) {
        return demonstrateLengthEquality(l1, l2);
    }


    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
        demonstrateFeetInchesComparison();

        System.out.println("Yard Inches check : " +
                demonstrateLengthComparison(new QuantityLength(1.0, QuantityLength.LengthUnit.YARDS),
                        new QuantityLength(36.0, QuantityLength.LengthUnit.INCHES)));

        System.out.println("Centimeters Inches check : " +
                demonstrateLengthComparison(new QuantityLength(100.0, QuantityLength.LengthUnit.CENTIMETERS),
                        new QuantityLength(39.3701, QuantityLength.LengthUnit.INCHES)));

        System.out.println("FEET Yards check : " +
                demonstrateLengthComparison(new QuantityLength(3, QuantityLength.LengthUnit.FEET),
                        new QuantityLength(1.0, QuantityLength.LengthUnit.YARDS)));

        System.out.println("CENTIMETERS FEET check : " +
                demonstrateLengthComparison(new QuantityLength(30.4800, QuantityLength.LengthUnit.CENTIMETERS),
                        new QuantityLength(1.0, QuantityLength.LengthUnit.FEET)));

    }
}
