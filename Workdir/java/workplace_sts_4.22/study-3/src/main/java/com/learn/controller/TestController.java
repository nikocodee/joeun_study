package com.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //페이지명
@RequestMapping("/test")
public class TestController {
	
	@GetMapping("/temp")
	@ResponseBody //값 반환 (String / JSON / JSON Array)
	public String home() {
		String result = "";
		result = "Temp Page!!!";
		return result;
	}
	
	@GetMapping("/thyme")
	public String temp() {
		String result = "";
		result = "tempTemplate";
		return result;
	}
}