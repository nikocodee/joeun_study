package com.learn.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@RequestMapping("/members")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping
	public List<Member> getAllMembers() {
		List<Member> result = new ArrayList<>();
		result = memberService.getAllMembers();
		return result;
	}
	
	@PostMapping
	//input태그로 받는게 form-data 형태로 받음 (첨부파일이 있으면 무조건 form-data로 받아야함)
	//@RequestBody 있으면 body객체에 Json 타입으로 받음
	public Member createMember(@RequestBody Member member){
		Member result = null;
		result = memberService.createMember(member);
		return result;
	}
	
	//Json으로 받기 위해 Map 사용
	@GetMapping("/{id}")
	public Member getMemberById(@PathVariable String id){
		Member result = null;
		result = memberService.getMemberById(id);
		return result;
	}

	//@RequestBody 없으면 form-data 형태로 받음
	//@RequestBody 있으면 body객체에 Json 타입으로 받음
	@PostMapping("/modify/{id}")
	public Member updateMember(@PathVariable String id, @RequestBody Member member){
		Member result = null;
		result = memberService.updateMember(id, member);
		return result;
	}
	
	@GetMapping("/delete/{id}")
	public Member deleteMember(@PathVariable String id){
		Member result = null;
		result = memberService.deleteMember(id);
		return result;
	}
}
