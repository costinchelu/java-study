package dp_structural.bridge.shapebridge;

public class Circle extends Shape {

    public Circle(Color color) {
        super(color);
    }

    @Override
    public void applyColor() {
        color.applyColor();
        System.out.println("circle.");
    }
}
