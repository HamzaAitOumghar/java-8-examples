package j8.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ArrayListTest {
    public static void main(String[] args) {

        ArrayList<String> languageList = new ArrayList<>();
        languageList.add("C");
        languageList.add("C++");
        languageList.add("Java");

        for ( String s: languageList) {
            System.out.println(s);
        }

        for(Iterator<String> it = languageList.iterator();it.hasNext();){
            System.out.println(it.next());
        }

        //removing items using iterator

        Iterator<String> it = languageList.iterator();
        while (it.hasNext()){
            it.next();
            it.remove();
        }
        System.out.println(languageList);

        //using  asList()
        //Arrays.asList() return a fixed-size list , you cannot add or remove elements
        String[] arrayOfString={"HAMZA","ECHO","TEST","HELLO"};
        List<String> newList = Arrays.asList(arrayOfString);
        System.out.println(newList);
        // newList.add("test"); impossible : UnsupportedOperationException


    }
}
