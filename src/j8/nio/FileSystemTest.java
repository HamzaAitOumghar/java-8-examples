package j8.nio;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.spi.FileSystemProvider;
import java.util.Iterator;
import java.util.List;

public class FileSystemTest {

    public static void main(String[] args) throws IOException {
        List<FileSystemProvider> providers = FileSystemProvider.installedProviders();
        FileSystemProvider windowsFSP = providers.get(0);

        FileSystem defaultFS = FileSystems.getDefault();
        URI root = URI.create("file:///");
        FileSystem fsByUri = FileSystems.getFileSystem(root);

        /*Path dir = Paths.get("");
        windowsFSP.createDirectory(dir);*/

        Iterable<Path> rootDirectories = defaultFS.getRootDirectories();


        Iterable<FileStore> fileStores = defaultFS.getFileStores();
        fileStores.forEach(r->{
            System.out.println(r.name()+" : "+ r.type());
        });

    }
}
