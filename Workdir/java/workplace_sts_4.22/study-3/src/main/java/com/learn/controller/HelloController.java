package com.learn.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.vo.UserFormVO;

@RestController
public class HelloController {

	@GetMapping("/hello")
	public String sayHello() {
		String result = "";
		result = "Hello, SpringBoot 3!";
		return result;
	}

	// name 클라이언트가 요청 시 전달하는 쿼리 파라미터(URL 파라미터)
	// @PathVariable URL 경로의 일부를 변수로 사용할 때 사용
	// hello/amy
	@GetMapping("/hello/{name}")
	public String sayHelloToUser(@PathVariable("name") String nameVar) {
		String result = "";
		result = "Hello, " + nameVar + "!";
		return result;
	}

	// userName 클라이언트가 요청 시 전달하는 쿼리 파라미터(URL 파라미터)
	// @RequestParam URL의 쿼리 문자열의 일부를 변수로 사용할 때 사용
	// greet?userName=amy
	@GetMapping("/greet")
	public List<String> greetUser(@RequestParam(name = "userName", defaultValue = "User") String uName) {
		List<String> result = null;
		String result1 = "Hello, " + uName + "1";
		String result2 = "Hello, " + uName + "2";
//		result.add(result1);
//		result.add(result2);
		result = List.of(result1, result2);
		return result;
	}

//	@PostMapping("/user")
////	public String create(@RequestBody String entity) { //다 찍어보기
//	public String create(@RequestParam(name = "id", defaultValue = "0") String id,
//			@RequestParam(name = "name", defaultValue = "User") String name,
//			@RequestParam(name = "email", defaultValue = "User") String email) {
//
//		return id + " : " + name + " : " + email;
//	}
	
//	@PostMapping("/user")
//	public Map<String, Object> create(@RequestParam(name = "id", defaultValue = "0") String id,
//			@RequestParam(name = "name", defaultValue = "User") String name,
//			@RequestParam(name = "email", defaultValue = "User") String email) {
//
////		Map<String, Object> result = new HashMap<>();
//		Map<String, Object> result = new LinkedHashMap<>(); //순서대로 출력됨
//		result.put("id", id);
//		result.put("name", name);
//		result.put("email", email);
//		return result;
//	}
	
	//@ModelAttribute 인자가 없는 생성자가 기본
	@PostMapping("/user")
	public Map<String, Object> create(@ModelAttribute UserFormVO vo) {
	    Map<String, Object> result = new LinkedHashMap<>(); //순서대로 출력됨
	    result.put("id", vo.getId());
	    result.put("name", vo.getName().equals("")?"-":vo.getName());
	    result.put("email", vo.getEmail());
	    return result;
	}

	@GetMapping("/user")
	public List<Map<String, Object>> getUser() {
		List<Map<String, Object>> result = new ArrayList<>();

		Map<String, Object> result1 = new HashMap<>();
		result1.put("name", "홍길동");
		result1.put("age", 28);

		Map<String, Object> result2 = new HashMap<>();
		result2.put("name", "박영수");
		result2.put("age", 45);

		result.add(result1);
		result.add(result2);
		return result;
	}

}
