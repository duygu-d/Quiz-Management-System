package org.quiz;

public abstract class User {
    private String id;
    private String username;
    private String passwordSalt;
    private String hashedPassword;

    public User(String id, String username, String passwordSalt,String hashedPassword) {
        this.username = username;
        this.passwordSalt = passwordSalt;
        this.hashedPassword = hashedPassword;
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


    @java.lang.Override
    public java.lang.String toString() {
        return id + ' ' + username + ' ' + passwordSalt+' '+hashedPassword;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    private void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    private void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}
