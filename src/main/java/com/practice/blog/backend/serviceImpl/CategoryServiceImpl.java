package com.practice.blog.backend.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.blog.backend.exception.ResourceNotFoundException;
import com.practice.blog.backend.model.Category;
import com.practice.blog.backend.payload.CategoryDto;
import com.practice.blog.backend.repo.CategoryRepo;
import com.practice.blog.backend.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto category) {
		Category modelCategory=this.modelMapper.map(category,Category.class);
		Category createdCategory=this.categoryRepo.save(modelCategory);
		return this.modelMapper.map(createdCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto category, Integer id) {		
		Category fetchCategory=this.categoryRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Category","id",id));
		fetchCategory.setCategoryTitle(category.getCategoryTitle());
		fetchCategory.setCategoryDescription(category.getCategoryDescription());
		Category updatedCategory=this.categoryRepo.save(fetchCategory);
		return this.modelMapper.map(updatedCategory, CategoryDto.class);		
	}

	@Override
	public CategoryDto getCategory(Integer id) {
		Category fetchCategory=this.categoryRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Category","id",id));
		return this.modelMapper.map(fetchCategory, CategoryDto.class);
	}

	@Override
	public String deleteCategory(Integer id) {
		Category fetchCategory=this.categoryRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Category","id",id));
		this.categoryRepo.delete(fetchCategory);
		return "Category deleted with id "+ id;
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories=this.categoryRepo.findAll();
		List<CategoryDto> categoriesDto=categories.stream()
				.map((category)->this.modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
		
		return categoriesDto;
	}

}
