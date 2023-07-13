package com.practice.blog.backend.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.blog.backend.exception.ResourceNotFoundException;
import com.practice.blog.backend.model.Comment;
import com.practice.blog.backend.model.Post;
import com.practice.blog.backend.payload.CommentDto;
import com.practice.blog.backend.repo.CommentRepo;
import com.practice.blog.backend.repo.PostRepo;
import com.practice.blog.backend.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;	
	@Autowired
	private CommentRepo commentRepo;	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CommentDto createComment(CommentDto comment, Integer postId) {
		Post post=this.postRepo.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("Post ","post id",postId));
		Comment modelComment=this.modelMapper.map(comment, Comment.class);
		modelComment.setUser(post.getUser());
		modelComment.setPost(post);
		Comment savedComment=this.commentRepo.save(modelComment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment=this.commentRepo.findById(commentId)
				.orElseThrow(()->new ResourceNotFoundException("Comment","comment Id",commentId));
		this.commentRepo.delete(comment);
		
		
	}

	@Override
	public CommentDto updateComment(CommentDto comment, Integer postId) {
			
		return null;
	}

}
