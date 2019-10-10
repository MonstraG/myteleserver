package com.teleone.mytele.db.user;

import java.io.Serializable;

@SuppressWarnings("WeakerAccess")
public class User implements Serializable {
    private int id;
    private String username;
    private String password;

    User() { }

    public User(int id) {
        this.id = id;
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}