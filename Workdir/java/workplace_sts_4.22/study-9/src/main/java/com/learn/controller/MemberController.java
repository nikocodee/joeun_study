package com.learn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.entity.Member;
import com.learn.service.MemberService;

@RestController
@RequestMapping("/users")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping
	public List<Member> getAllMembers() {
		List<Member> result = null;
//		result = "getAllUsers() 호출";
		result = memberService.getAllMembers();
		return result;
	}
	
	@PostMapping
	public Member createMember(@RequestBody Member member) {
		Member result = null;
//		result = "createUser() 호출 : " 
//				+ "name : " + name + "phone : " + phone + "email : " + email;
		result = memberService.createMember(member);
		return result;
	}
	
	@GetMapping("/{id}")
	public Member getMemberById(@PathVariable String id) {
		Member result = null;
//		result = "getUserById() 호출" + id;
		result = memberService.getMemberById(id);
		return result;
	}
	
	@PostMapping("/modify/{id}")
	public Member updateMember(@PathVariable String id, @RequestBody Member member) {
		Member result = null;
//		result = "updateUser() 호출" + id;
		result = memberService.updateMember(id, member);
		return result;
	}
	
	@GetMapping("/delete/{id}")
	public Member deleteMember(@PathVariable String id) {
		Member result = null;
//		result = "deleteUser() 호출" + id;
		result = memberService.deleteMember(id);
		return result;
	}
	
}
