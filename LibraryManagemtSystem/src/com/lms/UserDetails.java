package com.lms;

public class UserDetails {
	private int userId;
	private String userName;

	public UserDetails(int userId,String userName)
	{
		this.userId=userId;
		this.userName=userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setuserID(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


}