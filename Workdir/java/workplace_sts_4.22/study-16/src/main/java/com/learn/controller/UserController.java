package com.learn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.service.UserService;
import com.learn.vo.User;

@RestController
@RequestMapping("/mongo")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/regist")
	public User createUser(@RequestBody User user) {
		User result = null;
		result = userService.createUser(user);
		return result;
	}
	
	@GetMapping
	public List<User> getAllUsers(){
		List<User> result = null;
		result = userService.getAllUsers();
		return result;
	}
}
