package chapter_05.fixed.main;


import chapter_05.fixed.persistence.EmployeeFileSerializer;
import chapter_05.fixed.persistence.EmployeeRepository;
import chapter_05.fixed.personnel.Employee;
import chapter_05.fixed.personnel.FullTimeEmployee;
import common.logging.ConsoleLogger;

import java.util.List;

public class NatHolidayEmployeeTimeOffMain {
    public static void main(String[] args) {
        // Create dependencies
        ConsoleLogger consoleLogger = new ConsoleLogger();
        EmployeeFileSerializer employeeFileSerializer = new EmployeeFileSerializer();
        EmployeeRepository repository = new EmployeeRepository(employeeFileSerializer);

        // Grab employees
        List<Employee> employees = repository.findAll();
        Employee manager = new FullTimeEmployee("Steve Jackson", 5000);

        // Request time off for each employee on
        // national holiday
        for (Employee employee : employees){
            employee.requestTimeOff(1, manager);
            // in this case, we forgot that Subcontractor class can't have time offs so when we
            // call this on a subcontractor from the list of employees, we get an exception
        }
    }
}
