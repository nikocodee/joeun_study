package com.learn.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class UserVO {
	private Long id;
	private String name;
	private String phone;
	private String address;
	private String addressDetail;
	private String userStatus;
	private String userClass;
	private String email;
	private boolean marketingAgree;
	private String socialLogin;
	private Timestamp lastLoggedAt; // 연월일시분초
	private Date createdAt; // 연월일

	public UserVO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserClass() {
		return userClass;
	}

	public void setUserClass(String userClass) {
		this.userClass = userClass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isMarketingAgree() {
		return marketingAgree;
	}

	public void setMarketingAgree(boolean marketingAgree) {
		this.marketingAgree = marketingAgree;
	}

	public String getSocialLogin() {
		return socialLogin;
	}

	public void setSocialLogin(String socialLogin) {
		this.socialLogin = socialLogin;
	}

	public Timestamp getLastLoggedAt() {
		return lastLoggedAt;
	}

	public void setLastLoggedAt(Timestamp lastLoggedAt) {
		this.lastLoggedAt = lastLoggedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "UserVO [id=" + id + ", name=" + name + ", phone=" + phone + ", address=" + address + ", addressDetail="
				+ addressDetail + ", userStatus=" + userStatus + ", userClass=" + userClass + ", email=" + email
				+ ", marketingAgree=" + marketingAgree + ", socialLogin=" + socialLogin + ", lastLoggedAt="
				+ lastLoggedAt + ", createdAt=" + createdAt + "]";
	}

}
