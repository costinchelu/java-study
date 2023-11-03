package dp_structural.flyweight.wrapperexample;

public class Main {

    public static void main(String[] args) {

        Integer firstInt = Integer.valueOf(5);
        Integer secondInt = Integer.valueOf(10);
        Integer thirdInt = Integer.valueOf(5);

        System.out.println("firstInt hash = " + System.identityHashCode(firstInt));
        System.out.println("secondInt hash = " + System.identityHashCode(secondInt));
        System.out.println("thirdInt hash = " + System.identityHashCode(thirdInt));

        // notice that 1st and 3rd int has the same hashcode because Java used Flyweight
    }
}
/*
- more efficient use of memory
- optimisation pattern
- large number of similar objects
- uses immutable objects
-- pattern of patterns
-- utilizes a Factory

- used a lot bu the core API
java.lang.String
all wrapper classes


- complex
- premature optimisation
- used for inventories (not a graphical pattern)

 */
