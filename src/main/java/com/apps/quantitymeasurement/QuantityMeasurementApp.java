package com.apps.quantitymeasurement;

import java.util.Objects;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length length1, Length length2){
        return length1.equals(length2);
    }

    public static void demonstrateFeetEquality() {
        Length l1 = new Length(1, Length.LengthUnit.FEET);
        Length l2 = new Length(1, Length.LengthUnit.FEET);

        System.out.println("demonstrateFeetEquality : " + demonstrateLengthEquality(l1, l2));
    }

    public static void demonstrateInchesEquality() {
        Length l1 = new Length(1, Length.LengthUnit.FEET);
        Length l2 = new Length(1, Length.LengthUnit.FEET);

        System.out.println("demonstrateInchesEquality : " + demonstrateLengthEquality(l1, l2));
    }

    public static void demonstrateFeetInchesComparison(){
        Length l1 = new Length(1, Length.LengthUnit.FEET);
        Length l2 = new Length(12, Length.LengthUnit.INCHES);

        System.out.println("demonstrateFeetInchesComparison : " + demonstrateLengthEquality(l1, l2));
    }

    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
        demonstrateFeetInchesComparison();
    }

}
