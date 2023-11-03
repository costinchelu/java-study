package chapter_05.fixed.main;

import chapter_05.fixed.persistence.EmployeeFileSerializer;
import chapter_05.fixed.persistence.EmployeeRepository;
import chapter_05.fixed.personnel.Employee;
import chapter_05.fixed.taxes.TaxCalculator;
import chapter_05.fixed.taxes.TaxCalculatorFactory;
import common.logging.ConsoleLogger;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CalculateEmployeeTaxesMain {
    public static void main(String[] args) {
        // Create dependencies
        ConsoleLogger consoleLogger = new ConsoleLogger();
        EmployeeFileSerializer employeeFileSerializer = new EmployeeFileSerializer();
        EmployeeRepository repository = new EmployeeRepository(employeeFileSerializer);

        // Grab employees
        List<Employee> employees = repository.findAll();

        // Calculate taxes
        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        // for each employee
        double totalTaxes = 0;
        for (Employee employee: employees){
            TaxCalculator taxCalculator = TaxCalculatorFactory.create(employee);
            // this line will give an exception because we don't have a tax calculator for a subcontractor
            // of course we can create a new calculator class for subcontractors but that will go
            // against the business requirements (subcontractor_business_requirements.txt)

            // compute individual tax
            double tax = taxCalculator.calculate(employee);
            String formattedTax = currencyFormatter.format(tax);
            consoleLogger.writeInfo(employee.getFullName() + " taxes: " + formattedTax);

            // add to company total taxes
            totalTaxes += taxCalculator.calculate(employee);
        }
        consoleLogger.writeInfo("Total taxes = " + currencyFormatter.format(totalTaxes));
    }
}
