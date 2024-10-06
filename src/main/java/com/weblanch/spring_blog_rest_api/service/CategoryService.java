package com.weblanch.spring_blog_rest_api.service;

import com.weblanch.spring_blog_rest_api.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);
    CategoryDto getCategoryByCategoryId(Long categoryId);
    List<CategoryDto> getAllCategories();
    CategoryDto updateCategoryById(CategoryDto categoryDto, Long categoryId);
    String deleteByCategoryId(Long categoryId);
}
