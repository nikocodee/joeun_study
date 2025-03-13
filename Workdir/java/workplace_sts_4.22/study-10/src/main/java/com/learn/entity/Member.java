package com.learn.entity;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
//@Table(name = "customer") //참고로 만들어봄
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	//not null
	//길이는 기본 255자로 들어감
	@Column(nullable = false, length = 20)
	private String name;
	private String phone;
	private String address;
	private String address_detail;
	//user_status 기본값 설정
	private String user_status = "active";
	private String user_class;
	//unique 해야함 (겹치면 안됨), unique key
	@Column(nullable = false, length = 50, unique = true)
	private String email;
	// 대문자 Boolean 객체 (heap메모리에 저장), 변수에는 주소의 시작점만 저장됨, 기본값은 null
	// 소문자 boolean은 primitive 타입 (stack메모리에 저장), 기본값은 false, 0
	private Boolean marketing_agree;
	private String social_login;
	private Timestamp last_logged_at;
	private Date created_at;

	//기본 생성자
	//매개변수 있는 생성자 생성시, 기본 생성자는 안 만들기 때문에 만들어 둠
	public Member() {
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getAddress_detail() {
		return address_detail;
	}

	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}

	public String getUser_status() {
		return user_status;
	}

	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}

	public String getUser_class() {
		return user_class;
	}

	public void setUser_class(String user_class) {
		this.user_class = user_class;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getMarketing_agree() {
		return marketing_agree;
	}

	public void setMarketing_agree(Boolean marketing_agree) {
		this.marketing_agree = marketing_agree;
	}

	public String getSocial_login() {
		return social_login;
	}

	public void setSocial_login(String social_login) {
		this.social_login = social_login;
	}

	public Timestamp getLast_logged_at() {
		return last_logged_at;
	}

	public void setLast_logged_at(Timestamp last_logged_at) {
		this.last_logged_at = last_logged_at;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", phone=" + phone + ", address=" + address + ", address_detail="
				+ address_detail + ", user_status=" + user_status + ", user_class=" + user_class + ", email=" + email
				+ ", marketing_agree=" + marketing_agree + ", social_login=" + social_login + ", last_logged_at="
				+ last_logged_at + ", created_at=" + created_at + "]";
	}

}
