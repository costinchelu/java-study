package chapter_03.before;

import common.logging.*;
import common.persistence.*;
import common.personnel.*;

import java.io.IOException;
import java.util.List;

public class SaveEmployeesMain {

    public static void main(String[] args) {
        ConsoleLogger consoleLogger = new ConsoleLogger();
        // Grab employees
        EmployeeFileSerializer employeeFileSerializer = new EmployeeFileSerializer();
        EmployeeRepository repository = new EmployeeRepository(employeeFileSerializer);
        List<Employee> employees = repository.findAll();

        // Save all
        for (Employee e : employees){
            try {
                repository.save(e);
                consoleLogger.writeInfo("Saved employee: " + e.getFullName());
            } catch (IOException ex) {
                consoleLogger.writeError("Error saving employee", ex);
            }
        }
    }
}
