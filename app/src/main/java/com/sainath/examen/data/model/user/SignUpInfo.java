package com.sainath.examen.data.model.user;


import com.google.gson.annotations.SerializedName;

public class SignUpInfo {

	@SerializedName("password")
	private String password;

	@SerializedName("fullName")
	private String fullName;

	@SerializedName("userName")
	private String userName;

	@SerializedName("userId")
	private int userId;

	@SerializedName("userMaster")
	private SignUpDto userMaster;

	@SerializedName("message")
	private String message;

	public SignUpInfo (String fullName, String userName, String password) {
		this.fullName = fullName;
		this.userName = userName;
		this.password = password;
	}



	public SignUpDto getUserMaster() {
		return userMaster;
	}

	public void setUserMaster(SignUpDto userMaster) {
		this.userMaster = userMaster;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
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

	@Override
	public String toString(){
		return
				"SignUpDto{" +
						"userMaster = '" + userMaster + '\'' +
						",message = '" + message + '\'' +
						"}";
	}
}