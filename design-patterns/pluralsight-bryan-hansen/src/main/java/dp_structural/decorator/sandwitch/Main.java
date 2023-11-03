package dp_structural.decorator.sandwitch;


import dp_structural.decorator.sandwitch.componentbase.Sandwich;
import dp_structural.decorator.sandwitch.concretecomponent.SimpleSandwich;
import dp_structural.decorator.sandwitch.concretedecorator.DressingDecorator;
import dp_structural.decorator.sandwitch.concretedecorator.MeatDecorator;
import dp_structural.decorator.sandwitch.concretedecorator.SaladDecorator;

public class Main {

    public static void main(String[] args) {

        Sandwich sandwich = new DressingDecorator(new MeatDecorator(new SaladDecorator(new SimpleSandwich())));
        System.out.println(sandwich.make());
    }
}
/*
The original object stays the same. We can use the decorator to add functionality to them.
Often is confused with inheritance.
 */