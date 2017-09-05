package com.fantasy.mvc.objects;

/**
 * Created by Djelu on 05.09.2017.
 */
public class User {
    private String name;
    private String password;
    private boolean admin;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
