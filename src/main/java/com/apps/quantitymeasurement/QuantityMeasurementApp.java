package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    
    public  static boolean demonstrateLengthEquality(Length length1, Length length2){
        return length1.equals(length2);
    }

    public static boolean demonstrateLengthComparison(Length l1, Length l2){
        return  demonstrateLengthEquality(l1, l2);
    }

    public static Length demonstrateLengthConversion(double value, Length.LengthUnit fromUnit, Length.LengthUnit toUnit){
        if(fromUnit == null || toUnit == null){
            throw new IllegalArgumentException("Unit must not be null");
        }

        Length length = new Length(value, fromUnit);
        return length.convertTo(toUnit);
    }

    public static Length demonstrateLengthConversion(Length length, Length.LengthUnit toUnit){
        if(toUnit == null){
            throw new IllegalArgumentException("Unit must not be null");
        }

      return length.convertTo(toUnit);
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2){
        return l1.add(l2);
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2, Length.LengthUnit targetUnit){
       return l1.add(l2, targetUnit);
    }


    public static void main(String[] args){

        Length lengthAdd1 = new Length(1.0, Length.LengthUnit.FEET);
        Length lengthAdd2 = new Length(12.0, Length.LengthUnit.INCHES);
        System.out.println(demonstrateLengthAddition(lengthAdd1, lengthAdd2));
        System.out.println("Override "+demonstrateLengthAddition(lengthAdd1, lengthAdd2, Length.LengthUnit.FEET));

        System.out.println("--------------------");
        Length lengthAdd3 = new Length(1.0, Length.LengthUnit.FEET);
        Length lengthAdd4 = new Length(2.0, Length.LengthUnit.FEET);
        System.out.println(demonstrateLengthAddition(lengthAdd3, lengthAdd4));
        System.out.println("override "+demonstrateLengthAddition(lengthAdd3, lengthAdd4, Length.LengthUnit.FEET));


        System.out.println("--------------------");
        Length lengthAdd5 = new Length(12.0, Length.LengthUnit.INCHES);
        Length lengthAdd6 = new Length(1.0, Length.LengthUnit.FEET);
        System.out.println(demonstrateLengthAddition(lengthAdd5, lengthAdd6));
        System.out.println("Overrride "+demonstrateLengthAddition(lengthAdd5, lengthAdd6, Length.LengthUnit.INCHES));

        System.out.println("--------------------");
        Length lengthAdd7 = new Length(1.0, Length.LengthUnit.YARDS);
        Length lengthAdd8 = new Length(3.0, Length.LengthUnit.FEET);
        System.out.println(demonstrateLengthAddition(lengthAdd7, lengthAdd8));
        System.out.println("Override  "+demonstrateLengthAddition(lengthAdd7, lengthAdd8, Length.LengthUnit.YARDS));
    }
}
