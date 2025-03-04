package com.learn.vo;

public class UserFormVO { // 화면 전송값을 받는 목적이라면 getter/setter만 정의하면 된다.
	private long id;
	private String name;
	private String email;

	//@ModelAttribute가 사용
//	public UserFormVO() {};
//	
//	public UserFormVO(long id, String name, String email) {
//		this.id = id;
//		this.name = name;
//		this.email = email;
//	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserFormVO [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
}
