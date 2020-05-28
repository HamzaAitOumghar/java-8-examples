package j8.io;

import java.io.*;
import java.util.stream.IntStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFiles {
    public static void main(String[] args) {

        String fileName = "files/archive.zip";

        try (OutputStream os = new FileOutputStream(new File(fileName));
             ZipOutputStream zos = new ZipOutputStream(os);
             DataOutputStream dos = new DataOutputStream(zos);) {

            ZipEntry dirEntry = new ZipEntry("bin/");
            zos.putNextEntry(dirEntry);

            ZipEntry fileEntry = new ZipEntry("bin/ints.bin");
            zos.putNextEntry(fileEntry);

            IntStream.range(0,1000).forEach(r->{
                try {
                    dos.writeInt(r);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            ZipEntry otherEntry = new ZipEntry("text/");
            zos.putNextEntry(otherEntry);

            ZipEntry otherfileEntry = new ZipEntry("text/file.txt");
            zos.putNextEntry(otherfileEntry);

            dos.writeUTF("THIS IS A TEXT FILE");





        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
