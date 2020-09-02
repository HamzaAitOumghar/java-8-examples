package j8.stream;

import j8.stream.model.CaloricLevel;
import j8.stream.model.Dish;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class StreamDishTest {

    public static void main(String[] args) {

        List<Dish> menu = new ArrayList<>();
        Dish dish1 = new Dish("coscos", false, 2000, Dish.Type.OTHER);
        Dish dish2 = new Dish("tacos", false, 2030, Dish.Type.MEAT);
        Dish dish3 = new Dish("salade", true, 330, Dish.Type.FISH);
        Dish dish4 = new Dish("fuits", true, 130, Dish.Type.OTHER);
        Dish dish5 = new Dish("jus orange", true, 430, Dish.Type.OTHER);

        menu.add(dish1);
        menu.add(dish2);
        menu.add(dish3);
        menu.add(dish4);
        menu.add(dish5);

        //filtring with predicate

        Predicate<Dish> moreThan300Calories = d -> d.getCalories() > 300;
        List<Dish> dishes = menu.stream().filter(moreThan300Calories).limit(3).collect(Collectors.toList());
        System.out.println(dishes);


        //Skipping elememts with skip(n)

        dishes = menu.stream().filter(moreThan300Calories).skip(3).collect(Collectors.toList());
        System.out.println(dishes);

        //reducing
        // number of dishes :
        int numberOfDishes = menu.stream().map(dish -> 1).reduce(0, Integer::sum);
        System.out.println(numberOfDishes);

        //flattening stream

        List<String> words = new ArrayList<>();
        words.add("Hello");
        words.add("world");
        List<String> result = words.stream().flatMap(s -> Arrays.stream(s.split(""))).collect(Collectors.toList());
        System.out.println(result);

        //finding max and min in a stream of values :
        //by using maxBy/minBy that takes a Comparator as argument
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream().collect(Collectors.maxBy(dishCaloriesComparator));
        Optional<Dish> fewestCalorieDish = menu.stream().collect(Collectors.minBy(dishCaloriesComparator));

        //summarization

        int sum = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        double average = menu.stream().collect(Collectors.averagingDouble(Dish::getCalories));
        IntSummaryStatistics statistics = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));

        //joining strings :
        String finalStrring = menu.stream().map(Dish::getName).collect(Collectors.joining());
        String finalString2 = menu.stream().map(Dish::getName).reduce("", String::concat);
        //join with delimiter
        finalStrring = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
        //general summarization with reducing
        int total1 = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
        int total2 = menu.stream().reduce(0, (a, b) -> a + b.getCalories(), Integer::sum);
        int total3 = menu.stream().mapToInt(Dish::getCalories).sum();
        // best solutions : choose the most specialized one

        //grouping :

        //classify dishes by types

        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println(dishesByType);

        //classify dishes by costum enum : CaloricLevel
        Function<Dish, CaloricLevel> groupeByCaloricLevel = d -> {
            if (d.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (d.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        };
        Map<CaloricLevel, List<Dish>> dishesByCalorieLevel = menu.stream().collect(Collectors.groupingBy(groupeByCaloricLevel));
        System.out.println(dishesByCalorieLevel);

        //Multilevel grouping
        Map<Dish.Type,Map<CaloricLevel,List<Dish>>> multiLevelGroupingByType = menu.stream().collect(Collectors.groupingBy(Dish::getType,Collectors.groupingBy(groupeByCaloricLevel)));
        System.out.println(multiLevelGroupingByType);
        Map<Dish.Type,Long> countByType = menu.stream().collect(Collectors.groupingBy(Dish::getType,Collectors.counting()));
        System.out.println(countByType);
        Map<Dish.Type,Dish> mostCaloricByType = menu.stream().collect(Collectors.groupingBy(Dish::getType,Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),Optional::get)));
        System.out.println(mostCaloricByType);


    }
}
