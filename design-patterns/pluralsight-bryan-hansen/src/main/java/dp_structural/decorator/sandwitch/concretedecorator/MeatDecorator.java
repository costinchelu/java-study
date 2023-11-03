package dp_structural.decorator.sandwitch.concretedecorator;


import dp_structural.decorator.sandwitch.componentbase.Sandwich;
import dp_structural.decorator.sandwitch.decoratorbase.SandwichDecorator;

public class MeatDecorator extends SandwichDecorator {

    public MeatDecorator(Sandwich customSandwich) {
        super(customSandwich);
    }

    public String make() {
        return customSandwich.make() + addMeat();
    }

    private String addMeat() {
        return " + meat";
    }
}
