package com.learn.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.entity.Member;
import com.learn.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;

	//하드코딩 방지로 상수 선언
	private final String DELETE_FLAG = "retire";
	
	//원래 spring이 이렇게 담아줬음
//	private final MemberRepository memberRepository;
//	
//	MemberService(MemberRepository memberRepo){ //너가 전해주는 memberRepository를 집어 넣겠다
//		this.memberRepository = memberRepo;
//	}
	
	// ** user_status = active만 출력하기
	public List<Member> getAllMembers(){
		List<Member> resultTemp = null;
		List<Member> result = new ArrayList<>();
		resultTemp = memberRepository.findAll();
		
		// 1.for문
//		for(int i=0; i<resultTemp.size(); i++) {
//			Member temp = resultTemp.get(i);
//			if("active".equals(temp.getUser_status())) {
//				result.add(temp);
//			}
//		}
		
		// 2.stream 사용
		result = resultTemp.stream().filter(member->{
			return "active".equals(member.getUser_status());
		}).collect(Collectors.toList());
		
//		result = resultTemp.stream().filter(member->{
//			boolean cond1 = "active".equals(member.getUser_status());
//			boolean cond2 = false;
//			return cond1 && !cond2;
//		}).collect(Collectors.toList());
		
		return result;
	}
	
	//controller에서 member를 전달 받음
	public Member createMember(Member member) {
		Member result = null;
		result = memberRepository.save(member);
		return result;
	}
	
	public Member getMemberById(String id) {
		Member result = null;
		//가정의 케이스
		Member emptyResult = new Member();
		// -로 빈 값이라는 걸 설정
		emptyResult.setName("-");
		emptyResult.setPhone("-");
		emptyResult.setEmail("-");
		//cannot convert from Optional<Member> to Member 리턴타입이 달라서 오류
		//orElse 널이면 내가 만들어 놨던 빈 값을 보냄
		result = memberRepository.findById(id).orElse(emptyResult);
		return result;
	}
	
	public Member updateMember(String id, Member memberDetails) {
		Member result = null;
		Member emptyResult = new Member();
		emptyResult.setName("-");
		emptyResult.setPhone("-");
		result = memberRepository.findById(id).map(member->{
			member.setName(memberDetails.getName());
			member.setPhone(memberDetails.getPhone());
			return memberRepository.save(member);
		}).orElse(emptyResult);
		
//		result = memberRepository.findById(id).map(member->{
//			member.setName(memberDetails.getName());
//			member.setPhone(memberDetails.getPhone());
//			//map함수의 리턴
//			return memberRepository.save(member);
//		}).orElseThrow(()-> new RuntimeException("잘못된 ID"));
		return result;
	}
	
	public Member deleteMember(String id){
		Member result = null;
		Member emptyResult = new Member();
		emptyResult.setUser_status("-");
		result = memberRepository.findById(id).map(member->{
			member.setUser_status(DELETE_FLAG);
			return memberRepository.save(member);
		}).orElse(emptyResult);
		
//		result = memberRepository.findById(id).map(member->{
//			//맨위에 선언한 상수 사용
//			member.setUser_status(DELETE_FLAG);
//			return memberRepository.save(member);
//		}).orElseThrow(()-> new RuntimeException("잘못된 ID"));
		return result;
	}
}
