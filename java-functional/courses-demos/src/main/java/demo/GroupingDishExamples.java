package demo;

import model.CaloricLevel;
import model.Dish;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static model.Dish.menu;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toSet;

public class GroupingDishExamples {

    public static void main(String[] args) {


        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel =
                menu.stream()
                        .collect(
                            groupingBy(dish -> {
                                if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                else return CaloricLevel.FAT;
                            }));

        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
                menu.stream()
                        .collect(
                            groupingBy(Dish::getType,
                                    groupingBy(dish -> {
                                        if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                        else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                        else return CaloricLevel.FAT;
                                    })));

        Map<Dish.Type, Long> typesCount =
                menu.stream()
                        .collect(
                                groupingBy(Dish::getType, counting()));

        Map<Dish.Type, Optional<Dish>> mostCaloricByType =
                menu.stream()
                        .collect(
                                groupingBy(
                                        Dish::getType,
                                        maxBy(comparingInt(Dish::getCalories))));

        Map<Dish.Type, Dish> mostCaloricByTypeFromOptional =
                menu.stream()
                        .collect(
                                groupingBy(
                                        Dish::getType,
                                        collectingAndThen(
                                            maxBy(comparingInt(Dish::getCalories)),
                                            Optional::get)));

        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
                menu.stream()
                        .collect(
                                groupingBy(
                                        Dish::getType,
                                        mapping(
                                            dish -> {
                                                if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                                else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                                else return CaloricLevel.FAT;
                                                },
                                            toSet() )
                                ));

        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType2 =
                menu.stream()
                        .collect(
                                groupingBy(
                                        Dish::getType,
                                        mapping(
                                            dish -> {
                                                if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                                else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                                else return CaloricLevel.FAT;
                                                },
                                            toCollection(HashSet::new) )
                                ));

        System.out.println(dishesByCaloricLevel);
        System.out.println(dishesByTypeCaloricLevel);
        System.out.println(typesCount);
        System.out.println(mostCaloricByType);
        System.out.println(mostCaloricByTypeFromOptional);
        System.out.println(caloricLevelsByType);
    }
}
