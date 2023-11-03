package dp_structural.adapter.adapter;


import dp_structural.adapter.adaptee.EmployeeCustom;
import dp_structural.adapter.itarget.Employee;

public class EmployeeAdapter implements Employee {

    private EmployeeCustom instance;

    public EmployeeAdapter(EmployeeCustom instance) {
        this.instance = instance;
    }

    @Override
    public String getId() {
        return instance.getCn();
    }

    @Override
    public String getFirstName() {
        return instance.getGivenName();
    }

    @Override
    public String getLastName() {
        return instance.getSurname();
    }

    @Override
    public String getEmail() {
        return instance.getMail();
    }
}
