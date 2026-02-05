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
            if (obj == this) return true;

            System.out.println("inside equals");
            if (this.getClass() != obj.getClass()) return false;

            System.out.println("in equals method");

            return Double.compare(value1, ((Feet) obj).getValue1()) == 0 && Double.compare(value2, ((Feet) obj).getValue2()) == 0;

        }

        @Override
        public int hashCode() {
            return Objects.hash(value1, value2);
        }
    }

    public static class Inches {
        private final double value1;
        private final double value2;


        public Inches(double value1, double value2) {
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
            if (this == obj) return true;

            System.out.println("inside equals");

            if (this.getClass() != obj.getClass()) return false;
            return Double.compare(this.value1, ((Inches) obj).getValue1()) == 0
                    && Double.compare(this.value2, ((Inches) obj).getValue2()) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value1, value2);
        }
    }

    public static void demonstrateFeetEquality() {
        Feet f1 = new Feet(1.1, 2.2);
        Feet f2 = new Feet(1.1, 2.2);

        System.out.println("Feet Equality");
        System.out.println(f1.equals(f2));
    }

    public static void demonstrateInchesEquality() {
        Inches i1 = new Inches(1.0, 2.0);
        Inches i2 = new Inches(1.0, 2.0);

        System.out.println("Inches Equality");
        System.out.println(i1.equals(i2));
    }

    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
    }

}
