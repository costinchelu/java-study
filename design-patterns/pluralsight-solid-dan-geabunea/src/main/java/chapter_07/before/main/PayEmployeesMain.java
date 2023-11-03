package chapter_07.before.main;

import chapter_07.before.payment.PaymentProcessor;

public class PayEmployeesMain {

    /*
    Will take a couple of seconds to execute due to the sending of mails.
     */

    public static void main(String[] args) {
        PaymentProcessor paymentProcessor = new PaymentProcessor();
        int totalPayments = paymentProcessor.sendPayments();
        System.out.println("Total payments " + totalPayments);
    }
}
/*
this works but our application is very coupled
 */