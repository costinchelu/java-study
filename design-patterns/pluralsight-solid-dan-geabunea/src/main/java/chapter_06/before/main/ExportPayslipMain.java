package chapter_06.before.main;

import chapter_06.before.documents.Payslip;
import common.logging.ConsoleLogger;
import chapter_06.before.persistence.EmployeeFileSerializer;
import chapter_06.before.persistence.EmployeeRepository;
import chapter_06.before.personnel.Employee;

import java.time.Month;
import java.util.List;

public class ExportPayslipMain {

    public static void main(String[] args) {

        // Create dependencies
        ConsoleLogger consoleLogger = new ConsoleLogger();
        EmployeeFileSerializer employeeFileSerializer = new EmployeeFileSerializer();
        EmployeeRepository repository = new EmployeeRepository(employeeFileSerializer);

        // Grab employees
        List<Employee> employees = repository.findAll();

        // export payslip to txt
        for (Employee employee : employees){
            Payslip payslip = new Payslip(employee , Month.AUGUST);

            String exportableText = payslip.toTxt().toUpperCase();
            System.out.println(exportableText);
        }
    }
}

// this exports payslip for every employee