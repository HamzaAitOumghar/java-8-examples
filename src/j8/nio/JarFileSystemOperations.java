package j8.nio;


import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public class JarFileSystemOperations {
    public static void main(String[] args) {

        URI zip = URI.create("jar:file:///C:/Users/dell/Downloads/archive.zip");

        Map<String, String> options = new HashMap<>();
        options.put("create", "true");
        try (FileSystem zipFs = FileSystems.newFileSystem(zip, options)) {

            Path dir = zipFs.getPath("files/");
            zipFs.provider().createDirectory(dir);

            Path path = Paths.get("files/aesop.txt");
            Path target = zipFs.getPath("files/aesop-compresses.txt");

            Files.copy(path,target);

            Path bindir = zipFs.getPath("bin/");
            Path binFile = zipFs.getPath("bin/ints.bin");
            zipFs.provider().createDirectory(bindir);

            OutputStream outputStream = zipFs.provider().newOutputStream(binFile, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            DataOutputStream dos=new DataOutputStream(outputStream);
            dos.write(10);
            dos.write(20);
            dos.write(30);
            dos.close();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
