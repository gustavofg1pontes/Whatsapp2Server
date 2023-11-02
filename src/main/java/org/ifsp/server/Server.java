package org.ifsp.server;

import org.ifsp.models.user.User;

import java.util.ArrayList;
import java.util.List;

public class Server {
    public static final int DEFAULT_PORT = 12345;
    private static final List<User> users = new ArrayList<>();

    public static void addUser(User user){
        users.add(user);
    }

    public static List<User> getUsers() {
        return users;
    }
}
