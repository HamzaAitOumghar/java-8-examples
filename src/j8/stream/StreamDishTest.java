package j8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamDishTest {

    public static void main(String[] args) {

        List<Dish> menu = new ArrayList<>();
        Dish dish1=new Dish("coscos",false,2000, Dish.Type.OTHER);
        Dish dish2=new Dish("tacos",false,2030,Dish.Type.MEAT);
        Dish dish3=new Dish("salade",true,330, Dish.Type.FISH);
        Dish dish4=new Dish("fuits",true,130, Dish.Type.OTHER);
        Dish dish5=new Dish("jus orange",true,430, Dish.Type.OTHER);

        menu.add(dish1);
        menu.add(dish2);
        menu.add(dish3);
        menu.add(dish4);
        menu.add(dish5);

        //filtring with predicate

        Predicate<Dish> moreThan300Calories = d->d.getCalories()>300;
        List<Dish> dishes = menu.stream().filter(moreThan300Calories).limit(3).collect(Collectors.toList());
        System.out.println(dishes);


        //Skipping elememts with skip(n)

        dishes = menu.stream().filter(moreThan300Calories).skip(3).collect(Collectors.toList());
        System.out.println(dishes);

        //reducing
        // number of dishes :
        int numberOfDishes = menu.stream().map(dish ->1 ).reduce(0,Integer::sum);
        System.out.println(numberOfDishes);

        //flattening stream

        List<String> words = new ArrayList<>();
        words.add("Hello");words.add("world");
        List<String> result = words.stream().flatMap(s-> Arrays.stream(s.split(""))).collect(Collectors.toList());
        System.out.println(result);


    }
}
