package com.apps.quantitymeasurement;

import javax.xml.transform.sax.SAXSource;

public class QuantityMeasurementApp {

    
    public  static boolean demonstrateLengthEquality(Length length1, Length length2){
        return length1.equals(length2);
    }

    public static boolean demonstrateLengthComparison(Length l1, Length l2){
        return  demonstrateLengthEquality(l1, l2);
    }

    public static Length demonstrateLengthConversion(double value, LengthUnit fromUnit, LengthUnit toUnit){
        if(fromUnit == null || toUnit == null){
            throw new IllegalArgumentException("Unit must not be null");
        }

        Length length = new Length(value, fromUnit);
        return length.convertTo(toUnit);
    }

    public static Length demonstrateLengthConversion(Length length, LengthUnit toUnit){
        if(toUnit == null || Double.isNaN(length.getValue())){
            throw new IllegalArgumentException("Unit must not be null");
        }

      return length.convertTo(toUnit);
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2){
        return l1.add(l2);
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2, LengthUnit targetUnit){
       return l1.add(l2, targetUnit);
    }


    public static void main(String[] args){

        Weight w1 = new Weight(1000.0, WeightUnit.GRAM);
        Weight w2 = new Weight(100.0, WeightUnit.KILOGRAM);

        System.out.println("1000 grams & 100Kg are Equal : "+demonstrateWeightEquality(w1, w2));

        System.out.println("1000 grams & 1Kg are Equal : "+ demonstrateWeightComparison(1000.0, WeightUnit.GRAM, 1.0, WeightUnit.KILOGRAM));

    }

    public static  boolean demonstrateWeightEquality(Weight weight1, Weight weight2){
        return weight1.equals(weight2);
    }

    public static boolean demonstrateWeightComparison(double value1, WeightUnit unit1, double value2, WeightUnit unit2){
        Weight w1 = new Weight(value1, unit1);
        Weight w2 = new Weight(value2, unit2);
        return w1.equals(w2);
    }

    public static Weight demonstrateWeightConversion(double value, WeightUnit fromUnit, WeightUnit toUnit){
        Weight weight = new Weight(value, fromUnit);
        return weight.convertTo(toUnit);
    }

    public static Weight demonstrateWeightConversion(Weight w1, WeightUnit targetUnit){
        return w1.convertTo(targetUnit);
    }

    public static Weight demonstrateWeightAddition(Weight w1, Weight w2){
        return w1.add(w2);
    }

    public static Weight demonstrateWeightAddition(Weight w1, Weight w2, WeightUnit targetUnit){
        return w1.add(w2, targetUnit);
    }


}
