package howto.model;

public class Employee {
    public Department getDepartment() {
        return new Department();
    }

    public int getSalary() {
        return 1;
    }
}
