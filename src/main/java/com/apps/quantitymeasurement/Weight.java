package com.apps.quantitymeasurement;

public class Weight {

    private double value;
    private WeightUnit unit;

    public Weight(double value, WeightUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    public boolean compare(Weight weight) {
        return Double.compare(this.convertToBaseUnit(), weight.convertToBaseUnit()) == 0;
    }

    private double convertToBaseUnit() {
        return this.unit.convertToBaseUnit(this.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (this.getClass() != obj.getClass()) return false;
        return this.compare((Weight) obj);
    }

    public Weight convertTo(WeightUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit should not be  null");
        }
        double baseUnit = this.convertToBaseUnit();
        return new Weight(convertFromBaseToTargetUnit(baseUnit, targetUnit), targetUnit);
    }

    public Weight add(Weight weight) {
        if (weight == null) {
            throw new IllegalArgumentException("Weight object should not be null");
        }
        return addAndConvert(weight, weight.getUnit());
    }

    public Weight add(Weight weight, WeightUnit weightUnit) {
        if (weight == null || weightUnit == null) {
            throw new IllegalArgumentException("Weight object should not be null");
        }
        return addAndConvert(weight, weightUnit);
    }

    private Weight addAndConvert(Weight weight, WeightUnit targetUnit) {
        double sumBaseUnit = this.convertToBaseUnit() + weight.convertToBaseUnit();
        return new Weight(convertFromBaseToTargetUnit(sumBaseUnit, targetUnit), targetUnit);
    }

    private double convertFromBaseToTargetUnit(double weightInGrams, WeightUnit targetUnit) {
        return targetUnit.convertFromBaseUnit(weightInGrams);
    }

    @Override
    public String toString() {
        return "Weight{" +
                value + " " + unit +
                '}';
    }

    public static void main(String[] args) {
        Weight weight = new Weight(1000.0, WeightUnit.GRAM);
        Weight weight1 = weight.convertTo(WeightUnit.KILOGRAM);
        System.out.println(weight1);
    }
}
