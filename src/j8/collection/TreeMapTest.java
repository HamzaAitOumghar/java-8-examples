package j8.collection;

import java.util.NavigableMap;
import java.util.TreeMap;

public class TreeMapTest {

    public static void main(String[] args) {
        NavigableMap<Integer,String> examScores = new TreeMap<>();
        examScores.put(90,"HAMZA");
        examScores.put(40,"AYOUB");
        examScores.put(30,"IKRAM");
        examScores.put(20,"OMAR");

        System.out.println("data in the map : "+ examScores);
        System.out.println("descending order  : "+ examScores.descendingMap());
        System.out.println("those who passed the exam   : "+ examScores.tailMap(40));
        System.out.println("Lowest mark : " + examScores.firstEntry());
        System.out.println("Max mark : " + examScores.lastEntry());




    }

}

