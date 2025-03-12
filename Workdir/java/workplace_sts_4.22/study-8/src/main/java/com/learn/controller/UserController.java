package com.learn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.entity.User;
import com.learn.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public User createUser(@RequestBody User user) {
		User result = null;
		// result ="createUser()호출 :" +name;
		result = userService.saveUser(user);
		return result;
	}

	@GetMapping
	public List<User> getAllUsers() {
		List<User> result = null;
		// result ="getAllUsers()호출 :" ;
		result = userService.getAllUsers();
		return result;
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
		User result = null;
		// result ="getUserById()호출 :" +id;
		result = userService.getUserById(id);
		return result;

	}

	@PutMapping("/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User user) {
		User result = null;
		// result ="updateUser()호출 :" +id;
		result = userService.updateUser(id, user);
		return result;
	}

	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		String result = null;
		result = "deleteUser()호출 :" + id;

		try {
			userService.deleteUser(id);
		} catch (Exception e) {
			result = "Exception!!!!" + id;
		}
		return result;
	}

}
