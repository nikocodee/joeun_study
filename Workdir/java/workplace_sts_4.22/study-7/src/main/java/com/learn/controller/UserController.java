package com.learn.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	@PostMapping
	//@RequestBody body에서 text,JSON으로 받음
	public String createUser(@RequestBody String name){
		String result = null;
		result = "createUser() 호출 : " + name;
		return result;
	}
	
	@GetMapping
	public String getAllUsers() {
		String result = null;
		result = "getAllUsers() 호출 ";
		return result;
	}
	
	@GetMapping("/{id}")
	public String getUserById(@PathVariable Long id) {
		String result = null;
		result = "getUserById() 호출 : " + id;
		return result;
	}

	@PutMapping("/{id}")
	public String updateUser(@PathVariable Long id){
		String result = null;
		result = "updateUser() 호출 : " + id;
		return result;
	}

	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id){
		String result = null;
		result = "deleteUser() 호출 : " + id;
		return result;
	}
}
