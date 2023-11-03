package dp_behavioral.state.withstatepattern;


import dp_behavioral.state.withstatepattern.context.Fan;

public class Main {

    public static void main(String[] args) {

        Fan fan = new Fan();
        System.out.println(fan);

        for(int i = 0; i < 4; i++) {
            fan.pullChain();
            System.out.println(fan);
        }

    }
}
