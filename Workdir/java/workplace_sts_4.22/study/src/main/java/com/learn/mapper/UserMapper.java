package com.learn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.learn.domain.User;

@Mapper
public interface UserMapper {
	// 메소드가 mappe.xml에서 id
	List<User> findAll();
}