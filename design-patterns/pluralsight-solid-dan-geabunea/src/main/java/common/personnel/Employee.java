package common.personnel;


import lombok.Getter;

public abstract class Employee {

    private String firstName;

    private String lastName;

    @Getter
    private int monthlyIncome;

    @Getter
    private int nbHoursPerWeek;

    public Employee(String fullName, int monthlyIncome){
        setMonthlyIncome(monthlyIncome);

        String[] names = fullName.split(" ");
        this.firstName = names[0];
        this.lastName = names[1];
    }

    public String getEmail() {
        return this.firstName + "." +
                this.lastName +
                "@globomanticshr.com";
    }

    @Override
    public String toString() {
        return this.firstName + " " +
                this.lastName + " - " +
                this.monthlyIncome;
    }

    public void setMonthlyIncome(int monthlyIncome) {
        if(monthlyIncome < 0){
            throw new IllegalArgumentException("Income must be positive");
        }

        this.monthlyIncome = monthlyIncome;
    }

    public void setNbHoursPerWeek(int nbHoursPerWeek) {
        if(nbHoursPerWeek <= 0){
            throw new IllegalArgumentException("Income must be positive");
        }

        this.nbHoursPerWeek = nbHoursPerWeek;
    }

    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }

/*
we have 3 responsibilities inside the save method: serialization, file access, logging
and also the overall coordination of these steps including the exception handling

save method deals with persistence but the Employee class is a business entity so save
should not be here

the decision is to move the method entirely to a persistence class
also logging should be separate and try catch should be in main (caller)
*/

}
