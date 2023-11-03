package callback;

import lombok.extern.java.Log;

import java.util.function.DoubleConsumer;


@Log
public class CallbackExample {

    private static final double SALARY = 50_000.00;
    private static final double TAX = 5; // 5 percent of our gross salary
    private static final double RENT = 20_000.00;

    public static void getSalary(DoubleConsumer callback) {
        log.info("Getting salary...");
        // call back our callback function
        callback.accept(SALARY);
    }

    public static void deductTax(double grossSalary, DoubleConsumer callback) {
        log.info("Deduct tax...");
        double afterTax = grossSalary * (100 - TAX) / 100;
        callback.accept(afterTax);
    }

    public static void payRent(double afterTaxSalary, DoubleConsumer callback) {
        log.info("Pay rent...");
        double afterRent = afterTaxSalary - RENT;
        callback.accept(afterRent);
    }


    public static void main(String... args) {
        // here we are passing a consumer function as an argument
        getSalary(grossSalary -> {
            log.info("Gross salary :" + grossSalary);
            deductTax(grossSalary, afterTaxSalary -> {
                log.info("After tax :" + afterTaxSalary);
                payRent(afterTaxSalary, afterRentPayment -> log.info("After rent :" + afterRentPayment));
            });
        });
    }
}