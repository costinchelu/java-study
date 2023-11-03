package chapter_05.before.main;

import common.logging.*;
import chapter_05.before.persistence.EmployeeFileSerializer;
import chapter_05.before.persistence.EmployeeRepository;
import chapter_05.before.personnel.Employee;
import chapter_05.before.personnel.ServiceLicenseAgreement;
import chapter_05.before.personnel.Subcontractor;

import java.util.List;

public class ApproveSLAMain {
    public static void main(String[] args) {
        // Create dependencies
        ConsoleLogger consoleLogger = new ConsoleLogger();
        EmployeeFileSerializer employeeFileSerializer = new EmployeeFileSerializer();
        EmployeeRepository repository = new EmployeeRepository(employeeFileSerializer);

        // Define SLA
        int minTimeOffPercent = 98;
        int maxResolutionDays = 2;
        ServiceLicenseAgreement companySla = new ServiceLicenseAgreement(
                minTimeOffPercent,
                maxResolutionDays);

        // Grab subcontractors
        List<Employee> subcontractors = repository.findAll();

        for (Employee e : subcontractors){
            if(e instanceof  Subcontractor){
                Subcontractor s = (Subcontractor)e;
                s.approveSLA(companySla);
                // in this case, the program will work because the approveSLA() will be called only if
                // the employee is a subcontractor
                // but it still looks strange to check every employee (something seems wrong)
            }
        }
    }
}
