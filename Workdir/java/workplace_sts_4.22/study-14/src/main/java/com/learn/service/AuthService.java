package com.learn.service;

import org.springframework.stereotype.Service;

import com.learn.vo.LoginRequestFormVO;
import com.learn.vo.UserSessionDto;

@Service
public class AuthService {
//	public UserSessionDto authenticate(String uId, String pw){
	public UserSessionDto authenticate(LoginRequestFormVO vo) {
		UserSessionDto result = null;
		// 비교하려는 값을 앞에 두기
		// 만약 uId, pw가 null이면 equals 안됨 -> NullPointerException 방지
		// DB값.equals(화면에서 넘긴 값)
//		if ("test".equals(uId) && "123".equals(pw)) {
		if ("test".equals(vo.getUserId()) && "123".equals(vo.getPassword())) {
			result = new UserSessionDto(vo.getUserId(), "홍길동");
		}
		return result;
	}
}
