package sec16e_function;

import sec16d_functional_interfaces.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;

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

        // using a lambda function to print last names from the employees list:
        employees.forEach(employee -> {
            String lastName = employee.getName().substring(employee.getName().indexOf(" ") + 1);
            // select everything from the first character after the first space
            System.out.println("Last name " + (employees.indexOf(employee) + 1) + ": " + lastName);
        });

        /*
        using a function to print last names from employees:
        from the generics: first type is argument type, second is the return type
        when using function, we need the curly braces even for a one line return.
        By contrast, in a normal lambda we don't even specify "return" when having a one line expression
        */

        Function<Employee, String> getLastName = (Employee employee) -> {
          return employee.getName().substring(employee.getName().indexOf(" ") + 1);
        };

        Function<Employee, String> getFirstName = (Employee employee) -> {
            return employee.getName().substring(0, employee.getName().indexOf(" "));
        };

        System.out.println("Tim's last name is: " + getLastName.apply(employees.get(1)).toUpperCase());

        // using getAName function to print first or last name based on a random boolean (true print first name)
        Random random = new Random();
        for(Employee employee : employees) {
            if(random.nextBoolean()) {
                System.out.println(getAName(getFirstName, employee));
            } else {
                System.out.println(getAName(getLastName, employee));
            }
        }


        // Example chaining functions:
        Function<Employee, String> employeeNameToUppercase = employee -> employee.getName().toUpperCase();
        Function<String, String> chainedFirstName = firstName -> firstName.substring(0, firstName.indexOf(" "));
        Function chainedFunction = employeeNameToUppercase.andThen(chainedFirstName);
        System.out.println(chainedFunction.apply(employees.get(0)));

        BiFunction<String, Employee, String> concatenateAgeToName = (String name, Employee employee) -> {
            return name.concat(" " + employee.getAge());
        };

        // because we need a String as the first argument in the BiFunction:
        String upperName = employeeNameToUppercase.apply(employees.get(0));
        System.out.println(concatenateAgeToName.apply(upperName, employees.get(0)));


        // Unary operator example:
        IntUnaryOperator incrementBy5 = i -> i + 5;
        System.out.println(incrementBy5.applyAsInt(10));


        Consumer<String> c1 = s -> s.toUpperCase();
        Consumer<String> c2 = s -> System.out.println(s);
        c1.andThen(c2).accept("Hello World");
    }

    // apply method of function interface will run the function specified with the employee supplied
    private static String getAName(Function<Employee, String> getName, Employee employee) {
        return getName.apply(employee);
    }
}

/*
INTERFACE           FUNCTIONAL METHOD       NO. OF ARGUMENTS        RETURNS A VALUE             CHAINED?
Consumer            accept()                1 or 2(Bi)              No                          Yes
Supplier            get()                   0                       Yes                         No
Predicate           test()                  1 or 2(Bi)              Yes (boolean)               Yes
Function            apply()                 1 or 2(Bi)              Yes                         Yes
UnaryOperator       depends on type         1                       yes (same as argument)      Yes
 */