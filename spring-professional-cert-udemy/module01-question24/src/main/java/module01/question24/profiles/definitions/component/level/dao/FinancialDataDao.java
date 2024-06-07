package module01.question24.profiles.definitions.component.level.dao;

import module01.question24.profiles.definitions.component.level.ds.FinancialMonthSummary;
import module01.question24.profiles.definitions.component.level.ds.FinancialQuarterSummary;
import module01.question24.profiles.definitions.component.level.ds.FinancialYearSummary;

public interface FinancialDataDao {

    FinancialYearSummary findFinancialYearSummary(int year);

    FinancialQuarterSummary findFinancialQuarterSummary(int year, int quarter);

    FinancialMonthSummary findFinancialMonthSummary(int year, int month);
}
