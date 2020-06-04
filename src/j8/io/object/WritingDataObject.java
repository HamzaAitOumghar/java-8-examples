package j8.io.object;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WritingDataObject {


    public static void main(String[] args) {
        String fileName = "files/person-proxy.bin";
        File myFile = new File(fileName);

        Person p1 = new Person("Linda", 32);
        Person p2 = new Person("HAMZA", 27);



        try (OutputStream os = new FileOutputStream(myFile);
             ObjectOutputStream oos = new ObjectOutputStream(os)
        ) {
            oos.writeObject(p1);
            oos.writeObject(p2);

        } catch (Exception e) {
                e.printStackTrace();
        }

        try {
            System.out.println("file size : " + Files.size(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
