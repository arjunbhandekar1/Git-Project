package com.blog.api.controller;

import java.util.List;

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

import com.blog.api.entities.Category;
import com.blog.api.payloads.ApiResponse;
import com.blog.api.payloads.CategoryDto;
import com.blog.api.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/allCat")
	public List<Category> saveAllCategories(@RequestBody List<Category> catList) {
		return categoryService.insertAllCategories(catList);
		
	}

	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);

	}

	@PutMapping("/{CatId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,
			@PathVariable("CatId") Integer CatId) {
		CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, CatId);
		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);

	}

	@DeleteMapping
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("CatId") Integer CatId) {
		this.categoryService.deleteCategory(CatId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted", true), HttpStatus.OK);

	}

	@GetMapping("/{CatId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("CatId") Integer CatId) {
		CategoryDto Category = this.categoryService.getCategory(CatId);
		return new ResponseEntity<CategoryDto>(Category, HttpStatus.OK);

	}

	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories() {
		List<CategoryDto> Categories = this.categoryService.getAllCategories();
		return new ResponseEntity<List<CategoryDto>>(Categories, HttpStatus.OK);

	}

}
