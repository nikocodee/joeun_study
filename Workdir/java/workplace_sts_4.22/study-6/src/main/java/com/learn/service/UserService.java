package com.learn.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learn.entity.User;
import com.learn.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository; //여기서는 자리만 만듦
	
	// spring이 생성자 호출되면 알아서 UserRepository를 위에 필드에 넣어줌
	public UserService(UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
	public List<User> getAllUesrs(){
		List<User> result = null;
		result = userRepository.findAll();
		return result;
	}
	
	public User createUser(String name){
		User result = null;
		result = userRepository.save(new User(name));
		System.out.println("저장 PK : " + result.getId());
		return result;
	}
}
