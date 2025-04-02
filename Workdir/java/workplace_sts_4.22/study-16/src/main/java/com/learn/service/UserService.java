package com.learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.dao.UserRepository;
import com.learn.vo.User;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers(){
		List<User> result = null;
		result = userRepository.findAll();
		return result;
	}
	
	public User createUser(User user) {
		User result = null;
		result = userRepository.save(user);
		return result;
	}
}
