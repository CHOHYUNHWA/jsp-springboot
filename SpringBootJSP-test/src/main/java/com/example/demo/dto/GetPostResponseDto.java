package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPostResponseDto {
	
	private String title;
	private String body;
	private String writer;
	private boolean checkWriter;
	private LocalDateTime createdAt;
	

	public void toGetPostResponseDto(Post post) {
	    this.title = post.getTitle();
	    this.body = post.getBody();
	    this.writer = post.getMember().getName();
	    this.createdAt = post.getCreatedAt();
	}
}
