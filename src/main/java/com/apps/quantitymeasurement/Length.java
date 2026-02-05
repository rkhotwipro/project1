package com.apps.quantitymeasurement;

public class Length {
    private double value;
    private LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public enum LengthUnit {
        FEET( 12.0),
        INCHES( 1.0);
        private final double conversionFactor;

        LengthUnit(double conversionFactor){
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    private double convertToBAseUnit(){

        return 0;
    }


    public boolean compare(Length thatLength){

        return false;
    }

    @Override
    public boolean equals(Object o){

        return false;
    }

    public static void main(String[] args){
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);

        System.out.println("Are Lengths Equal : " + length1.equals(length2));
    }
}
