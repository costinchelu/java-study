package howto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class Circle {

    private Point center;

    private int radius;
}

@AllArgsConstructor
@Getter
class Point {

    private int x, y;
}


public class DeepVsShallowCopy {

    public Circle deepCopyCircle(Circle original) {
        Point copiedPoint = new Point(original.getCenter().getX(), original.getCenter().getY());
        return new Circle(copiedPoint, original.getRadius());
    }

    public Circle shallowCopyCircle(Circle original) {
        return new Circle(original.getCenter(), original.getRadius());
    }
}
