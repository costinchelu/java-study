package module01.question01.commons.report;

import module01.question01.commons.ds.EmployeeSalary;

import java.util.List;

public interface SalaryReport {

    void writeReport(List<EmployeeSalary> employeeSalaries);
}
