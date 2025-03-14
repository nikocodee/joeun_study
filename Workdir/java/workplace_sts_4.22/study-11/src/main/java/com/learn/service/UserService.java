package com.learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.mapper.UserMapper;
import com.learn.vo.UserVO;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	public List<UserVO> getAllUsers(){
		List<UserVO> result = null;
		result = userMapper.getAllUsers();
		return result;
	}
	
	public int createUser(UserVO userVO) {
		int result = 0;
		result = userMapper.createUser(userVO);
		return result;
	}
	
	public UserVO getUserById(Long id) {
		UserVO result = null;
		result = userMapper.getUserById(id);
		return result;
	}
	
	public int updateUser(Long id, UserVO userVO) {
		int result = 999999;
		userVO.setId(id);
		result = userMapper.updateUser(userVO);
		return result;
	}
	
	public int deleteUser(Long id) {
		int result = 999999;
		result = userMapper.deleteUser(id);
		return result;
	}
}
