package com.blog.api.services;

import java.util.List;

import com.blog.api.entities.Category;
import com.blog.api.payloads.CategoryDto;

public interface CategoryService {

	/// create
	CategoryDto createCategory(CategoryDto categoryDto);

	// update
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	// delete
	public void deleteCategory(Integer CategoryId);

	// get
	public CategoryDto getCategory(Integer categoryId);

	// getAll
	List<CategoryDto> getAllCategories();

	// create Allcategories
	List<Category> insertAllCategories(List<Category> catList);
}
