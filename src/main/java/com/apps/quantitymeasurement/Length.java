package com.apps.quantitymeasurement;

public class Length {

    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit must not be a null");
        }

        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Please enter the floating point value");
        }
        this.value = value;
        this.unit = unit;
    }

    // Base unit is inches
    private double convertToBaseUnit() {
        return this.unit.convertToBaseUnit(this.value);
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

    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit must not be null");
        }
        return convertFromBaseToTargetUnit(this.convertToBaseUnit(), targetUnit);
    }

    public Length add(Length thatLength) {
        if (thatLength == null) {
            throw new IllegalArgumentException("Target unit must not be null");
        }
        double baseInch = this.convertToBaseUnit() + thatLength.convertToBaseUnit();
        Length l1 = new Length(baseInch, LengthUnit.INCHES);
        return l1.convertTo(this.unit);
    }

    public Length add(Length length, LengthUnit targetUnit) {
        if (length == null || targetUnit == null) {
            throw new IllegalArgumentException("Target unit must not be null");
        }
        double baseInch = this.convertToBaseUnit() + length.convertToBaseUnit();
        Length l1 = new Length(baseInch, LengthUnit.INCHES);
        return l1.convertTo(targetUnit);
    }

    private Length addAndConvert(Length length, LengthUnit targetUnit) {
        if (length == null || targetUnit == null) {
            throw new IllegalArgumentException("Target unit must not be null");
        }
        double baseUnit = this.convertToBaseUnit() + length.convertToBaseUnit();
        return convertFromBaseToTargetUnit(baseUnit, targetUnit);
    }

    private Length convertFromBaseToTargetUnit(double lengthInInches, LengthUnit targetUnit) {
        if (Double.isNaN(lengthInInches) || targetUnit == null) {
            throw new IllegalArgumentException("Target unit must not be null");
        }
        double targetValue = targetUnit.convertFromBaseUnit(lengthInInches);
        return new Length(targetValue, targetUnit);
    }

    public static void main(String[] args) {
        Length length1 = new Length(1, LengthUnit.FEET);
        System.out.print(length1);

        System.out.print(" is ");
        Length length2 = length1.convertTo(LengthUnit.INCHES);
        System.out.println(length2);

        System.out.print(" -----------------------add ---------- ");
        Length length3 = new Length(12.0, LengthUnit.INCHES);
        Length length4 = length1.add(length3, LengthUnit.FEET);
        System.out.println(length4);

        System.out.print(" ------Equals ---------- ");
        Length length5 = new Length(36.0, LengthUnit.INCHES);
        Length length6 = new Length(1.0, LengthUnit.YARDS);
        System.out.println(length5.equals(length6));

        System.out.print(" ------------------yard-----add ---------- ");
        Length length7 = new Length(1.0, LengthUnit.YARDS);
        Length length8 = length7.add(new Length(3.0, LengthUnit.FEET), LengthUnit.YARDS);
        System.out.println(length8);

        System.out.println(" ------------------Convet to inch----- ---------- ");
        Length length9 = new Length(2.54, LengthUnit.CENTIMETERS);
        System.out.println(length9);
        Length length10 = length9.convertTo(LengthUnit.INCHES);
        System.out.println(length10);

        System.out.println(" ------------------feet with 0-----add ---------- ");
        Length length11 = new Length(5.0, LengthUnit.FEET);
        Length length12 = length11.add(new Length(0.0, LengthUnit.INCHES), LengthUnit.FEET);
        System.out.println(length12);

        LengthUnit.FEET.convertToBaseUnit(12.0);
    }

    @Override
    public String toString() {
        return "Length{" +
                value + " " + unit +
                '}';
    }

    public double getValue() {
        return value;
    }
}
