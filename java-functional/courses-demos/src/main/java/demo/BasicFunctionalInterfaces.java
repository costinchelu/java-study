package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

// only one abstract method makes an interface functional
@FunctionalInterface
interface Climb {

    void reach();

    default void fall() {

    }

    static int getBackUp() {
        return 100;
    }

    private static boolean checkHeight() {
        return true;
    }

    private boolean privateMethod() {
        return true;
    }
}


// Since toString(), equals() & hashCode() are public methods implemented in Object,
// they don't count toward the single abstract method test.
@FunctionalInterface
interface Dive {

    void dive();

    String toString();

    boolean equals(Object o);

    int hashCode();
}


record Animal(String species, boolean canHop, boolean canSwim) { }


interface CheckTrait {
    boolean testing(Animal a);
}


class CheckIfHopper implements CheckTrait {

    @Override
    public boolean testing(Animal a) {
        return a.canHop();
    }
}


class CheckIfSwimmer implements CheckTrait {

    @Override
    public boolean testing(Animal a) {
        return a.canSwim();
    }
}


public class BasicFunctionalInterfaces {

    public static void main(String[] args) {
        var animals = new ArrayList<Animal>();
        animals.add(new Animal("fish", false, true));
        animals.add(new Animal("kangaroo", true, false));
        animals.add(new Animal("rabbit", true, false));
        animals.add(new Animal("turtle", false, true));

        print(animals, new CheckIfSwimmer());
        print(animals, new CheckIfHopper());

        print(animals, a -> a.canSwim());
        print(animals, Animal::canHop);

        print(animals, a -> !a.canSwim());

//        var predicate = (Animal a) -> a.canHop();     // not working (cannot infer type)
        Predicate<Animal> predicate = (Animal a) -> a.canHop();
        System.out.println(predicate);

    }

    private static void print(List<Animal> animals, CheckTrait checker) {
        for (Animal animal : animals) {
            if (checker.testing(animal))
                System.out.print(animal + " ");
        }
        System.out.println();
    }
}
