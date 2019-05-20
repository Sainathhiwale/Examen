package com.sainath.examen.data.model.user;


import com.google.gson.annotations.SerializedName;

public class SignInDto{

    @SerializedName("password")
    private String password;

    @SerializedName("fullName")
    private String fullName;

    @SerializedName("userName")
    private String userName;

    @SerializedName("userId")
    private int userId;

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

    @Override
    public String toString(){
        return
                "UserMaster{" +
                        "password = '" + password + '\'' +
                        ",fullName = '" + fullName + '\'' +
                        ",userName = '" + userName + '\'' +
                        ",userId = '" + userId + '\'' +
                        "}";
    }
}