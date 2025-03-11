package com.learn.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.entity.User;
import com.learn.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<User> getAllUsers() {
		List<User> result = null;
		result = userService.getAllUesrs(); // "getAllUsers() 호출";
		return result;
	}

	// @RequestParam 쿼리파라미터로 전달, 보통 get방식에 사용(url 노출)
	@PostMapping
	public User createUser(String name) {
		User result = null;
		result = userService.createUser(name); // "createUser() 호출 : " + name;
		return result;
	}
}
