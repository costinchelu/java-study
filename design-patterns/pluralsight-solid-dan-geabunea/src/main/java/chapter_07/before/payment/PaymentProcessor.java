package chapter_07.before.payment;

import chapter_07.before.notifications.EmailSender;
import chapter_07.before.persistence.EmployeeFileRepository;
import chapter_07.before.persistence.EmployeeFileSerializer;
import chapter_07.before.personnel.Employee;

import java.util.List;

public class PaymentProcessor {

    private EmployeeFileRepository employeeRepository;

    public PaymentProcessor(){
        EmployeeFileSerializer serializer = new EmployeeFileSerializer();
        this.employeeRepository = new EmployeeFileRepository(serializer);
    }

    public int sendPayments(){
        List<Employee> employees = this.employeeRepository.findAll();
        int totalPayments = 0;

        for(Employee employee : employees){
            totalPayments += employee.getMonthlyIncome();
            EmailSender.notify(employee);
        }

        return totalPayments;
    }
}
/*
this component is highly coupled with EmployeeFileRepository and EmailSender

in the constructor it's creating the dependencies (EmployeeFileRepository and EmployeeFileSerializer

in the case of EmailSender we call a static method but even without instantiation, a static method call is a sign
of coupling

what will happen if a particular client doesn't want to read employees from a csv file? It may want to read them
from a database. What will happen if he just wants to send employees some sms instead of emails? The way we have
written our code it's impossible to reconfigure a t runtime. PaymentProcessor initializes it's own EmployeeFileRepo
and depends directly on the EmailSender. We'are not only violating the dependency inversion principle but we are
also violating the open-closed principle. Testing is also affected

 */