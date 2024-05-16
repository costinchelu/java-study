package howto.map_interface;

import howto.model.Department;
import howto.model.Employee;
import howto.model.Person;
import howto.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamCollectionsGrouping {

    private static final int PASS_THRESHOLD = 5;

    public static void main(String[] args) {

        // Group employees by department
        List<Employee> employees = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        List<Person> persons = new ArrayList<>();


        Map<Department, List<Employee>> byDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        // Compute sum of salaries by department
        Map<Department, Integer> totalByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summingInt(Employee::getSalary)));

        // Partition students into passing and failing
        Map<Boolean, List<Student>> passingFailing = students.stream()
                .collect(Collectors.partitioningBy(s -> s.getGrade() >= PASS_THRESHOLD));

        // Classify Person objects by city
        Map<String, List<Person>> peopleByCity = persons.stream()
                .collect(Collectors.groupingBy(Person::getCity));

        // Cascade Collectors
        Map<String, Map<String, List<Person>>> peopleByStateAndCity = persons.stream()
                .collect(Collectors.groupingBy(Person::getState, Collectors.groupingBy(Person::getCity)));
    }
}
