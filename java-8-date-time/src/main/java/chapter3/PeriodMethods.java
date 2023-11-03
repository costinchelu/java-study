package chapter3;


import java.time.Period;

public class PeriodMethods {
    public static void main(String[] args) {
        // ------------------ Factory methods for duration:
        Period P1Y1D = Period.of(1, 0, 1);
        Period P120D = Period.ofDays(120);

        // ------------------ Adjustment methods (add seconds or nanos)
        Period P1Yminus4M1D = P1Y1D.minusMonths(4);
        Period P8M1D = P1Yminus4M1D.withYears(0).withMonths(8);

        // ------------------ Accessor methods:
        int months8 = P8M1D.getMonths();
    }
}
