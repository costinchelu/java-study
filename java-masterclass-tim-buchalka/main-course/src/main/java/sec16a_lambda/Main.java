package sec16a_lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
/*
1st method
         new Thread(new CodeToRun()).start();

2nd method
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Printing from the Runnable");
            }
        }).start();
*/
// lambda method
        new Thread(() -> {
            System.out.println("Printing from the Runnable");
            System.out.format("This is line %d\n", 2);
            System.out.println("And another line...");
        }).start();

/*
the compiler matches the no parameter lambda with the run method (we only have the run method)
lambda expressions can only be used with interfaces that contain only one method that
has to be implemented -> so these interfaces are also referred to as functional interfaces
 */

        // -------------------------------------------------------------------------------------------

        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Buchalka", 21);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);

        /*
classic way to sort the List:

        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee employee1, Employee employee2) {
                return employee1.getName().compareTo(employee2.getName());
            }
        });


lambda way to sort the list (in Comparator documentation it states that this is a functional interface)

        //Collections.sort(employees, (Employee e1, Employee e2) -> e1.getName().compareTo(e2.getName()));


the compiler can actually infer that we compare two Employee objects */
        Collections.sort(employees, (e1, e2) -> e1.getName().compareTo(e2.getName()));

        for(Employee employee : employees) {
            System.out.println(employee.getName());
        }

        // -------------------------------------------------------------------------------------------

        /*
        * an anonymous class that overrides the only method in interface let's use a lambda expression instead now we
        * still have to define the interface we can't get away from doing that when using lambdas
        * the rest of the code by passing a lambda instead of an anonymous class
        *
        * */

/* classic way:

        String sillyString = doStringStuff(new UpperConcat() {
            @Override
            public String upperAndConcat(String s1, String s2) {
                return s1.toUpperCase() + s2.toUpperCase();
            }
        }, employees.get(0).getName(), employees.get(1).getName());
         */


        // lambda implementation:
        UpperConcat uc = (s1, s2) -> s1.toUpperCase() + s2.toUpperCase();
        String sillyString = doStringStuff(uc, employees.get(0).getName(), employees.get(1).getName());
        // we can't use return statement for a one line lambda expression

        System.out.println(sillyString);


        // -------------------------------------------------------------------------------------------

        AnotherClass anotherClass = new AnotherClass();
        String s = anotherClass.doSomething();

        System.out.println(s);

    }

    // implementing a method using the interface in example 3
    public final static String doStringStuff(UpperConcat uc, String s1, String s2) {
        return uc.upperAndConcat(s1, s2);
    }
}

// class made for the first method
class CodeToRun implements Runnable {
    @Override
    public void run() {
        System.out.println("Printing from the Runnable");
    }
}

// 2nd lambda example
class Employee {

    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }
}

// 3rd lambda example
interface UpperConcat {
    public String upperAndConcat(String s1, String s2);
}

// 4th lambda example
class AnotherClass {

    /*
// (long way) classic way to write the method with anonymous class:

    public String doSomething() {

        System.out.println("The AnotherClass class's name is " + getClass().getSimpleName());

        // in class's main method we are going to construct an instance of another class and call the do something
        return Main.doStringStuff(new UpperConcat() {
            @Override
            public String upperAndConcat(String s1, String s2) {
                // this will not print class's name because an anonymous class doesn't have a name
                System.out.println("The anonymous class's name is " + getClass().getSimpleName());
                return s1.toUpperCase() + s2.toUpperCase();
            }
       }, "String1", "String2");
    }


// using a nested block:

    public String doSomething() {

        final int i = 0;

        {
            UpperConcat uc = new UpperConcat() {
                @Override
                public String upperAndConcat(String s1, String s2) {
                    System.out.println("i (within anonymous class) = " + i);
                    return s1.toUpperCase() + s2.toUpperCase();
                }
            };

            System.out.println("The AnotherClass class's name is " + getClass().getSimpleName());
            System.out.println("i = " + i);

            return Main.doStringStuff(uc, "String1", "String2");
        }

    }
    */


    // lambda mode to write doSomething method:
    public String doSomething() {
        UpperConcat uc = (s1, s2) -> {
            System.out.println("The lambda expression's class is " + getClass().getSimpleName());
            String result = s1.toUpperCase() + s2.toUpperCase();
            return result;
        };

        System.out.println("The AnotherClass class's name is " + getClass().getSimpleName());
        return Main.doStringStuff(uc, "String1", "String2");
    }

}


/*
* Easier way to work with interfaces that have only one method
* Typical use case are anonymous classes
 */

