package dp_structural.bridge.shapebridge;

public class Main {

    public static void main(String[] args) {

        Color blue = new Blue();
        Color red = new Red();
        Color green = new Green();

        Shape square = new Square(blue);
        Shape circle = new Circle(red);
        Shape circle2 = new Circle(green);
        Shape square2 = new Square(green);

        square.applyColor();
        circle.applyColor();
        circle2.applyColor();
        square2.applyColor();
    }
}
/*
Comparing to Adapter Pattern (which links legacy code with client code), Bridge will link existing code
to decouple abstraction from implementation
Changes in abstraction won't affect client


Examples:
-JDBC
 */