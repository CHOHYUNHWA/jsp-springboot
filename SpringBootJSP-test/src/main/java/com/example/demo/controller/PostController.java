package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.GetPostResponseDto;
import com.example.demo.dto.PostCreateDto;
import com.example.demo.dto.PostResponseDto;
import com.example.demo.dto.PostUpdateDto;
import com.example.demo.entity.Post;
import com.example.demo.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {
	
	private final PostService postService;
	
	
	@GetMapping("/board")
	public String getBoards(Model model) {
		List<PostResponseDto> postList = postService.getAllPost();
		
		model.addAttribute("postList", postList);
		
		
		return "/board";
	}
	
	@GetMapping("/post")
	public String getPost(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		Long memberId = null;
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("auth")) {
				memberId = Long.parseLong(cookie.getValue());
			}
		}
		
		if(memberId == null) {
			request.setAttribute("msg", "로그인이 필요합니다.");
			request.setAttribute("url", "/login");
			return "alert";
		}
		
		return "/postCreate";
	}
	
	@PostMapping("post")
	public String addPost(HttpServletRequest request, PostCreateDto postCreateDto) {
		Cookie[] cookies = request.getCookies();
		Long memberId = null;
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("auth")) {
				memberId = Long.parseLong(cookie.getValue());
			}
		}
		
		if(memberId == null) {
			request.setAttribute("msg", "로그인이 필요합니다.");
			request.setAttribute("url", "/");
			return "alert";
		}
		
		postService.addPost(memberId, postCreateDto);
		
		return "redirect:/board";
	}
	
	@GetMapping("/post/{postId}")
	public String getPost(@PathVariable("postId") Long postId, HttpServletRequest request, Model model) {
		Cookie[] cookies = request.getCookies();
		Long memberId = null;
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("auth")) {
				memberId = Long.parseLong(cookie.getValue());
			}
		}
		
		Post post = postService.getPost(postId);
		
		log.info("post memberId ={}, auth memberId = {}"   , post.getMember().getId(), memberId);
		
		GetPostResponseDto getPostResponseDto = new GetPostResponseDto();
		getPostResponseDto.toGetPostResponseDto(post);
		if(post.getMember().getId() == memberId) {
			getPostResponseDto.setCheckWriter(true);
		}
				
		model.addAttribute("postId", String.valueOf(postId));
		model.addAttribute("title", getPostResponseDto.getTitle());
		model.addAttribute("writer",getPostResponseDto.getWriter());
		model.addAttribute("createdAt",getPostResponseDto.getCreatedAt());
		model.addAttribute("body",getPostResponseDto.getBody());
		model.addAttribute("checkWriter",getPostResponseDto.isCheckWriter());
		return "/post";
	}
	
	@GetMapping("/postUpdate/{postId}")
	public String getPostUpdate(@PathVariable("postId") Long postId, HttpServletRequest request,Model model) {
		Cookie[] cookies = request.getCookies();
		Long memberId = null;
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("auth")) {
				memberId = Long.parseLong(cookie.getValue());
			}
		}
		
		Post post = postService.getPost(postId);
		
		if(post.getMember().getId() != memberId) {
			request.setAttribute("msg", "권한이 없습니다.");
			request.setAttribute("url", "/");
			return "alert";
		}
		
		model.addAttribute("postId", post.getId());
		model.addAttribute("title",post.getTitle());
		model.addAttribute("writer",post.getMember().getName());
		model.addAttribute("createdAt",post.getCreatedAt());
		model.addAttribute("body",post.getBody());	
		return "/postUpdate";
	}
	
	@PostMapping("/postUpdate/{postId}")
	public String postUpdate(@PathVariable("postId") Long postId, 
													 HttpServletRequest request, 
													 PostUpdateDto postUpdateDto,
													 Model model) {
		
		Cookie[] cookies = request.getCookies();
		Long memberId = null;
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("auth")) {
				memberId = Long.parseLong(cookie.getValue());
			}
		}
		
		Post post = postService.updatePost(postId, postUpdateDto);
		
		
		if(post.getMember().getId() != memberId) {
			request.setAttribute("msg", "권한이 없습니다.");
			request.setAttribute("url", "/");
			return "alert";
		}
		
		model.addAttribute("postId", String.valueOf(postId));
		model.addAttribute("title", post.getTitle());
		model.addAttribute("writer",post.getMember().getName());
		model.addAttribute("createdAt",post.getCreatedAt());
		model.addAttribute("body",post.getBody());
		
		
		return "redirect:/post/{postId}";
	}
	
	@PostMapping("/postDelete/{postId}")
	public String postDelete(@PathVariable("postId") Long postId,HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		Long memberId = null;
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("auth")) {
				memberId = Long.parseLong(cookie.getValue());
			}
		}
		
		boolean result = postService.deletePost(memberId, postId);
		if(result == false) {
			request.setAttribute("msg", "권한이 없습니다.");
			request.setAttribute("url", "/post/" + postId );
			return "/alert";
		} else {
			request.setAttribute("msg", "게시글이 삭제되었습니다.");
			request.setAttribute("url", "/board");
			return "/alert";
		}
		
	}
}
