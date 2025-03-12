package com.learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.entity.User;
import com.learn.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
//	private final UserRepository useRepository;
	
//	public UserService(UserRepository userRepository) {
//		this.userRepository = userRepository;  //너가 전해주는 userRepository를 집어 넣겠다.
//	}
	
	
	// CREATE 사용자 추가
	public User saveUser(User user){
		User result = null;
		result = userRepository.save(user);
		return result;
	}
	
	// READ 모든 사용자 조회
	public List<User> getAllUsers(){
		List<User> result =null;
		result = userRepository.findAll();
		return result;
		
	}
	
	// READ ID로 사용자 조회
	public User getUserById(Long id){
		User result = null;
		result = userRepository.findById(id).orElse(result);
		return result;
		
	}
	
	// 사용자 정보 수정
	public User updateUser(Long id, User userDetail){
		User result = null;
		userRepository.findById(id).map(user ->{
			user.setName(userDetail.getName());
			user.setEmail(userDetail.getEmail());
			return userRepository.save(user);
		}).orElseThrow(()->new RuntimeException("User not found"));
		
		return result;
		
//		userRepository.findById(id).map(user -> 
//		{user.setName(userDetail.getName());
//		 user.setEmail(userDetail.getEmail());
//		 return userRepository.save(user);
//		 }).orElseThrow(()-> new RuntimeException("User not found"));
	}
	
	// 사용자 삭제
	public boolean deleteUser(Long id) {
		boolean result = false;
		try {
			userRepository.deleteById(id);
			result = true;
		} catch (Exception e) {
			result = false;
		}
		
		return result;
		
		//User result = null;
//		return result;
	}

}
