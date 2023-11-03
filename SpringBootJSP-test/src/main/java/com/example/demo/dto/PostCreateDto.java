package com.example.demo.dto;

import com.example.demo.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCreateDto {
	
	private String title;
	private String body;
	
	
	public Post toEntity(PostCreateDto postCreateDto) {
		Post post =  Post.builder()
				.title(postCreateDto.getTitle())
				.body(postCreateDto.getBody())
				.build();
		return post;
	}
}
