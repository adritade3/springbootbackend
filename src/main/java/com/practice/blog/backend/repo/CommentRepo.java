package com.practice.blog.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.blog.backend.model.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
