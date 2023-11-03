package dp_structural.adapter.legacycode;


import dp_structural.adapter.itarget.Employee;

public class EmployeeDefault implements Employee {

    private String id;
    private String firstName;
    private String lastName;
    private String email;

    public EmployeeDefault(String id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "CodeFromAPI.Employee -> [" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ']';
    }
}

/*
This would be code provided
We need to adapt our employee types to this type
 */