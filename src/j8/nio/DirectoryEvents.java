package j8.nio;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.List;

public class DirectoryEvents {
    public static void main(String[] args) throws IOException, InterruptedException {

        Path dir = Paths.get(URI.create("file:///C:/Users/dell/Downloads/Compressed/Vide"));
        FileSystem fileSystem = dir.getFileSystem();
        WatchService watchService = fileSystem.newWatchService();
        WatchKey key = dir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_MODIFY);


        while (key.isValid()) {

            WatchKey take = watchService.take();
            List<WatchEvent<?>> events = take.pollEvents();

            for (WatchEvent<?> event : events) {
                if (event.kind() == StandardWatchEventKinds.OVERFLOW) {
                    continue;
                } else if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                    Path path=(Path) event.context();
                    System.out.println("FILE CREATION - "+ path +" - "+ Files.probeContentType(path));
                }
                else if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                    Path path=(Path) event.context();
                    System.out.println("FILE MODIFICATION - "+ path +" - "+ Files.probeContentType(path));
                }
                else if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                    Path path=(Path) event.context();
                    System.out.println("FILE DELETION - "+ path +" - "+ Files.probeContentType(path));
                }
            }

            take.reset();
        }

        System.out.println("Key is invalid");


    }
}
