package chapter_04.wrong.taxes;


import common.personnel.*;


public class TaxCalculator {
    private final int RETIREMENT_TAX_PERCENTAGE = 10;
    private final int INCOME_TAX_PERCENTAGE = 16;
    private final int BASE_HEALTH_INSURANCE = 100;

    // after the 2nd time we receive business requirements we are tempted to modify
    // existing calculate(Employee) method to fulfill the new requirements
    // It will result in a complex method that does several conditional calculations
    // which will be even more complex as business requirements changes in time.

    public double calculate(Employee employee) {
        int monthlyIncome = employee.getMonthlyIncome();

        if(employee instanceof FullTimeEmployee) {
            return
                    BASE_HEALTH_INSURANCE +
                            monthlyIncome * ((double) RETIREMENT_TAX_PERCENTAGE / 100) +
                            monthlyIncome * ((double) INCOME_TAX_PERCENTAGE / 100);
        }

        if(employee instanceof PartTimeEmployee) {
            return
                    BASE_HEALTH_INSURANCE +
                            monthlyIncome * ((double) RETIREMENT_TAX_PERCENTAGE / 2 / 100) +
                            monthlyIncome * ((double) INCOME_TAX_PERCENTAGE / 100);
        }

        if(employee instanceof Intern) {
            if(monthlyIncome < 350) {
                return 0;
            } else {
                return monthlyIncome * ((double) INCOME_TAX_PERCENTAGE / 100);
            }
        }

        return 0;
    }
}
