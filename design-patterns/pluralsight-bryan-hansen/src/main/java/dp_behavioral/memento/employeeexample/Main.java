package dp_behavioral.memento.employeeexample;


import dp_behavioral.memento.employeeexample.caretaker.Caretaker;
import dp_behavioral.memento.employeeexample.originator.Employee;

public class Main {

    public static void main(String[] args) {

        Caretaker caretaker = new Caretaker();

        Employee employee = new Employee();
        employee.setName("John Wick");
        employee.setAddress("111 TwitterClientExample.WithoutStatePattern.ComparatorExample.ComparatorExampleStrategy.Main Street");
        employee.setPhone("888-555-1111");
        System.out.println("Employee BEFORE save:                   " + employee);
        caretaker.save(employee);


        employee.setPhone("444-555-1111");
        System.out.println("Employee AFTER changing phone number:   " + employee);
        caretaker.save(employee);


        employee.setPhone("222-555-1111");  //<-- we haven't called save!
        System.out.println("Employee state:                         " + employee);

        caretaker.revert(employee);
        System.out.println("Last save point:                        " + employee);

        caretaker.revert(employee);
        System.out.println("Original:                               " + employee);
    }
}
