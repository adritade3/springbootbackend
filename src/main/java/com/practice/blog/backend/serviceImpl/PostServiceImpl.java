package com.practice.blog.backend.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.practice.blog.backend.exception.ResourceNotFoundException;
import com.practice.blog.backend.model.Category;
import com.practice.blog.backend.model.Post;
import com.practice.blog.backend.model.User;
import com.practice.blog.backend.payload.PostDto;
import com.practice.blog.backend.payload.PostResponse;
import com.practice.blog.backend.repo.CategoryRepo;
import com.practice.blog.backend.repo.PostRepo;
import com.practice.blog.backend.repo.UserRepo;
import com.practice.blog.backend.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto post, Integer userId, Integer categoryId) {
		User user=this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		Category category=this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));
		System.out.println(post);
		Post modelPost=this.modelMapper.map(post, Post.class);
		modelPost.setImageName("default.png");
		modelPost.setAddDate(new Date());
		modelPost.setUser(user);
		modelPost.setCategory(category);
		Post savedPost=this.postRepo.save(modelPost);
		
		return this.modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto post, Integer postId) {
		Post modelPost=this.postRepo.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
		modelPost.setContent(post.getContent());
		modelPost.setTitle(post.getTitle());
		modelPost.setAddDate(new Date());
		modelPost.setImageName(post.getImageName());
		Post savedPost=this.postRepo.save(modelPost);
		return this.modelMapper.map(savedPost, PostDto.class);
		
	}

	@Override
	public String deletePost(Integer postId) {
		Post post=this.postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
		String title=post.getTitle();
		this.postRepo.delete(post);
		return "Post with %s  is deleted"+title;
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber,Integer pageSize) {		
		
		Pageable p=PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePosts=this.postRepo.findAll(p);
		List<Post> posts=pagePosts.getContent();		
		List<PostDto> postDtos=posts.stream()
				.map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalPages(pagePosts.getTotalPages());
		postResponse.setTotalElements(pagePosts.getTotalElements());
		postResponse.setLastPage(pagePosts.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer id) {
		Category category=this.categoryRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Category","id",id));
		List<Post> posts=this.postRepo.findByCategory(category);
		List<PostDto> postDtos=posts.stream()
				.map(post->this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer id) {
		User user=this.userRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("User","id",id));
		List<Post> posts=this.postRepo.findByUser(user);
		List<PostDto> postDtos=posts.stream()
				.map(post->this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;

	}

	@Override
	public List<PostDto> searhPosts(String keyword) {
		return null;
	}

	

}
