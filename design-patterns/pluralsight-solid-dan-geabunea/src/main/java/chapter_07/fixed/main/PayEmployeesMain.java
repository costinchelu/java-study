package chapter_07.fixed.main;

import chapter_07.fixed.notifications.EmailSender;
import chapter_07.fixed.notifications.EmployeeNotifier;
import chapter_07.fixed.payment.PaymentProcessor;
import chapter_07.fixed.persistence.EmployeeFileRepository;
import chapter_07.fixed.persistence.EmployeeFileSerializer;
import chapter_07.fixed.persistence.EmployeeRepository;

public class PayEmployeesMain {

    /*
    Now here we need to create the dependencies for repo and notifier
    in our case, the repo has a csv processor and the notifier sends emails, but that can be extended further
     */

    public static void main(String[] args) {
        EmployeeFileSerializer serializer = new EmployeeFileSerializer();
        EmployeeRepository employeeRepository = new EmployeeFileRepository(serializer);
        EmployeeNotifier employeeNotifier = new EmailSender();

        PaymentProcessor paymentProcessor = new PaymentProcessor(employeeRepository, employeeNotifier);

        int totalPayments = paymentProcessor.sendPayments();
        System.out.println("Total payments " + totalPayments);
    }
}
/*
We need to create instances for serializer, repo and notifier in main app. 

InterfaceClass i = new SubClass();
*/