package chapter_05.before.personnel;



public class Subcontractor extends Employee {

    public Subcontractor(String fullName, int monthlyIncome) {
        super(fullName, monthlyIncome);
    }

    @Override
    public void requestTimeOff(int nbDays, Employee manager) {
        throw new RuntimeException("Not implemented");

        /*
        * Because a subcontractor is not eligible for time-off, we cannot implement this method.
        * This means that Subcontractor does not fully substitute Employee.
        * (it violates Liskov Substitution Principle)
        * */
    }

    public boolean approveSLA(ServiceLicenseAgreement sla) {
        /*
        Business logic for approving a service license agreement for a for a subcontractor
         */
        boolean result = false;
        if (sla.getMinUptimePercent() >= 98
                && sla.getProblemResolutionTimeDays() <= 2) {
            result=  true;
        }

        System.out.println("SLA approval for " + this.getFullName() + ": " + result);
        return result;
    }
}

/*
analyzing all the problems we get for creating this class, we get the conclusion
that this is not the best approach
 */
