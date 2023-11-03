package dp_behavioral.memento.employeeexample.caretaker;


import dp_behavioral.memento.employeeexample.memento.EmployeeMemento;
import dp_behavioral.memento.employeeexample.originator.Employee;

import java.util.Stack;

public class Caretaker {

    private Stack<EmployeeMemento> employeeHistory = new Stack<>();

    public void save(Employee employee) {
        System.out.println("Saving state...");
        employeeHistory.push(employee.save());
    }

    public void revert(Employee employee) {
        System.out.println("Reverting state...");
        employee.revert(employeeHistory.pop());
    }
}
/*
In this case we choose to only save an Employee's name and phone number so we are storing an EmployeeMemento object
 */