package dp_decorator;

import java.awt.*;
import java.util.function.Function;
import java.util.stream.Stream;

public class DecoratorExample {

    public static void main(String[] args) {
        Function<Integer, Integer> increment = e -> e + 1;
        Function<Integer, Integer> doubleInt = e -> e * 2;

        // function chaining
        showResults(5, increment);
        showResults(6, doubleInt);
        showResults(5, increment.andThen(doubleInt).andThen(increment));

        printSnap(new Camera());
        printSnap(new Camera(Color::brighter));
        printSnap(new Camera(Color::brighter, Color::darker));
    }

    private static void showResults(int value, Function<Integer, Integer> function) {
        System.out.println(function.apply(value));
    }

    private  static void printSnap(Camera camera) {
        System.out.println(camera.snap(new Color(125, 125, 125)));
    }
}

class Camera {

    private Function<Color, Color> filter;

    @SafeVarargs
    public Camera(Function<Color, Color>... filters) {
        setFilters(filters);
    }

    @SafeVarargs
    private void setFilters(Function<Color, Color>... filters) {
        filter = Stream.of(filters)
                .reduce(Function.identity(),
                        Function::andThen);
    }

    public Color snap(Color input) {
        return filter.apply(input);
    }
}
