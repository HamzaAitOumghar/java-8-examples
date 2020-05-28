package j8.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class ReadingBytes {


    public static void main(String[] args) {

        String path = "files/ints.bin.gz";
        Path myFile = Paths.get(path);
        try {
            long size = Files.size(myFile);
            System.out.println("myFile size : " + size);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream is = new FileInputStream(new File(path));
             GZIPInputStream gis=new GZIPInputStream(is);
             DataInputStream dis = new DataInputStream(gis)) {
            List<Integer> myIntegerList = new ArrayList<>();

            try {
                while (true) {
                    int i = dis.readInt();
                    myIntegerList.add(i);
                }
            } catch (EOFException ex) {
            }

            System.out.println("INTS : " + myIntegerList.size());

        } catch (IOException e) {

        }


    }
}
