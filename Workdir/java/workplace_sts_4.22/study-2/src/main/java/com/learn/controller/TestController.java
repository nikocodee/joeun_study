package com.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//RestController 자체가 값을 반환함
//@RestController 
@Controller
@RequestMapping("/test")
public class TestController {
	
	@GetMapping("/")
	@ResponseBody
	public String home() {
		String result = "";
		result = "Home Page!!!";
		return result;
	}
	
	@GetMapping("/temp")
	public String test() {
		String result = "";
		result = "testTemplate";
		return result;
	}
}
