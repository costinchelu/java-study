package dp_creational.builder.no_interface;

public class Main {
    public static void main(String[] args) {

        // v. 0.1 - using normal setters and getters
        UsingSettersAndGetters lunchOrder1 = new UsingSettersAndGetters();
        lunchOrder1.setBread("White");
        lunchOrder1.setMeat("Chicken");

        // not immutable
        lunchOrder1.setMeat("Pork");

        System.out.println(lunchOrder1.toString() + "\n");

        // v. 0.2 - using telescopingConstructors
        UsingTelescopingConstructors lunchOrder2 =
                new UsingTelescopingConstructors("Brown", "Salt", "Mayo", "Chicken");

        // we can get it immutable by not providing setters but we still have to create constructors for
        // every combination of parameters we need
        // - NO -    lunchOrder2.setMeat("Pork");

        System.out.println(lunchOrder2.toString() + "\n");

        // v. 0.3 - using the builder
        UsingBuilder.SandwichBuilder sandwichBuilder = new UsingBuilder.SandwichBuilder();

        // after constructing the builder we can create the object exactly as we want by chaining specific
        // build methods for every attribute
        sandwichBuilder.buildBread("Rye").buildDressing("Pesto").BuildCondiments("Pepper").buildMeat("no meat");

        // finally build required object by using the build() method
        UsingBuilder lunchOrder3 = sandwichBuilder.makeASandwich();

        sandwichBuilder.buildBread("Toast").buildDressing("Butter").BuildCondiments("-").buildMeat("Beef");
        UsingBuilder lunchOrder4 = sandwichBuilder.makeASandwich();

        System.out.println(lunchOrder3.toString() + "\n");
        System.out.println(lunchOrder4.toString() + "\n");
    }
}
/*
Separate the construction of a complex object from its representing so that the same construction
process can create different representations.

- handles complex constructors
- no interface required
- large number of parameters
- immutability

StringBuilder
DocumentBuilder
Locale.Builder
 */
