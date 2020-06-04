package j8.io.object;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class ReadingDataObjects {


    public static void main(String[] args) {

        String fileName = "files/person-externalizable.bin";
        File file = new File(fileName);

        try (InputStream is = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(is);
        ) {

            Person p1 = (Person) ois.readObject();
            Person p2 = (Person) ois.readObject();
            System.out.println(p1);
            System.out.println(p2);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
