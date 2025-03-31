package com.learn.vo;

import java.io.Serializable;

public class UserSessionDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userId;
	private String userName;

	public UserSessionDto() {

	}

	public UserSessionDto(String userId, String userName) {
		super();
		this.userId = userId;
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	@Override
	public String toString() {
		return "UserSessionDto [userId=" + userId + ", userName=" + userName + "]";
	}

}
