package chapter_07.fixed.payment;

import chapter_07.fixed.notifications.EmployeeNotifier;
import chapter_07.fixed.persistence.EmployeeRepository;

import chapter_07.fixed.personnel.Employee;

import java.util.List;

public class PaymentProcessor {

    private EmployeeRepository employeeRepository;
    private EmployeeNotifier employeeNotifier;

    // dependency injection:
    public PaymentProcessor(EmployeeRepository employeeRepository, EmployeeNotifier employeeNotifier) {
        this.employeeRepository = employeeRepository;
        this.employeeNotifier = employeeNotifier;
        /*
        now we can pass at runtime any type of repository we want: file repo, sql repo, noSql...
        also instead of just mail, with the employeeNotifier we can pass a slack or sms notifier for example

        The code is no longer coupled
         */
    }

    public int sendPayments(){
        List<Employee> employees = this.employeeRepository.findAll();
        int totalPayments = 0;

        for(Employee employee : employees){
            totalPayments += employee.getMonthlyIncome();
            this.employeeNotifier.notify(employee);
        }

        return totalPayments;
    }
}
/*
initially the payment processor was coupled with other low level modules(classes).
Now it will have 2 dependencies on created abstractions
 */