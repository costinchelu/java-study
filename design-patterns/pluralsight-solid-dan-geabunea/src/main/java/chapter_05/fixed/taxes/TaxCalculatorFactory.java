package chapter_05.fixed.taxes;

import chapter_05.fixed.personnel.Employee;
import chapter_05.fixed.personnel.FullTimeEmployee;
import chapter_05.fixed.personnel.Intern;
import chapter_05.fixed.personnel.PartTimeEmployee;

public class TaxCalculatorFactory {
    public static TaxCalculator create(Employee employee) {
        if (employee instanceof FullTimeEmployee) {
            return new FullTimeTaxCalculator();
        }

        if (employee instanceof PartTimeEmployee) {
            return new PartTimeTaxCalculator();
        }

        if (employee instanceof Intern) {
            return new InternTaxCalculator();
        }

        throw new RuntimeException("Invalid employee type");
    }
}
