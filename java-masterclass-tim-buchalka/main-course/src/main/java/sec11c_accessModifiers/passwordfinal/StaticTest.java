package sec11c_accessModifiers.passwordfinal;

public class StaticTest {

    private static int numInstances = 0;
    private String name;

    public StaticTest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static int getNumInstances() {
        return numInstances;
    }

    //being static numInstances field is common to every instance created.
    // SO, every time we modify the static variable, we will save its value


    //also, a static method can be called directly from the class name, because it is common
    //to every instance we will create from that class. For example:
    /*

    StaticTest someInstance = new StaticTest("an instance of StaticTest");
    System.out.println(StaticTest.getNumInstances());   // a static method
    System.out.println(someInstance.getName());         // a nonstatic method

     */


    // final fields can be set only once. Either when declaring field or in the constructor
    // similar with const (in C) with the exception that it's not necessary
    // to set the value of the variable(field) when declaring it
    //
    // classes can also be final which means that they cannot be subclassed
    // for example Math class is public final
    // it also have a private constructor private Math() which means it cannot be instantiate
    // or cannot extend from it

}
