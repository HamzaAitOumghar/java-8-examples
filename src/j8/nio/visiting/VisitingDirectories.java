package j8.nio.visiting;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Spliterator;
import java.util.stream.StreamSupport;

public class VisitingDirectories {

    static class CustomFileVisitor implements FileVisitor<Path>{

        private long emptyDirs=0L;
        private Map<String,Long> types=new HashMap<>();


        public long getEmptyDirs() {
            return emptyDirs;
        }

        public Map<String, Long> getTypes() {
            return types;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir);
            Spliterator<Path> spliterator = directoryStream.spliterator();
            boolean dirIsNotEmpty = StreamSupport.stream(spliterator, false).findFirst().isPresent();

            if(dirIsNotEmpty){
                emptyDirs++;
               return FileVisitResult.CONTINUE;
            }else {
                return FileVisitResult.SKIP_SUBTREE;
            }
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            String fileType = Files.probeContentType(file);
            types.merge(fileType,1L,(l1,l2)->l1+l2);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
        }
    }

    public static void main(String[] args) throws IOException {

        CustomFileVisitor fileVisitor = new CustomFileVisitor();
        Path dir = Paths.get(URI.create("file:///C:/Users/dell/Downloads/Compressed"));
        Files.walkFileTree(dir,fileVisitor);

        System.out.println("Empty Dirs : "+fileVisitor.getEmptyDirs());

        System.out.println(fileVisitor.types);

    }
}
