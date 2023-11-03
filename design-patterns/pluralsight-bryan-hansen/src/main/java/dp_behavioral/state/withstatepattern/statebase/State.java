package dp_behavioral.state.withstatepattern.statebase;

public abstract class State {

    public void handleRequest() {
        System.out.println("Shouldn't be able to get here.");
    }

}
