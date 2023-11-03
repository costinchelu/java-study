package chapter_04.good.taxes;


import common.personnel.*;


public class FullTimeTaxCalculator implements TaxCalculator {

    private final int RETIREMENT_TAX_PERCENTAGE = 10;
    private final int INCOME_TAX_PERCENTAGE = 16;
    private final int BASE_HEALTH_INSURANCE = 100;


    public double calculate(Employee employee) {
        return BASE_HEALTH_INSURANCE +
                (employee.getMonthlyIncome() * RETIREMENT_TAX_PERCENTAGE) / 100 +
                (employee.getMonthlyIncome() * INCOME_TAX_PERCENTAGE) / 100;
    }
}

/*
* Applying the strategy pattern we will rename this class to FullTimeTaxCalculator to be used only
* in case that we have a full time employee which tax we need to calculate.
*
* This will implement a common interface (strategy pattern) with all other calculator types.
* The calculate() method will be specific for every employee type.
* */