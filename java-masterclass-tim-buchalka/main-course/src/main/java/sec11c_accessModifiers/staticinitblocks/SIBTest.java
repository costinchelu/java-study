package sec11c_accessModifiers.staticinitblocks;


//static initialization blocks
//this block is only executed once when the class is loaded into the project
//static final variables must be initialized by the time all static initialization blocks terminate


public class SIBTest {
    public static final String owner;

    static {
        owner = "tim";
        System.out.println("SIBTEST static initialization test called");
    }

    public SIBTest() {
        System.out.println("SIB constructor called");
    }

    static {
        System.out.println("2nd initialization block called");
    }

    public void someMethod() {
        System.out.println("SomeMethod called");
    }
}
