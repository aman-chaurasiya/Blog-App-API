package com.codewithaman.blog.services;

import java.util.List;

import com.codewithaman.blog.payloads.CategoryDto;

public interface CategoryService {

	//create category
	 CategoryDto createCategory(CategoryDto categoryDto);
//update category
	 CategoryDto updateCategoery(CategoryDto categoryDto, Integer categoryId);
// delete category
	 void deleteCategory(Integer categoryId);
//get single category
	 CategoryDto getCategoryById(Integer categoryId);
	//get all category
	List<CategoryDto> getAllCategory();
	
	//delete All categories
	void deleteAllCategory();
}
