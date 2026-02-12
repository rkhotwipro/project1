package com.apps.quantitymeasurement;

public class Length {

    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {

        if (unit == null){
            throw new IllegalArgumentException("Unit must not be a null");
        }

        if(Double.isNaN(value) || Double.isInfinite(value)){
            throw new IllegalArgumentException("Please enter the floating point value");
        }

        this.value = value;
        this.unit = unit;
    }

    public enum LengthUnit{
        FEET( 12.0),
        INCHES( 1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    // Base unit is inches
    private double convertToBaseUnit(){
        return  this.value * unit.getConversionFactor();
    }

    public boolean compare(Length thatLength){
        if(thatLength == null) return false;

        return Double.compare(Math.round(convertToBaseUnit()) , Math.round(thatLength.convertToBaseUnit())) == 0;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null) return  false;
        if(this == obj) return true;
        if(this.getClass() != obj.getClass()) return false;
        return this.compare((Length) obj);
    }

    public Length convertTo(LengthUnit targetUnit){

        if(targetUnit == null){
            throw new IllegalArgumentException("Target unit must not be null");
        }
        //Length length1 = new Length(this.value, this.unit);

        double baseUnit = this.convertToBaseUnit();
        //return new Length(Math.round(baseUnit / targetUnit.getConversionFactor() * 100.0) / 100.0, targetUnit);
        return convertFromBaseToTargetUnit(baseUnit, targetUnit);
    }

    public Length add(Length thatLength){
        if(thatLength == null){
            throw new IllegalArgumentException("Target unit must not be null");
        }
        double baseInch  = this.convertToBaseUnit() + thatLength.convertToBaseUnit();
        Length l1 = new Length(baseInch, LengthUnit.INCHES);
        return l1.convertTo(this.unit);
    }

    public Length add(Length length, LengthUnit targetUnit){
        if(targetUnit == null){
            throw new IllegalArgumentException("Target unit must not be null");
        }

        double baseInch  = this.convertToBaseUnit() + length.convertToBaseUnit();
        Length l1 = new Length(baseInch, LengthUnit.INCHES);

        return l1.convertTo(targetUnit);

    }

    private Length addAndConvert(Length length, LengthUnit targetUnit){
        if(targetUnit == null){
            throw new IllegalArgumentException("Target unit must not be null");
        }

        double baseUnit  = this.convertToBaseUnit() + length.convertToBaseUnit();
        return  convertFromBaseToTargetUnit(baseUnit, targetUnit);
    }

    private Length convertFromBaseToTargetUnit(double baseUnit, LengthUnit targetUnit){
        return new Length(Math.round(baseUnit / targetUnit.getConversionFactor() * 100.0) / 100.0, targetUnit);
    }

    public static void main(String[] args){
        Length length1 = new Length(1, LengthUnit.FEET);

        System.out.print(length1);

        System.out.print(" is ");
        Length length2 = length1.convertTo(LengthUnit.INCHES);
        System.out.println(length2);
    }

    @Override
    public String toString() {
        return "Length{" +
                value + " "+ unit +
                '}';
    }

    public double getValue() {
        return value;
    }
}
