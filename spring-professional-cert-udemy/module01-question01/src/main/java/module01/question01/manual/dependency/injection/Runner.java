package module01.question01.manual.dependency.injection;

import module01.question01.commons.bls.EmployeeSalaryCalculator;
import module01.question01.commons.dao.EmployeeDao;
import module01.question01.commons.report.PdfSalaryReport;

public class Runner {
    public static void main(String... args) {
        EmployeesSalariesReportService employeesSalariesReportService = new EmployeesSalariesReportService(
                new EmployeeDao(),
                new EmployeeSalaryCalculator(),
                new PdfSalaryReport()
        );

        employeesSalariesReportService.generateReport();
    }
}
