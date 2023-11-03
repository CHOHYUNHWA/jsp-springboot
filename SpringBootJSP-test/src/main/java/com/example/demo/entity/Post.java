package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String title;
	
	private String body;
	
	private LocalDateTime createdAt;
	
	@ManyToOne
	private Member member;
	
	public void setCreatedAt(LocalDateTime nowTime) {
		this.createdAt = nowTime;
	}
	
	public void addMember(Member member) {
		this.member = member;
		member.getPosts().add(this);
	}
	
	public void updatePost(String title, String body) {
		this.title = title;
		this.body = body;
	}
}
