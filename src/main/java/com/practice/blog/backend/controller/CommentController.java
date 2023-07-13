package com.practice.blog.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.blog.backend.payload.ApiResponse;
import com.practice.blog.backend.payload.CommentDto;
import com.practice.blog.backend.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/post/{postId}/comment")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment,@PathVariable Integer postId){
		CommentDto commentDto=this.commentService.createComment(comment, postId);
		return new ResponseEntity<>(commentDto,HttpStatus.CREATED);
	}

	@DeleteMapping("/post/{postId}/comments")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer postId){
		this.commentService.deleteComment(postId);
		return new ResponseEntity<>(new ApiResponse("comment deleted successfully",true),HttpStatus.OK);
	}
}
