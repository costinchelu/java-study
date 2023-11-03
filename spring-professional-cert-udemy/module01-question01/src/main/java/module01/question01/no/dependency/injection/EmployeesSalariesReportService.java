package module01.question01.no.dependency.injection;

import module01.question01.commons.bls.EmployeeSalaryCalculator;
import module01.question01.commons.dao.EmployeeDao;
import module01.question01.commons.ds.Employee;
import module01.question01.commons.ds.EmployeeSalary;
import module01.question01.commons.report.PdfSalaryReport;

import java.util.List;

class EmployeesSalariesReportService {

    void generateReport() {
        EmployeeDao employeeDao = new EmployeeDao();
        List<Employee> employees = employeeDao.findAll();

        EmployeeSalaryCalculator employeeSalaryCalculator = new EmployeeSalaryCalculator();
        List<EmployeeSalary> employeeSalaries = employeeSalaryCalculator.calculateSalaries(employees);

        PdfSalaryReport pdfSalaryReport = new PdfSalaryReport();
        pdfSalaryReport.writeReport(employeeSalaries);
    }
}
