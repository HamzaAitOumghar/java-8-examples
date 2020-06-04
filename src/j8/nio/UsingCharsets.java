package j8.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class UsingCharsets {

    public static void main(String[] args) throws IOException {

        Charset charset  =  null ;
        //charset=StandardCharsets.ISO_8859_1;
        charset=StandardCharsets.UTF_8;

        String hello = "Hello world from José";
        System.out.println("Length : "+hello.length());

        CharBuffer charBuffer = CharBuffer.allocate(1024*1024);

        charBuffer.put(hello);
        charBuffer.flip();

        ByteBuffer buffer = charset.encode(charBuffer);
        Path path = null;

        //path = Paths.get("filesnio/hello.latin1.txt");
        path = Paths.get("filesnio/hello.utf.txt");
        try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.CREATE,StandardOpenOption.WRITE)){
            fileChannel.write(buffer);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("File size : " + Files.size(path));

        path = Paths.get("filesnio/hello.utf.txt");

        buffer.clear();

        try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ)){
            fileChannel.read(buffer);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        buffer.flip();
        charBuffer=charset.decode(buffer);

        String fileContent = new String(charBuffer.array());
        System.out.println(fileContent);


    }
}
