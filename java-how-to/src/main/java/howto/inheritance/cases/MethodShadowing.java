package howto.inheritance.cases;

/*
if print() method has a public access modifier, then "Child" is printed
if print method has a private modifier, then "Parent" is printed

because if the print() has a public access, then it can be overridden in the Child class,
else what is in a Child class is another method (method shadowing)
 */
public class MethodShadowing {

    public static void main(String[] args) {
        Child child = new Child();
        child.display();
    }
}


class Parent {

    private void print() {
        System.out.println("Parent");
    }

    public void display() {
        print();
    }
}


class Child extends Parent {

//    @Override
    public void print() {
        System.out.println("Child");
    }
}