package com.learn.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learn.domain.User;
import com.learn.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//restcontroller json으로 반환
@Controller
public class UserController {
	//new 안하고 spring 한테 넣어달라고 함
	private final UserService userService;

	//생성자 주입 방식
	public UserController(UserService userService){
		this.userService = userService;
	}
	
	@GetMapping("/users")
	//model, 컨트롤러와 jsp 간의 값을 교환하려고 쓰는 공간
	public String getUsers(Model model) {
		String result = "user-list";
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users); 
		return result;
	}
	
	@GetMapping("/user")
	//값으로 리턴됨 (파일 이름이 아니라)
	@ResponseBody
	//컨트롤러와 jsp 간의 값을 교환하려고 쓰는 공간
	public List<User> getUser() {
		List<User> result = userService.getAllUsers();
		
		return result;
	}
	
}
