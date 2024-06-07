package module01.question24.profiles.definitions.bean.method.level.writer;

import module01.question24.profiles.definitions.bean.method.level.ds.FinancialMonthSummary;
import module01.question24.profiles.definitions.bean.method.level.ds.FinancialQuarterSummary;
import module01.question24.profiles.definitions.bean.method.level.ds.FinancialYearSummary;

public interface FinancialReportWriter {

    void appendFreeText(String title);

    void appendFinancialYearSummary(FinancialYearSummary financialYearSummary);

    void appendFinancialQuarterSummary(FinancialQuarterSummary financialQuarterSummary);

    void appendFinancialMonthSummary(FinancialMonthSummary financialMonthSummary);
}
