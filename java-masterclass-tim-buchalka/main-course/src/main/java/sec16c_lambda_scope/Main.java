package sec16c_lambda_scope;


public class Main {

    public static void main(String[] args) {

        AnotherClass anotherClass = new AnotherClass();
        String s = anotherClass.doSomething();

        System.out.println(s);

    }

    // implementing a method using the interface in example 3
    public final static String doStringStuff(UpperConcat uc, String s1, String s2) {
        return uc.upperAndConcat(s1, s2);
    }
}


interface UpperConcat {
    public String upperAndConcat(String s1, String s2);
}


class AnotherClass {

    public String doSomething() {
        int i = 0;


        UpperConcat uc = (s1, s2) -> {
            System.out.println("The lambda expression's class is " + getClass().getSimpleName());
            System.out.println("i in the lambda expression must be final (or effectively final). So i = " + i);
            String result = s1.toUpperCase() + s2.toUpperCase();
            return result;
        };

        System.out.println("We cannot access s1 and s2 from outside the lambda expression!");
        System.out.println("The AnotherClass class's name is " + getClass().getSimpleName());
        return Main.doStringStuff(uc, "String1", "String2");
    }

    public void printValue() {
        int number = 25;

        Runnable r = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println("The value of number is " + number);
        };

        new Thread(r).start();
    }

}

/*
*
* variable inside the lambdas are not available outside the block
*
 */
