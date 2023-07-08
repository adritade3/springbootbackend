package com.practice.blog.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.blog.backend.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
