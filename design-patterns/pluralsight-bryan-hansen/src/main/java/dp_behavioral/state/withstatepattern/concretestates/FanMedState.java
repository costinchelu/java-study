package dp_behavioral.state.withstatepattern.concretestates;


import dp_behavioral.state.withstatepattern.context.Fan;
import dp_behavioral.state.withstatepattern.statebase.State;

public class FanMedState extends State {

    private Fan fan;

    public FanMedState(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void handleRequest() {
        System.out.println("Turning fan on to high");
        fan.setState(fan.getFanHighState());
    }

    @Override
    public String toString() {
        return "Fan is on MEDIUM";
    }
}
