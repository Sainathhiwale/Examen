package com.sainath.examen.data.model.user;

import com.google.gson.annotations.SerializedName;

public class SignUpDto {

    @SerializedName("password")
    private String password;

    @SerializedName("fullName")
    private String fullName;

    @SerializedName("userName")
    private String userName;

    @SerializedName("userId")
    private int userId;

    @SerializedName("message")
    private String message;

    public SignUpDto(String fullName, String userName, String password) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    public String getFullName(){
        return fullName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public int getUserId(){
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString(){
        return
                "SignUpInfo{" +
                        "password = '" + password + '\'' +
                        ",fullName = '" + fullName + '\'' +
                        ",userName = '" + userName + '\'' +
                        ",userId = '" + userId + '\'' +
                        "}";
    }
}

