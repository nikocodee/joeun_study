package com.learn.vo;

import java.io.Serializable;

public class UserSessionDto implements Serializable{
	//Serializable import 후 Quick fix 하면 자동 생성
	private static final long serialVersionUID = 1701763464718044061L;
	private String userId;
	private String userName;

	public UserSessionDto() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "UserSessionDto [userId=" + userId + ", userName=" + userName + "]";
	}
}
