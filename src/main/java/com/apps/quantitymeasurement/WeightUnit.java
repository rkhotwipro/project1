package com.apps.quantitymeasurement;

public enum WeightUnit implements Imeasurable{
    //Conversion factor base unit is grams
    MILLIGRAM(0.001),
    GRAM(1),
    KILOGRAM(1000),
    POUND(453.592),
    TONNE(1000000);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return Math.round(value * getConversionFactor() * 100.0) / 100.0;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return Math.round(baseValue / this.getConversionFactor() * 100.0) / 100.0;
    }

    @Override
    public String getUnitName() {
        return "WeightUnit";
    }

    public static void main(String[] args) {
        double kg = .50;
        double gr = WeightUnit.KILOGRAM.convertToBaseUnit(kg);
        System.out.println(kg + " KG means " + gr + " Grams ");

        double milligrams = WeightUnit.MILLIGRAM.convertFromBaseUnit(gr);
        System.out.println(gr + " grams means " + milligrams + " miligrams ");
    }
}
