package demo.stream_fork;

import model.Dish;
import static model.Dish.menu;

import java.util.*;
import java.util.stream.*;

/**
 For performance reasons you shouldn’t take for granted that this approach is more
 efficient than traversing the stream several times. The overhead caused by the use of
 the blocking queues can easily outweigh the advantages of executing the different
 operations in parallel when the stream is made of data that’s all in memory.
 Conversely, accessing the stream only once could be a winning choice when this
 involves some expensive I/O operations, such as when the source of the stream is a
 huge file; so (as usual) the only meaningful rule when optimizing the performance of
 your application is to “Just measure it!”
 <p>
 This example demonstrates how it can be possible to execute multiple operations
 on the same stream in one shot. More importantly, we believe this proves that even
 when a specific feature isn’t provided by the native Java API, the flexibility of lambda
 expressions and a bit of creativity in reusing and combining what’s already available
 can let you implement the missing feature on your own.
 */
public class StreamForkerExample {

    public static void main(String[] args) throws Exception {
        processMenu();
    }

    private static void processMenu() {
        Stream<Dish> menuStream = menu.stream();

        StreamForker.Results results = new StreamForker<>(menuStream)
                .fork("shortMenu", s -> s.map(Dish::getName).collect(Collectors.joining(", ")))

                .fork("totalCalories", s -> s.mapToInt(Dish::getCalories).sum())

                .fork("mostCaloricDish", s -> s.collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)).orElseThrow(() -> new RuntimeException("No Dish")))

                .fork("dishesByType", s -> s.collect(Collectors.groupingBy(Dish::getType)))

                .getResults();

        String shortMenu = results.get("shortMenu");
        int totalCalories = results.get("totalCalories");
        Dish mostCaloricDish = results.get("mostCaloricDish");
        Map<Dish.Type, List<Dish>> dishesByType = results.get("dishesByType");

        System.out.println("Short menu: " + shortMenu);
        System.out.println("Total calories: " + totalCalories);
        System.out.println("Most caloric dish: " + mostCaloricDish);
        System.out.println("Dishes by type: " + dishesByType);
    }
}
