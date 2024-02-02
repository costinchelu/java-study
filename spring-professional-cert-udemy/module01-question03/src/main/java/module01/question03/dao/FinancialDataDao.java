package module01.question03.dao;

import module01.question03.ds.FinancialMonthSummary;
import module01.question03.ds.FinancialQuarterSummary;
import module01.question03.ds.FinancialYearSummary;

public interface FinancialDataDao {

    FinancialYearSummary findFinancialYearSummary(int year);

    FinancialQuarterSummary findFinancialQuarterSummary(int year, int quarter);

    FinancialMonthSummary findFinancialMonthSummary(int year, int month);
}
