package sec11c_accessModifiers.staticinitblocks;

public class Main {
    public static void main(String[] args) {


        System.out.println("Example1 method called");


        SIBTest test = new SIBTest();

        // "static" blocks will be called first, then the constructor



        test.someMethod();
        System.out.println("Owner is " + SIBTest.owner);
    }
}
