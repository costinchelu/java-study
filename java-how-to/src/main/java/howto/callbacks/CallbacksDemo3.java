package howto.callbacks;

import java.util.function.Consumer;

public class CallbacksDemo3 {
}

class Callback {

    public static void getSalary(Consumer<Double> callback) {
        double salary = 50_000.00;
        System.out.println("Get salary...");
        // call back our callback function
        callback.accept(salary);
    }

    public static void deductTax(double grossSalary, Consumer<Double> callback) {
        System.out.println("Deduct tax...");
        double tax = 5; // 5 percent of our gross salary
        double afterTax = grossSalary * (100 - tax) / 100;
        callback.accept(afterTax);
    }

    public static void payRent(double afterTaxSalary, Consumer<Double> callback) {
        System.out.println("Pay rent...");
        double rent = 20_000.00;
        double afterRent = afterTaxSalary - rent;
        callback.accept(afterRent);
    }

    public static void main(String... args) {
        // here we are passing a consumer function as an argument
        getSalary((grossSalary) -> {
            System.out.println("Gross salary :" + grossSalary);
            deductTax(grossSalary, afterTaxSalary -> {
                System.out.println("After tax :" + afterTaxSalary);
                payRent(afterTaxSalary, afterRentPayment -> System.out.println("After rent :" + afterRentPayment));
            });
        });
    }
}
