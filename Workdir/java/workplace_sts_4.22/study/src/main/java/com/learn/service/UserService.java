package com.learn.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learn.domain.User;
import com.learn.mapper.UserMapper;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	//mapper interface 선언 (new대용)
	private final UserMapper userMapper;
	
	public UserService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	public List<User> getAllUsers(){
		List<User> result = null;
		log.info("getAllUsers() 호출");
		result = userMapper.findAll();
//		System.out.println("서비스 완료");
		log.debug("Fetchd users: {}", result);
		log.info("로그 완료!!!!!!");
		// 컨트롤러에 결과 반환
		return result;
	}
}
