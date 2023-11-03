package chapter_07.before.payment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentProcessorTest {


    @Test
    public void send_payments_should_pay_all_employee_salaries() {
        // arrange
        PaymentProcessor paymentProcessor = new PaymentProcessor();

        // act
        int result = paymentProcessor.sendPayments();

        // assert
        assertEquals(5440, result);
    }
}
/*
we want to test the PaymentProcessor by sending payments and asserting the result to be equal to 5440
we computed the result by looking at the employees.csv file

if we are building this in an environment where we don't have an internet connection  the mail sender module
will throw an exception

also, what happens when we modify our csv? We should not care how and where the employees are retrieve. All
these problems are caused by coupling
 */