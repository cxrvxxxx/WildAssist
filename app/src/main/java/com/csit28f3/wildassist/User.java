package com.csit28f3.wildassist;

public class User {
    private String name;
    private String email;
    private String password;

    public User() {}

    public User(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(Object o) {
        if (o instanceof User) {
            User temp = (User) o;

            if (temp.getName() == this.getName() && temp.getEmail() == this.getEmail())
                return true;
        }

        return false;
    }
}
