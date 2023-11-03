package dp_structural.decorator.sandwitch.decoratorbase;


import dp_structural.decorator.sandwitch.componentbase.Sandwich;

public abstract class SandwichDecorator implements Sandwich {

    // all the subdecorators will extend and build upon it
    protected Sandwich customSandwich;

    protected SandwichDecorator(Sandwich customSandwich) {
        this.customSandwich = customSandwich;
        // uses composition to take in an instance of Sandwich (can be a concrete class or another decorator)
    }

    public String make() {
        return customSandwich.make();
    }
}
