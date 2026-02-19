package com.apps.quantitymeasurement;

import java.util.Objects;

public class Quantity<U extends Imeasurable> {
    private double value;
    private U unit;

    public Quantity(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit must not be a null");
        }
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Please enter the floating point value");
        }
        this.value = value;
        this.unit = unit;
    }

    public U getUnit() {
        return unit;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Quantity<U> quantity = (Quantity<U>) o;
        return this.compare(quantity);
    }

    private boolean compare(Quantity<U> quantity) {
        if (quantity == null) return false;
        return Double.compare(Math.round(this.unit.convertToBaseUnit(this.value)), Math.round(quantity.unit.convertToBaseUnit(quantity.value))) == 0;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit must not be null");
        }
        return convertFromBaseToTargetUnit(this.unit.convertToBaseUnit(this.value), targetUnit);
    }

    private Quantity<U> convertFromBaseToTargetUnit(double value, U targetUnit) {
        if (Double.isNaN(value) || targetUnit == null) {
            throw new IllegalArgumentException("Target unit must not be null");
        }
        double targetValue = targetUnit.convertFromBaseUnit(value);
        return new Quantity<U>(targetValue, targetUnit);
    }

    public Quantity<U> add(Quantity<U> thatQty) {
        if (thatQty == null) {
            throw new IllegalArgumentException("Target unit must not be null");
        }
        double baseValue = this.unit.convertToBaseUnit(value) + thatQty.unit.convertToBaseUnit(thatQty.value);
        return new Quantity<U>(this.unit.convertFromBaseUnit(baseValue), this.unit);
    }

    public Quantity<U> add(Quantity<U> thatQty, U targetUnit) {
        if (thatQty == null || targetUnit == null) {
            throw new IllegalArgumentException("Target unit must not be null");
        }
        double baseValue = this.unit.convertToBaseUnit(value) + thatQty.unit.convertToBaseUnit(thatQty.value);
        return new Quantity<U>(targetUnit.convertFromBaseUnit(baseValue), targetUnit);
    }

    @Override
    public String toString() {
        return "Quantity {" + value + " " + unit + '}';
    }

    public static void main(String[] args) {
        System.out.println(" ------------------Convet Length to inch----- ---------- ");
        Quantity<LengthUnit> length = new Quantity<LengthUnit>(2.54, LengthUnit.CENTIMETERS);
        System.out.println(length);
        Quantity<LengthUnit> length1 = length.convertTo(LengthUnit.INCHES);
        System.out.println(length1);

        System.out.println(" ------------------Check Equals for Length----- ---------- ");
        Quantity<LengthUnit> lengthInFeet = new Quantity<LengthUnit>(10, LengthUnit.FEET);
        Quantity<LengthUnit> lengthInInches = new Quantity<LengthUnit>(120, LengthUnit.INCHES);
        boolean isEqual = lengthInFeet.equals(lengthInInches);
        System.out.println("r Lengths equal - " + isEqual + "  " + lengthInFeet.toString() + lengthInInches.toString());

        System.out.println(" ------------------Check Equals for Weight ----- ---------- ");
        Quantity<WeightUnit> weightinKg = new Quantity<WeightUnit>(1, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> weightInGrams = new Quantity<WeightUnit>(1000, WeightUnit.GRAM);
        isEqual = weightinKg.equals(weightInGrams);
        System.out.println("Are Weights equal - " + isEqual + "  " + weightinKg.toString() + weightInGrams.toString());

        System.out.println(" ------------------ conversion ----- ---------- ");
        Quantity<LengthUnit> convertedLength = lengthInFeet.convertTo(LengthUnit.INCHES);
        System.out.println("10 feet in inches = " + convertedLength);

        System.out.println(" ------------------ add length ----- ---------- ");
        Quantity<LengthUnit> totalLength = lengthInFeet.add(lengthInInches);
        System.out.println("totalLength " + totalLength + "_" + lengthInFeet.toString() + lengthInInches.toString());

        System.out.println(" ------------------add weight ----- ---------- ");
        Quantity<WeightUnit> totalWeight = weightinKg.add(weightInGrams, WeightUnit.GRAM);
        System.out.println("totalWeight " + totalWeight + "_" + weightinKg.toString() + weightInGrams.toString());
    }
}
