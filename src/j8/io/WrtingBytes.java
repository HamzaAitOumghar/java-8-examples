package j8.io;

import javax.xml.crypto.Data;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;
import java.util.zip.GZIPOutputStream;

public class WrtingBytes {

    public static void main(String[] args) {

        String pathFile = "files/ints.bin.gz";

        File file = new File(pathFile);

        try (OutputStream os = new FileOutputStream(file);
             GZIPOutputStream gzip=new GZIPOutputStream(os);
             DataOutputStream dos = new DataOutputStream(gzip)) {

            IntStream.range(0,1000).forEach(r->{
                try {
                    dos.writeInt(r);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }


        Path myNewFile = Paths.get(pathFile);
        try {
            System.out.println(Files.size(myNewFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
