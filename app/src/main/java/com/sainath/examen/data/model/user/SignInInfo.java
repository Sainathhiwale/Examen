package com.sainath.examen.data.model.user;

import com.google.gson.annotations.SerializedName;

public class SignInInfo {


    @SerializedName("userName")
    private String userName;

    @SerializedName("password")
    private String password;


    @SerializedName("userMaster")
    private  SignInDto userMaster;

    @SerializedName("message")
    private String message;

    public SignInInfo(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SignInDto getUserMaster() {
        return userMaster;
    }

    public void setUserMaster(SignInDto userMaster) {
        this.userMaster = userMaster;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    @Override
    public String toString(){
        return
                "SignInDto{" +
                        "userMaster = '" + userMaster + '\'' +
                        ",message = '" + message + '\'' +
                        "}";
    }

}
