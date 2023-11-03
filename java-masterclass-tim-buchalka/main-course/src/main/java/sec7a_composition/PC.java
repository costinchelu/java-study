package sec7a_composition;

// example with "has a" relationship

public class PC {
	
    private Case theCase;
    private Monitor monitor;
    private Motherboard motherboard;

    public PC(Case theCase, Monitor monitor, Motherboard motherboard) {
        this.theCase = theCase;
        this.monitor = monitor;
        this.motherboard = motherboard;
    }

    //GETTERS
    
    public Case getTheCase() {
        return theCase;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }



    //ACTIONS
    private void drawLogo() {
        monitor.drawPixelAt(1200, 800, "green");
    }


    public void powerUP()
    {
        theCase.pressPowerButton();
        monitor.drawPixelAt(1200, 800, "red");
        motherboard.loadProgram("Windows 1.0");
    }
}
