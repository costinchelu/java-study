package module01.question24.profiles.definitions.configuration.level.dao;

import module01.question24.profiles.definitions.configuration.level.ds.FinancialMonthSummary;
import module01.question24.profiles.definitions.configuration.level.ds.FinancialQuarterSummary;
import module01.question24.profiles.definitions.configuration.level.ds.FinancialYearSummary;

public interface FinancialDataDao {

    FinancialYearSummary findFinancialYearSummary(int year);

    FinancialQuarterSummary findFinancialQuarterSummary(int year, int quarter);

    FinancialMonthSummary findFinancialMonthSummary(int year, int month);
}
