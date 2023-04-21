package org.example.nonblockingSingleThread.client;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 2001));
        Scanner scanner = new Scanner(System.in);
        // Main loop
        while (true) {
            String request = scanner.nextLine();
            // Sending data to the server and storing the response in a buffer
            ByteBuffer byteBuffer = ByteBuffer.wrap(request.getBytes());
            socketChannel.write(byteBuffer);
            ByteBuffer responseBuffer = ByteBuffer.allocate(1024);
            int bytesRead = socketChannel.read(responseBuffer);
            if (bytesRead > 0) {
                String response = new String(responseBuffer.array(), 0, bytesRead).trim();
                System.out.println("Response => " + response);
            }
        }
    }
}



