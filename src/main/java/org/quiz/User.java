package org.quiz;

public abstract class User {
    private String id;
    private String username;
    private String password;

    public User(String id, String username, String password) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
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

    @java.lang.Override
    public java.lang.String toString() {
        return id + ' ' + username + ' ' + password;
    }
}
