package com.apps.quantitymeasurement;

public class Length {

    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {

        if (unit == null) {
            throw new IllegalArgumentException("Unit must not be a null");
        }

        this.value = value;
        this.unit = unit;
    }

    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
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
    private double convertToBaseUnit() {
        return this.value * unit.getConversionFactor();
    }

    public boolean compare(Length thatLength) {
        if (thatLength == null) return false;

        return Double.compare(Math.round(convertToBaseUnit()), Math.round(thatLength.convertToBaseUnit())) == 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (this.getClass() != obj.getClass()) return false;
        return this.compare((Length) obj);
    }

    public static void main(String[] args) {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        System.out.println("Are length equals? " + l1.equals(l2));

        Length l3 = new Length(1.0, LengthUnit.YARDS);
        Length l4 = new Length(36.0, LengthUnit.INCHES);

        System.out.println("Are length equals? " + l3.equals(l4));

        Length l5 = new Length(100.0, LengthUnit.CENTIMETERS);
        Length l6 = new Length(39.3701, LengthUnit.INCHES);

        System.out.println("Are length equals? " + l5.equals(l6));

    }
}