package com.practice.blog.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.blog.backend.payload.ApiResponse;
import com.practice.blog.backend.payload.CategoryDto;
import com.practice.blog.backend.payload.UserDto;
import com.practice.blog.backend.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createdCategory=this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(createdCategory,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable Integer id ){
		CategoryDto updatedCategory=this.categoryService.updateCategory(categoryDto, id);
		return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCatgory(@PathVariable Integer id){
		CategoryDto fetchCategory=this.categoryService.getCategory(id);
		return new ResponseEntity<>(fetchCategory,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		List<CategoryDto> getAllcategories=this.categoryService.getAllCategories();
		return new ResponseEntity<>(getAllcategories,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Integer id){
		String message=this.categoryService.deleteCategory(id);
		return ResponseEntity.ok(new ApiResponse(message,true));
		
	}

}
