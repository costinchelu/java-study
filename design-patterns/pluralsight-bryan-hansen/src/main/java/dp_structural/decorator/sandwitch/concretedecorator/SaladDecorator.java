package dp_structural.decorator.sandwitch.concretedecorator;


import dp_structural.decorator.sandwitch.componentbase.Sandwich;
import dp_structural.decorator.sandwitch.decoratorbase.SandwichDecorator;

public class SaladDecorator extends SandwichDecorator {

    public SaladDecorator(Sandwich customSandwich) {
        super(customSandwich);
    }

    public String make() {
        return customSandwich.make() + addSalad();
    }

    private String addSalad() {
        return " + salad";
    }
}
