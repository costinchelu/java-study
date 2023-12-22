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

/**
 * Partitioning is a special case of grouping. With partitioning, there are only two possible
 * groups: true and false. Partitioning is like splitting a list into two parts.
 */
public class GroupingWPartitioning {

    public static void main(String[] args) {

        Map<Boolean, List<Dish>> partitionedMenu =
                menu.stream()
                        .collect(partitioningBy(Dish::isVegetarian));
        List<Dish> vegetarianDishes = partitionedMenu.get(true);
        /*
        only the "true" key is selected from the partitionedMenu map
        [Dish(name=french fries, vegetarian=true, calories=530, type=OTHER),
        Dish(name=rice, vegetarian=true, calories=350, type=OTHER),
        Dish(name=season fruit, vegetarian=true, calories=120, type=OTHER),
        Dish(name=pizza, vegetarian=true, calories=550, type=OTHER)]
         */

        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType =
                menu.stream()
                        .collect(
                                partitioningBy(Dish::isVegetarian,
                                groupingBy(Dish::getType)));
        /*
        {false=
            {FISH=[
                Dish(name=prawns, vegetarian=false, calories=400, type=FISH),
                Dish(name=salmon, vegetarian=false, calories=450, type=FISH)],
             MEAT=[
                Dish(name=pork, vegetarian=false, calories=800, type=MEAT),
                Dish(name=beef, vegetarian=false, calories=700, type=MEAT),
                Dish(name=chicken, vegetarian=false, calories=400, type=MEAT)]},
          true=
            {OTHER=[
                Dish(name=french fries, vegetarian=true, calories=530, type=OTHER),
                Dish(name=rice, vegetarian=true, calories=350, type=OTHER),
                Dish(name=season fruit, vegetarian=true, calories=120, type=OTHER),
                Dish(name=pizza, vegetarian=true, calories=550, type=OTHER)]}}
         */

        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian =
                menu.stream()
                        .collect(
                                partitioningBy(Dish::isVegetarian,
                                        collectingAndThen(
                                                maxBy(comparingInt(Dish::getCalories)),
                                                Optional::get)));
        /*
        {false=Dish(name=pork, vegetarian=false, calories=800, type=MEAT),
        true=Dish(name=pizza, vegetarian=true, calories=550, type=OTHER)}
         */

        Map<Boolean, Map<Boolean, List<Dish>>> vegetarianCaloricDishes =
                menu.stream()
                        .collect(
                                partitioningBy(Dish::isVegetarian,
                                        partitioningBy(d -> d.getCalories() > 500)));
        /*
        {false=
            {false=[
                Dish(name=chicken, vegetarian=false, calories=400, type=MEAT),
                Dish(name=prawns, vegetarian=false, calories=400, type=FISH),
                Dish(name=salmon, vegetarian=false, calories=450, type=FISH)],
             true=[
                Dish(name=pork, vegetarian=false, calories=800, type=MEAT),
                Dish(name=beef, vegetarian=false, calories=700, type=MEAT)]},
          true=
            {false=[
                Dish(name=rice, vegetarian=true, calories=350, type=OTHER),
                Dish(name=season fruit, vegetarian=true, calories=120, type=OTHER)],
             true=[
                Dish(name=french fries, vegetarian=true, calories=530, type=OTHER),
                Dish(name=pizza, vegetarian=true, calories=550, type=OTHER)]}}
         */

        System.out.println(vegetarianDishes);
        System.out.println(vegetarianDishesByType);
        System.out.println(mostCaloricPartitionedByVegetarian);
        System.out.println(vegetarianCaloricDishes);
    }
}
