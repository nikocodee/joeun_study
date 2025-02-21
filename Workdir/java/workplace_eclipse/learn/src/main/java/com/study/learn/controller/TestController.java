package com.study.learn.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
	// https://localhost:8080/api/info
	@GetMapping("/info")
	public String info() {
		String result = "";
		result = "Hello SpringBoot!!!";
		return result;
	}
	
	// https://localhost:8080/api/noti
	@GetMapping("/noti")
	public Map<String, String> noti(){
		Map<String, String> result = new HashMap<>();
		result.put("title", "Hello Json");
		return result;
	}
	
}