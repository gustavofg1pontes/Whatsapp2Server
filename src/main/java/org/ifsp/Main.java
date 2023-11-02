package org.ifsp;

import org.ifsp.server.UserServerHandler;
import org.ifsp.server.Server;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(Server.DEFAULT_PORT)) {
            System.out.println("Chat Server is running on port " + Server.DEFAULT_PORT);
            while (true) {
                new UserServerHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}