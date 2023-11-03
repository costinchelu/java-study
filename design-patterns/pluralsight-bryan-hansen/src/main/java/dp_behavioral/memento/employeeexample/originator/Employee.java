package dp_behavioral.memento.employeeexample.originator;


import dp_behavioral.memento.employeeexample.memento.EmployeeMemento;

public class Employee {

    private String name;
    private String address;
    private String phone;


    public EmployeeMemento save() {
        return new EmployeeMemento(name, phone);
    }

    public void revert(EmployeeMemento e) {
        this.name = e.name();
        this.phone = e.phone();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "[" + name + " : " + address + " : " + phone + ']';
    }
}
