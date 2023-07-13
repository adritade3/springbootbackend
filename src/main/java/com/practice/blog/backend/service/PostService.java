package com.practice.blog.backend.service;

import java.util.List;

import com.practice.blog.backend.payload.PostDto;
import com.practice.blog.backend.payload.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto post,Integer userId,Integer postId);
	PostDto updatePost(PostDto post,Integer postId);
	String deletePost(Integer postId);
	PostResponse getAllPosts(Integer pageNumber,Integer pageSize);
	PostDto getPostById(Integer postId);
	List<PostDto> getPostsByCategory(Integer id);
	List<PostDto> getPostsByUser(Integer id);
	List<PostDto> searhPosts(String keyword);

}
