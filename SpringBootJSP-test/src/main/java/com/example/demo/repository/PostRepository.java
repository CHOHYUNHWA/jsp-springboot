package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.PostResponseDto;
import com.example.demo.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
	
	@Query("SELECT new com.example.demo.dto.PostResponseDto(p.id, p.title, m.name, p.createdAt) FROM Post p JOIN p.member m")
	List<PostResponseDto> getAllPost();

}
