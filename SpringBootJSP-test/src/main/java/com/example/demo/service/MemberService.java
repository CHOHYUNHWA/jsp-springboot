package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.MemberUpdateDto;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
	
	private final MemberRepository memberRepository;
	

	public void join(Member member) {
		memberRepository.save(member);
	}

	public Long login(String username, String password) {
		Member findMember = memberRepository.findByUsernameAndPassword(username, password).orElse(null);
		if(findMember == null) {
			return null;
		}
		return findMember.getId();
		
	}
	
	public Member findMember(Long memberId) {
		Optional<Member> findMember = memberRepository.findById(memberId);
		Member member = findMember.orElse(null);
		return member;
	}
	
	public Member updateMember(Long memberId, MemberUpdateDto memberUpdateDto) {
		Member member = findMember(memberId);
		if(member.getPassword().equals(memberUpdateDto.getPassword())) {
			member.setPassword(memberUpdateDto.getNewPassword());
		} else {
			return null;
		}
		return member;
	}

	public void delete(Long memberId) {
		memberRepository.deleteById(memberId);
		
	}
	
	
	public List<Member> getAllMember(){
		return memberRepository.findAll();
	}
}
