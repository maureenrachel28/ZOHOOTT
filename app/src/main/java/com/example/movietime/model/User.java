package com.example.movietime.model;

public class User {
    String username;
    String password;
    Boolean isAdmin;

    public Boolean getAdmin() {
        return isAdmin;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
