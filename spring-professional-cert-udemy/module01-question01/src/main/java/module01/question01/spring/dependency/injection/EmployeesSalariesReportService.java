package module01.question01.spring.dependency.injection;

import module01.question01.commons.bls.EmployeeSalaryCalculator;
import module01.question01.commons.dao.EmployeeDao;
import module01.question01.commons.ds.Employee;
import module01.question01.commons.ds.EmployeeSalary;
import module01.question01.commons.report.SalaryReport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class EmployeesSalariesReportService {
    private final EmployeeDao employeeDao;
    private final EmployeeSalaryCalculator employeeSalaryCalculator;
    private final SalaryReport salaryReport;

    EmployeesSalariesReportService(EmployeeDao employeeDao, EmployeeSalaryCalculator employeeSalaryCalculator, SalaryReport salaryReport) {
        this.employeeDao = employeeDao;
        this.employeeSalaryCalculator = employeeSalaryCalculator;
        this.salaryReport = salaryReport;
    }

    void generateReport() {
        List<Employee> employees = employeeDao.findAll();
        List<EmployeeSalary> employeeSalaries = employeeSalaryCalculator.calculateSalaries(employees);
        salaryReport.writeReport(employeeSalaries);
    }
}
