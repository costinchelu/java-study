package module01.question24.profiles.definitions.custom.annotation.service;

import module01.question24.profiles.definitions.custom.annotation.dao.FinancialDataDao;
import module01.question24.profiles.definitions.custom.annotation.ds.FinancialMonthSummary;
import module01.question24.profiles.definitions.custom.annotation.ds.FinancialQuarterSummary;
import module01.question24.profiles.definitions.custom.annotation.ds.FinancialYearSummary;
import module01.question24.profiles.definitions.custom.annotation.writer.FinancialReportWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FinancialReportService {

    @Autowired
    private FinancialDataDao financialDataDao;
    @Autowired
    private FinancialReportWriter financialReportWriter;

    public void generateReport() {
        FinancialYearSummary financialYearSummary = financialDataDao.findFinancialYearSummary(2019);
        FinancialQuarterSummary financialQuarterSummary = financialDataDao.findFinancialQuarterSummary(2019, 1);
        FinancialMonthSummary financialMonthSummary = financialDataDao.findFinancialMonthSummary(2019, 4);

        financialReportWriter.appendFreeText("Year / Quarter / Month Financial Report");
        financialReportWriter.appendFinancialYearSummary(financialYearSummary);
        financialReportWriter.appendFinancialQuarterSummary(financialQuarterSummary);
        financialReportWriter.appendFinancialMonthSummary(financialMonthSummary);
    }
}
