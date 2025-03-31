package com.learn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.vo.UserSessionDto;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/session")
public class SessionController {
	@PostMapping("/create")
	public String createSession(@RequestBody UserSessionDto user, HttpSession sess) {
		String result = null;
		try {
			sess.setAttribute("user", user);
			result = "세션 생성 정상 수행";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			result = "Exception Raise !!!!";
		}
		return result;
	}

	@GetMapping("/read")
	public Object readSession(HttpSession sess) {
		Object result = null;
		result = sess.getAttribute("user");
		return result;
	}

	@PostMapping("/update")
	public String updateSession(@RequestBody UserSessionDto user, HttpSession sess) {
		String result = "수정 완료";
		sess.setAttribute("user", user);
		return result;
	}

	@GetMapping("/logout")
	public String deleteSession(HttpSession sess) {
		String result = "로그아웃";
		sess.invalidate();
		return result;
	}
}
