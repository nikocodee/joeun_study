package com.learn.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users") // user는 db에서 예약어이므로 테이블명 지정
public class User {
	// primitive type이라 stack에 저장되어서 빠르지만 overflow 위험
//	long id;
	// 객체라 heap에 저장되어 공간은 여유있지만 속도는 느림, 처리 속도가 중요하지 않으면 Long
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	// 기본 생성자
	// getter, setter 호출(사용)하기 위해 필요
	public User() {
	}

	// 생성자
	// 초기화 목적 / 생성값 전달 목적
	// 생성자 만들면 컴파일러가 기본 생성자는 안 만들어 줌
	public User(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}

}
