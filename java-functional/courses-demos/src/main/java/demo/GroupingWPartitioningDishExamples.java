package demo;

import model.Dish;

import java.util.List;
import java.util.Map;

import static model.Dish.menu;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.partitioningBy;
import java.util.Optional;

public class GroupingWPartitioningDishExamples {

    public static void main(String[] args) {

        Map<Boolean, List<Dish>> partitionedMenu =
                menu.stream()
                        .collect(partitioningBy(Dish::isVegetarian));
        List<Dish> vegetarianDishes = partitionedMenu.get(true);

        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType =
                menu.stream()
                        .collect(partitioningBy(Dish::isVegetarian,
                                groupingBy(Dish::getType)));

        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian =
                menu.stream()
                        .collect(
                                partitioningBy(Dish::isVegetarian,
                                        collectingAndThen(
                                                maxBy(comparingInt(Dish::getCalories)),
                                                Optional::get)));

        Map<Boolean, Map<Boolean, List<Dish>>> vegetarianCaloricDishes =
                menu.stream()
                        .collect(
                                partitioningBy(Dish::isVegetarian,
                                        partitioningBy(d -> d.getCalories() > 500)));

        System.out.println(vegetarianDishes);
        System.out.println(vegetarianDishesByType);
        System.out.println(mostCaloricPartitionedByVegetarian);
        System.out.println(vegetarianCaloricDishes);
    }
}
