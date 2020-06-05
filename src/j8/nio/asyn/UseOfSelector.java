package j8.nio.asyn;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Set;

public class UseOfSelector {
    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(12345));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            System.out.println("We are waiting for events...");

            int select = selector.select();
            System.out.printf("Got %d events : ", select);

            Set<SelectionKey> keys = selector.selectedKeys();

            for (SelectionKey k : keys) {

                if ((k.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                    System.out.println("Acception the connection ");
                    ServerSocketChannel channel = (ServerSocketChannel) k.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    keys.remove(k);
                } else if ((k.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                    SocketChannel channel = (SocketChannel) k.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    channel.read(buffer);
                    buffer.flip();
                    CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer);
                    char[] array = charBuffer.array();
                    String result = new String(array);
                    System.out.println("Read : " + result);
                    buffer.clear();
                    keys.remove(k);
                    k.cancel();
                    channel.close();
                }


            }


        }
    }
}
