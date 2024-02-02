package module01.question08.report;

import module01.question08.ds.EmployeeSalary;

import java.util.List;

public interface SalaryReport {

    void writeReport(List<EmployeeSalary> employeeSalaries);
}
