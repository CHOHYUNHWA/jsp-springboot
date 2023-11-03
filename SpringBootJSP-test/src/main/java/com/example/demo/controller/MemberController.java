package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.MemberUpdateDto;
import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
	
	private final MemberService memberService;
	

	@GetMapping("/join")
	public String getJoin() {
		Member member =new Member();
		member.setId(5L);
		
		System.out.println(member.getId());
		return "/join";
	}
	
	@PostMapping("join")
	public String join(Member member) {
		memberService.join(member);
		return "/index";
	}
	
	@GetMapping("login")
	public String getLogin() {
		return "/login";
	}
	
	@PostMapping("login")
	public String login(HttpServletResponse response, HttpServletRequest request, Member member) {
		Long result = memberService.login(member.getUsername(),member.getPassword());
		if(result == null) {
			request.setAttribute("msg", "아이디 혹은 비밀번호가 틀렸습니다.");
			request.setAttribute("url", "/login");
			return "alert";
		}
		
		Cookie cookie = new Cookie("auth", String.valueOf(result));
		response.addCookie(cookie);
		
		return "redirect:/";
	}
	
	@GetMapping("memberInfo")
	public String getMember(HttpServletRequest request, Model model) {
		Cookie[] cookies = request.getCookies();
		String memberId = "";
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("auth")) {
				memberId = cookie.getValue();
			}
		}
		if(memberId == "") {
			return "redirect:/login";
		}
		Member member = memberService.findMember(Long.parseLong(memberId));
		
		model.addAttribute("username", member.getUsername());
		model.addAttribute("name", member.getName());
		
		return "memberInfo";
	}
	
	@GetMapping("memberUpdate")
	public String getMemberUpdate(HttpServletRequest request, Model model) {
		Cookie[] cookies = request.getCookies();
		String memberId = "";
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("auth")) {
				memberId = cookie.getValue();
			}
		}
		if(memberId == "") {
			request.setAttribute("msg", "로그인이 필요합니다.");
			request.setAttribute("url", "/");
			return "alert";
		}
		Member member = memberService.findMember(Long.parseLong(memberId));
		
		model.addAttribute("username", member.getUsername());
		model.addAttribute("name", member.getName());
		
		return "memberUpdate";
	}
	
	@PostMapping("memberUpdate")
	public String memberUpdate(HttpServletRequest request, MemberUpdateDto memberUpdateDto) {
		Cookie[] cookies = request.getCookies();
		String memberId = "";
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("auth")) {
				memberId = cookie.getValue();
			}
		}
		if(memberId == "") {
			request.setAttribute("msg", "로그인이 필요합니다.");
			request.setAttribute("url", "/");
			return "alert";
		}
		
		Member member = memberService.updateMember(Long.parseLong(memberId), memberUpdateDto);
		if(member == null) {
			request.setAttribute("msg", "비밀번호가 일치하지 않습니다.");
			request.setAttribute("url", "/memberUpdate");
			return "alert";
		} else {
			request.setAttribute("msg", "비밀번호 수정이 완료되었습니다.");
			request.setAttribute("url", "/memberInfo");
			return "alert";
		}
	}
	
	@PostMapping("memberDelete")
	public String memberDelete(HttpServletRequest request,HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		String memberId = "";
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("auth")) {
				memberId = cookie.getValue();
			}
		}
		if(memberId == "") {
			request.setAttribute("msg", "로그인이 필요합니다.");
			request.setAttribute("url", "/");
			return "alert";
		}
		
		memberService.delete(Long.parseLong(memberId));
		request.setAttribute("msg", "회원탈퇴가 완료되었습니다.");
		request.setAttribute("url", "/");
		Cookie cookie = new Cookie("auth",null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return "alert";
	}
	
	@GetMapping("allMember")
	public String getMembers(Model model) {
		List<Member> memberList = memberService.getAllMember();
		model.addAttribute("memberList", memberList);
		
		for(Member member : memberList) {
			log.info("member ={}", member.toString());
		}
		return "allMember";
	}
}
