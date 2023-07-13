package com.practice.blog.backend.service;

import com.practice.blog.backend.payload.CommentDto;

public interface CommentService {
	CommentDto createComment(CommentDto comment,Integer	postId);
	void deleteComment(Integer commentId);	
	CommentDto updateComment(CommentDto comment,Integer	postId);

}
