package dp_structural.decorator.sandwitch.concretedecorator;


import dp_structural.decorator.sandwitch.componentbase.Sandwich;
import dp_structural.decorator.sandwitch.decoratorbase.SandwichDecorator;

public class DressingDecorator extends SandwichDecorator {

    public DressingDecorator(Sandwich customSandwich) {
        super(customSandwich);
    }

    public String make() {
        return customSandwich.make() + addDressing();
    }

    private String addDressing() {
        return " + dressing";
    }
}
