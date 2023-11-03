package chapter_05.fixed.personnel;

public class Subcontractor {

    private final int monthlyIncome;
    private int nbHoursPerWeek;
    private String email;
    private String name;

    public Subcontractor(String name, String email, int nbHoursPerWeek, int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
        this.nbHoursPerWeek = nbHoursPerWeek;
        this.email = email;
        this.name = name;
    }

    public boolean approveSLA(ServiceLicenseAgreement sla) {
        boolean result = false;
        if(sla.getMinUptimePercent() >= 98 && sla.getProblemResolutionTimeDays() <= 2) {
            result = true;
        }

        System.out.println("SLA approval for " + this.name + ": " + result);
        return result;
    }
}
/*
initially this new class has no relationship with Employee class (after we have broken down the ex - Subcontractor class


- Empty methods
- Type checking
- Hardened preconditions
	Are all signs that we are violating LSP
 */


