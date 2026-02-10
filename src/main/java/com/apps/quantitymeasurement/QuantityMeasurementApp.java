package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {


    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1.equals(length2);
    }

    public static boolean demonstrateLengthComparison(Length l1, Length l2) {
        return demonstrateLengthEquality(l1, l2);
    }

    public static Length demonstrateLengthConversion(double value, Length.LengthUnit fromUnit, Length.LengthUnit toUnit) {
        if (fromUnit == null || toUnit == null) {
            throw new IllegalArgumentException("Unit must not be null");
        }

        Length length = new Length(value, fromUnit);
        return length.convertTo(toUnit);
    }

    public static Length demonstrateLengthConversion(Length length, Length.LengthUnit toUnit) {
        if (toUnit == null) {
            throw new IllegalArgumentException("Unit must not be null");
        }

        return length.convertTo(toUnit);
    }


    public static void main(String[] args) {

        Length length = new Length(3.0, Length.LengthUnit.FEET);

        System.out.print(length);
        System.out.print(" is ");
        System.out.println(demonstrateLengthConversion(length, Length.LengthUnit.INCHES));


        Length length2 = new Length(3.0, Length.LengthUnit.YARDS);

        System.out.print(length2);
        System.out.print(" is ");
        System.out.println(demonstrateLengthConversion(length2, Length.LengthUnit.FEET));


        Length length3 = new Length(36.0, Length.LengthUnit.INCHES);

        System.out.print(length3);
        System.out.print(" is ");
        System.out.println(demonstrateLengthConversion(length3, Length.LengthUnit.YARDS));

        Length length4 = new Length(1, Length.LengthUnit.CENTIMETERS);

        System.out.print(length4);
        System.out.print(" is ");
        System.out.println(demonstrateLengthConversion(length4, Length.LengthUnit.INCHES));


        Length length5 = new Length(0.0, Length.LengthUnit.FEET);

        System.out.print(length5);
        System.out.print(" is ");
        System.out.println(demonstrateLengthConversion(length5, Length.LengthUnit.INCHES));

        Length length6 = new Length(1, Length.LengthUnit.INCHES);
        System.out.print(length6);
        System.out.print(" is ");
        System.out.println(demonstrateLengthConversion(length6, Length.LengthUnit.CENTIMETERS));

        System.out.println("------------------------");
        System.out.println(demonstrateLengthConversion(1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES));

        System.out.println("------------------------");
        System.out.println(demonstrateLengthConversion(3.0, Length.LengthUnit.YARDS, Length.LengthUnit.FEET));


    }
}
