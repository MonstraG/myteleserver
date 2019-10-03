package com.teleone.mytele.db.user;

import java.io.Serializable;

public class User implements Serializable {
    private String username;

    User(){}

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}