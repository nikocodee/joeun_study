package com.learn.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.entity.Member;
import com.learn.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	public List<Member> getAllMembers(){
		List<Member> result = null;
		result = memberRepository.findAll();
		return result;
	}
	
	public Member createMember(Member member) {
		Member result = null;
		result = memberRepository.save(member);
		return result;
	}
	
	public Member getMemberById(String id) {
		Member result = null;
		result = memberRepository.findById(id).orElse(result);
		return result;
	}
	
	public Member updateMember(String id, Member memberDetail) {
		Member result = null;
		result = memberRepository.findById(id).map(member -> {
			member.setName(memberDetail.getName());
			member.setPhone(memberDetail.getPhone());
			return memberRepository.save(member);
		}).orElseThrow(() -> new RuntimeException("User not found"));
		
		return result;
	}
	
	public Member deleteMember(String id) {
		Member result = null;
		result = memberRepository.findById(id).map(member -> {
			member.setUser_status("retire");
			return memberRepository.save(member);
		}).orElseThrow(()-> new RuntimeException("User not found"));
		return result;
	}
}
