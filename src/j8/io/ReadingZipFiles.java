package j8.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ReadingZipFiles {

    public static void main(String[] args) {

        String fileName = "files/archive.zip";

        try (InputStream is = new FileInputStream(new File(fileName));
             ZipInputStream zis = new ZipInputStream(is);
             DataInputStream dis = new DataInputStream(zis)) {

            ZipEntry entry = zis.getNextEntry();

            while (entry != null) {

                if (entry.isDirectory()) {
                    System.out.println("Reading a directory " + entry);
                } else {
                    if (entry.getName().endsWith(".bin")) {
                        System.out.println("Reading a bin file " + entry);
                        List<Integer> myIntegerList = new ArrayList<>();
                        try {
                            while (true) {
                                int i = dis.readInt();
                                myIntegerList.add(i);
                            }
                        } catch (EOFException ex) {
                        }
                        System.out.println("list size : "+ myIntegerList.size());
                    } else if (entry.getName().endsWith(".txt")) {
                        System.out.println("Reading a txt file " + entry);
                        System.out.println("Content : "+dis.readUTF());
                    }
                }

                entry = zis.getNextEntry();
            }


        } catch (Exception e) {

        }

    }

}
