package com.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.service.AuthService;
import com.learn.vo.LoginRequestFormVO;
import com.learn.vo.UserSessionDto;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

//	private final AuthService authService;
//	
//	AuthController(AuthService auth) {
//		this.authService = auth;
//	}

	// @RequestBody body에 담아서 JSON으로 받을거다 -> 많이 씀
	// 아무것도 없으면 form-data로 받음 -> 페이지 reload 돼서 리액션 죽음
	// 단, 첨부파일 있으면 multipart form-data로 받음
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequestFormVO vo, HttpSession sess) {
//		ResponseEntity<String> result = null;
		UserSessionDto authUser = authService.authenticate(vo);
		sess.setAttribute("UserInfo", authUser);
		if (authUser != null) {
			return ResponseEntity.ok("login 성공");
		} else {
			return ResponseEntity.status(400).body("Check ID/Pass");
		}
//		return result;
	}

	@GetMapping("/me")
	public ResponseEntity<Object> me(HttpSession sess) {
		UserSessionDto sessUser = (UserSessionDto) sess.getAttribute("UserInfo");
		if (sessUser != null) {
			return ResponseEntity.ok(sessUser);
		} else {
			return ResponseEntity.status(400).body("로그인부터!!!");
		}
	}
	
	@GetMapping("/logout")
	public ResponseEntity<Object> logout(HttpSession sess){
		sess.invalidate();
		return ResponseEntity.ok("로그아웃");
	}

}
