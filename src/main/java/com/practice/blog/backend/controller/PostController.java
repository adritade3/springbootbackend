package com.practice.blog.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.blog.backend.payload.PostDto;
import com.practice.blog.backend.payload.PostResponse;
import com.practice.blog.backend.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDto> createpost(@Valid @RequestBody PostDto post,
			@PathVariable Integer userId,@PathVariable Integer categoryId ){
		PostDto createdPost=this.postService.createPost(post, userId, categoryId);
		return new ResponseEntity<>(createdPost,HttpStatus.CREATED);
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<PostDto>> getAllPostsByCategory(@PathVariable("categoryId") Integer id){
		List<PostDto> categoryAllPosts=this.postService.getPostsByCategory(id);
		return new ResponseEntity<>(categoryAllPosts,HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<PostDto>> getAllPostsByUser(@PathVariable("userId") Integer id){
		List<PostDto> userAllPosts=this.postService.getPostsByUser(id);
		return new ResponseEntity<>(userAllPosts,HttpStatus.OK);
	}
	
	@GetMapping("/all/")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value="pageNumber",defaultValue="0") Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue="3") Integer pageSize){
		PostResponse allPosts=this.postService.getAllPosts(pageNumber,pageSize);
		return new ResponseEntity<>(allPosts,HttpStatus.OK);
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer id){
		PostDto post=this.postService.getPostById(id);
		return new ResponseEntity<>(post,HttpStatus.OK);
	}

}
