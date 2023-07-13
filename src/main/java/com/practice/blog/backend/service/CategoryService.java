package com.practice.blog.backend.service;

import java.util.List;

import com.practice.blog.backend.model.Category;
import com.practice.blog.backend.payload.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto category);
	CategoryDto updateCategory(CategoryDto category, Integer id);
	CategoryDto getCategory(Integer id);
	String deleteCategory(Integer id);
	List<CategoryDto> getAllCategories();
}
