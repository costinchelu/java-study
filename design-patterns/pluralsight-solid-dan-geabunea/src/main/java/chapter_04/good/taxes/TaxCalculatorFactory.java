package chapter_04.good.taxes;


import common.personnel.*;


public class TaxCalculatorFactory {

    public static TaxCalculator create(Employee employee) {
        if(employee instanceof FullTimeEmployee) {
            return new FullTimeTaxCalculator();
        }

        if(employee instanceof PartTimeEmployee) {
            return new PartTimeTaxCalculator();
        }

        if (employee instanceof Intern) {
            return new InternTaxCalculator();
        }

        throw new RuntimeException("Invalid employee type");
    }
}

/*
* Implementing a new Factory method which receives an Employee and determines what type of employee
* it gets. Based on that, it will return a specific taxCalculator instance.
* */