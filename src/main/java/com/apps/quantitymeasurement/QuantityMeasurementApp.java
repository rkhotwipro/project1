package com.apps.quantitymeasurement;

import java.util.Objects;

public class QuantityMeasurementApp {
    public static class Feet {
        private final double value1;
        private final double value2;

        public Feet(double value1, double value2) {
            this.value1 = value1;
            this.value2 = value2;
        }

        public double getValue1() {
            return value1;
        }

        public double getValue2() {
            return value2;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (obj == this) return false;
            if (this.getClass() != obj.getClass()) return false;

            System.out.println("in equals method");

            return Double.compare(value1, ((Feet) obj).getValue1()) == 0 && Double.compare(value2, ((Feet) obj).getValue2()) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value1, value2);
        }
    }


    public static void main(String[] args) {

        Feet f1 = new Feet(1.11, 2.22);
        Feet f2 = new Feet(1.11, 2.222);

        System.out.println("Object1 : " + f1.getValue1() + " , " + f1.getValue2());
        System.out.println("Object2 : " + f2.getValue1() + " , " + f2.getValue2());

        System.out.println("The result of comparison is : " + f1.equals(f2));
    }

}
