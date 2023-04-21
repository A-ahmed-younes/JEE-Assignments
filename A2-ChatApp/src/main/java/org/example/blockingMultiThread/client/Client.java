package org.example.blockingMultiThread.client;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        String serverAddress = "localhost";
        int port = 2000;
        Socket socket = new Socket(serverAddress, port);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Connected!");
        while (true) {
            String message = consoleInput.readLine();
            output.println(message);
            if (message.equals("exit")) break;
            System.out.println(input.readLine());
        }
        socket.close();
    }
}