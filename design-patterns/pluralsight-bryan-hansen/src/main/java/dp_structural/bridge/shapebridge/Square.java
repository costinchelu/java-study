package dp_structural.bridge.shapebridge;

public class Square extends Shape {

    public Square(Color color) {
        super(color);
    }

    @Override
    public void applyColor() {
        color.applyColor();
        System.out.println("square.");
    }
}
