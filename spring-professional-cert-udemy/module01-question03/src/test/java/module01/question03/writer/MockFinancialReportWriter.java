package module01.question03.writer;

import module01.question03.ds.FinancialMonthSummary;
import module01.question03.ds.FinancialQuarterSummary;
import module01.question03.ds.FinancialYearSummary;

public class MockFinancialReportWriter implements FinancialReportWriter {

    @Override
    public void appendFreeText(String title) {
        System.out.println("Mock Writer => appendFreeText");
    }

    @Override
    public void appendFinancialYearSummary(FinancialYearSummary financialYearSummary) {
        System.out.println("Mock Writer => appendFinancialYearSummary");
    }

    @Override
    public void appendFinancialQuarterSummary(FinancialQuarterSummary financialQuarterSummary) {
        System.out.println("Mock Writer => appendFinancialQuarterSummary");
    }

    @Override
    public void appendFinancialMonthSummary(FinancialMonthSummary financialMonthSummary) {
        System.out.println("Mock Writer => appendFinancialMonthSummary");
    }
}