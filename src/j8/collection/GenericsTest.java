package j8.collection;

import java.util.ArrayList;
import java.util.List;

public class GenericsTest {

    public static void show(List<?> myList){
        for (Object o : myList)
            System.out.println(o);
    }

    public static void main(String[] args) {
        List<String> myList = new ArrayList<>();
        myList.add("Test");
        myList.add("Test1");
        myList.add("Test2");
        System.out.println(myList);
        //Generics function
        Utilities.fill(myList,"Hamza");
        System.out.println(myList);

        //generics and subtyping
        //subtyping works for classtypes and it does not work for generic type parameters
        // ex :  List<Number> myList = new ArrayList<Integer>();

        //wildcard param
        //you can use the wildcard param to indicate that it can march for any type
        //limitation : you can not call methods that modify the object
        List<?> wildCardList = new ArrayList<Integer>();
        show(myList);
        //other limitation of generic types :
        // T test = new T() ; compiler error
        // T test = new T[20] ;compiler error
        // static T test ;  compiler error
        // we can not create generic exception class : class FunctionalException<T> extends Throwable {} // compiler error

    }
}
