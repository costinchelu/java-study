package dp_behavioral.state.withstatepattern.context;


import dp_behavioral.state.withstatepattern.concretestates.FanHighState;
import dp_behavioral.state.withstatepattern.concretestates.FanLowState;
import dp_behavioral.state.withstatepattern.concretestates.FanMedState;
import dp_behavioral.state.withstatepattern.concretestates.FanOffState;
import dp_behavioral.state.withstatepattern.statebase.State;

public class Fan {

    State fanOffState;
    State fanLowState;
    State fanMedState;
    State fanHighState;

    State state;

    public Fan() {
        fanOffState = new FanOffState(this);
        fanLowState = new FanLowState(this);
        fanMedState = new FanMedState(this);
        fanHighState = new FanHighState(this);

        state = fanOffState;
    }

    public void pullChain() {
        state.handleRequest();
    }

    public void setState(State state) {
        this.state = state;
    }

    public String toString() {
        return state.toString();
    }

    public State getFanOffState() {
        return fanOffState;
    }

    public State getFanLowState() {
        return fanLowState;
    }

    public State getFanMedState() {
        return fanMedState;
    }

    public State getFanHighState() {
        return fanHighState;
    }
}
