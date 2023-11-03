package sec16d_functional_interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public class Main {

    public static void main(String[] args) {
        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Buchalka", 21);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);
        Employee red = new Employee("Red RidingHood", 35);
        Employee charming = new Employee("Prince Charming", 31);


        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);
        employees.add(red);
        employees.add(charming);

        /*
        System.out.println("Employees over 30:");
        System.out.println("==================");
        employees.forEach(employee -> {
            if(employee.getAge() > 30) {
                System.out.println(employee.getName());
            }
        });

        // for each doesn't return anything (used for concise iteration)
        // we can also use an enhanced for loop:

        for(Employee e : employees) {
            if(e.getAge() > 30) {
                System.out.println(e.getName());
            }
        }
        */

        printEmployeesByAge(employees, "Employees over 30", employee -> employee.getAge() > 30);
        printEmployeesByAge(employees, "\nEmployees 30 and under", employee -> employee.getAge() <= 30);

        // or we can use an anonymous class (because Predicates have only one method(test)):
        printEmployeesByAge(employees, "\nEmployees younger than 25", new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() < 25;
            }
        });

        // using a lambda function to print last names for employees list:
        employees.forEach(employee -> {
            String lastName = employee.getName().substring(employee.getName().indexOf(" ") + 1);
            // select everything from the first character after the first space
            System.out.println("Last name " + (employees.indexOf(employee) + 1) + ": " + lastName);
        });
    }


    // using a Predicate (interface that has a test method which tests for a given expression)
    // the Predicate interface accepts one parameter and returns a boolean value
    private static void printEmployeesByAge(List<Employee> employees,
                                            String ageText,
                                            Predicate<Employee> ageCondition) {

        System.out.println(ageText);
        System.out.println("===================");
        for(Employee employee : employees) {
            if (ageCondition.test(employee)) {
                System.out.println(employee.getName());
            }
        }
    }

}
