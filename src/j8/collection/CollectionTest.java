package j8.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CollectionTest {


    public static void main(String[] args) {

        List list = new ArrayList<>();
        list.add("First");
        list.add("Second");
        List<String> strList = list;

        for (Iterator<String> itemItr = strList.iterator();itemItr.hasNext();)
            System.out.println("Item : " + itemItr.next() );

        List<String> strList2=new LinkedList<>();
        strList2.add("First");
        strList2.add("Second");
        List list2=strList2;

        for (Iterator<String> itemItr = list2.iterator();itemItr.hasNext();)
            System.out.println("Item : " + itemItr.next() );



    }
}
