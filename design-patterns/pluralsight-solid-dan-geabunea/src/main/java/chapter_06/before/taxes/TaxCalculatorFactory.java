package chapter_06.before.taxes;

import chapter_06.before.personnel.Employee;
import chapter_06.before.personnel.FullTimeEmployee;
import chapter_06.before.personnel.Intern;
import chapter_06.before.personnel.PartTimeEmployee;

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
