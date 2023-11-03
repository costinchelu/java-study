package dp_structural.decorator.sandwitch.concretecomponent;


import dp_structural.decorator.sandwitch.componentbase.Sandwich;

public class SimpleSandwich implements Sandwich {

    @Override
    public String make() {
        return "Bread";
    }
}
