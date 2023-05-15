package com.csit28f3.wildassist;

import java.util.ArrayList;

public class Session {
    private static ArrayList<User> users = new ArrayList<User>();
    private static User activeUser = null;

    public static boolean hasUsers() {
        if (users.size() == 0)
            return true;

        return false;
    }

    public static boolean isUniqueUser(User u) {
        boolean flag = true;

        for (User user : users) {
            if (u.equals(user)) {
                flag = false;
                break;
            }
        }

        return flag;
    }

    public static void addUser(User u) {
        users.add(u);
    }

    public static void setActiveUser(User u) {
        activeUser = u;
    }

    public static User getActiveUser() {
        return activeUser;
    }

    public static boolean isValidCredentials(String email, String password) {
        boolean flag = false;

        for (User u : users) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    public static void login(String email, String password) {
        for (User u : users) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                setActiveUser(u);
                break;
            }
        }
    }
}
