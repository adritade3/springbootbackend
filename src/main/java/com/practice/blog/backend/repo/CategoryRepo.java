package com.practice.blog.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.blog.backend.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
