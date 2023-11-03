package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.PostCreateDto;
import com.example.demo.dto.PostResponseDto;
import com.example.demo.dto.PostUpdateDto;
import com.example.demo.entity.Member;
import com.example.demo.entity.Post;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor

public class PostService {
	
	private final PostRepository postRepository;
	private final MemberRepository memberRepository;
	
	
	public String addPost(Long memberId , PostCreateDto postCreateDto) {
		Post post = postCreateDto.toEntity(postCreateDto);
		Member member = memberRepository.findById(memberId).orElse(null);
		post.setCreatedAt(LocalDateTime.now());
		post.addMember(member);
		postRepository.save(post);
		
		return "/board";
	}
	
	public List<PostResponseDto> getAllPost() {
		List<PostResponseDto> postList = postRepository.getAllPost();
		return postList;
	}
	
	public Post getPost(Long postId) {
		Optional<Post> optionalPost = postRepository.findById(postId);
		Post post = optionalPost.orElse(null);
		
		
		
		return post;
		
	}
	
	public Post updatePost(Long postId, PostUpdateDto postUpdateDto) {
		Post post = getPost(postId);
		post.updatePost(postUpdateDto.getTitle(), postUpdateDto.getBody());
		return post;
	}
	
	public boolean deletePost(Long memberId, Long postId) {
		Post post = getPost(postId);
		if(post.getMember().getId() != memberId) {
			return false;
		} else {
			postRepository.delete(post);
			return true;
		}
	}
}