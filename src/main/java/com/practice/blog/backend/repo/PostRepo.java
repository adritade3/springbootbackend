package com.practice.blog.backend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.blog.backend.model.Category;
import com.practice.blog.backend.model.Post;
import com.practice.blog.backend.model.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	List<Post> findByUser(User user);
	List<Post> findByTitleContaining(String title);
	List<Post> findByCategory(Category category);

}
