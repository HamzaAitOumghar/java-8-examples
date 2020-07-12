package j8.collection;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetTest {

    public static void main(String[] args) {
        String simpleStringForTest="this program demonstrates the usage of TreeSet class";
        Set<Character> myTreeSet = new TreeSet<>();
        for ( char c: simpleStringForTest.toCharArray()) {
            myTreeSet.add(c);
        }
        System.out.println("Origin string : "+ simpleStringForTest);
        System.out.println("Sorted String  : "+ myTreeSet);

    }

}
