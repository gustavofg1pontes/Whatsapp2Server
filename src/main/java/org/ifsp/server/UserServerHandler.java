package org.ifsp.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.ifsp.models.message.Message;
import org.ifsp.models.user.User;
import org.ifsp.models.user.usecases.CreateUser;
import org.ifsp.models.user.usecases.UserCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UserServerHandler extends Thread {
    private Socket socket;
    private User user;
    private PrintWriter printWriter;

    public UserServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (true) {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                printWriter = new PrintWriter(socket.getOutputStream(), true);
                String json = in.readLine();

                Gson gson = new Gson();
                UserCommand userCommand = gson.fromJson(json, UserCommand.class);

                switch (userCommand.command()) {
                    case UserCommand.CREATE_USER -> {
                        CreateUser createUser = gson.fromJson(json, CreateUser.class);
                        user = new User(createUser.name(), printWriter);
                        Server.getUsers().add(user);
                    }
                    case UserCommand.SEND_MESSAGE -> broadcastMessage(user, gson.fromJson(json, Message.class).message());
                }
            }
        } catch (IOException e) {
            System.out.println("User disconnected: " + user.getName());
            broadcastMessage(new User("Server", null), user.getName() + " disconnected");
        } finally {
            try {
                if(socket != null && !socket.isClosed())
                    socket.close();
                Server.getUsers().remove(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private static void broadcastMessage(User user, String message) {
        final Gson gson = new GsonBuilder().create();
        final String json = gson.toJson(new Message(message, user.getName()));
        for (User u : Server.getUsers()) {
            if (u.getId() != user.getId()) {
                u.getPrintWriter().println(json);
            }
        }
    }
}