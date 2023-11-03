package sec16g_flatmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {

    public static void main(String[] args) {
        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Buchalka", 21);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);
        Employee red = new Employee("Red RidingHood", 35);
        Employee charming = new Employee("Prince Charming", 31);

        Department hr = new Department("Human Resources");
        hr.addEmployee(jack);
        hr.addEmployee(snow);
        hr.addEmployee(red);
        hr.addEmployee(charming);
        Department accounting = new Department("Accounting");
        accounting.addEmployee(john);
        accounting.addEmployee(tim);

        List<Department> departments = new ArrayList<>();
        departments.add(hr);
        departments.add(accounting);

        System.out.println("-----------flatMap -> prints all employees from all departments");
        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .forEach(System.out::println);

        // using collector to group employees by age
        Map<Integer, List<Employee>> groupedByAge = departments
        .stream()
        .flatMap(department -> department.getEmployees().stream())
        .collect(Collectors.groupingBy(Employee::getAge));

        System.out.println("------------reduce -> find the youngest employee");
        departments
                .stream()
                .flatMap(department -> department.getEmployees().stream())
                .reduce((e1, e2) -> e1.getAge() < e2.getAge() ? e1 : e2)
                .ifPresent(System.out::println);

        System.out.println("------------count is a terminal operation. Without it nothing will print");
        Stream.of("ABC", "AC", "BAC", "CCC")
                .filter(s -> {
                    System.out.println(s);
                    return s.length() == 3;
                }).count();

        /* observations:

        streams can't be reused
        intermediate operations are not performed until there is a terminal operation (lazily evaluated)
        there are specific stream interfaces (int, long streams etc) which have additional methods
        also parallel streams
         */

    }
}



























