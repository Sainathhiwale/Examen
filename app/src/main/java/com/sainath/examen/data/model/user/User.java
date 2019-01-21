package com.sainath.examen.data.model.user;

public class User {
    private String displayName;
    private String email;
    private String  userID;
    private String userPhone;


    public User() {

    }


    public User(String id, String email, String displayName) {
        this.userID =id;
        this.email = email;
        this.displayName =displayName;
    }


    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}

