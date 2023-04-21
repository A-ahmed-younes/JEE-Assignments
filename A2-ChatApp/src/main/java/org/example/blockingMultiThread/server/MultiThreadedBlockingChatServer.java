package org.example.blockingMultiThread.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MultiThreadedBlockingChatServer extends Thread {
    private List<Conversation> conversations = new ArrayList<>();
    int clientsCount = 0;

    public static void main(String[] args) {
        new MultiThreadedBlockingChatServer().start();
    }

    @Override
    public void run() {
        System.out.println("Server started on port 2000");
        try {
            ServerSocket serverSocket = new ServerSocket(2000);
            while
            (true) {
                Socket socket = serverSocket.accept();
                ++clientsCount;
                Conversation conversation = new Conversation(socket, clientsCount);
                conversations.add(conversation);
                conversation.start();
            }
        } catch
        (Exception e) {
            throw new RuntimeException(e);
        }
    }

    class Conversation extends Thread {
        private int clientId;
        private Socket socket;

        public Conversation(Socket socket, int clientId) {
            this.socket = socket;
            this.clientId = clientId;
        }

        @Override
        public void run() {
            try {
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                OutputStream os = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(os, true);
                System.out.println("New connection from Client n°" + clientId + " with the IP = " + socket.getRemoteSocketAddress());
                pw.println("\t Welcome, you are client n°:" + clientId);
                String request = "";
                String message;
                List<Integer> ids = new ArrayList<>();
                while ((request = br.readLine()) != null) {
                    if (request.contains("=>")) {
                        ids = new ArrayList<>();
                        String[] items = request.split("=>");
                        String clients = items[0];
                        message = items[1];
                        if (clients.contains(",")) {
                            String[] idsListStr = clients.split(",");
                            for (String id : idsListStr) {
                                ids.add(Integer.parseInt(id));
                            }
                        } else {
                            ids.add(Integer.parseInt(clients));
                        }
                    } else {
                        message = request;
                        ids = conversations.stream().map(c -> c.clientId).collect(Collectors.toList());
                    }
                    System.out.println("\t New Request => " + request + " from " + socket.getRemoteSocketAddress());
                    broadcastMessage(message, socket, ids);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public void broadcastMessage(String message, Socket from, List<Integer> clientIds) {
            try {
                for (Conversation conversation : conversations) {
                    Socket socket = conversation.socket;
                    if ((socket != from) && clientIds.contains(conversation.clientId)) {
                        OutputStream os = socket.getOutputStream();
                        PrintWriter printWriter = new PrintWriter(os, true);
                        printWriter.println(message);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
