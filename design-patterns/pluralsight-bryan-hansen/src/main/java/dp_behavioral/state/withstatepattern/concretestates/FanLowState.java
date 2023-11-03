package dp_behavioral.state.withstatepattern.concretestates;


import dp_behavioral.state.withstatepattern.context.Fan;
import dp_behavioral.state.withstatepattern.statebase.State;

public class FanLowState extends State {

    private Fan fan;

    public FanLowState(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void handleRequest() {
        System.out.println("Turning fan on to medium");
        fan.setState(fan.getFanMedState());
    }

    @Override
    public String toString() {
        return "Fan is on LOW";
    }

}
