package dp_behavioral.state.withoutstatepattern;

public class Main {

    public static void main(String[] args) {

        Fan fan = new Fan();
        System.out.println(fan);

        fan.pullChain();
        System.out.println(fan);

        fan.pullChain();
        System.out.println(fan);

        fan.pullChain();
        System.out.println(fan);

    }
}
/*
- localize state specific behaviour -> state object
- separates what state we are in from where we are in an application
- open-closed principle
- class is closed for changes but the states are opened for extension

- more classes than the classic conditional method
 */