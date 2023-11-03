package chapter_03.after;

import common.logging.*;
import common.persistence.*;
import common.personnel.*;

import java.io.IOException;
import java.util.List;

public class SaveEmployeesMain {

    public static void main(String[] args) {
        // Grab employees
        EmployeeFileSerializer employeeFileSerializer = new EmployeeFileSerializer();
        ConsoleLogger consoleLogger = new ConsoleLogger();

        EmployeeRepository repository = new EmployeeRepository(employeeFileSerializer);
        List<Employee> employees = repository.findAll();

        // Save all
        for (Employee e : employees){
            try {
                repository.save(e);
                consoleLogger.writeInfo("Saved employee " + e.toString());
            } catch (IOException ex) {
                consoleLogger.writeError("Error saving employee", ex);
            }
        }
    }
}
