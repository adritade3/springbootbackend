package com.practice.blog.backend.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
	
	private String content;
	private UserDto user;

}
