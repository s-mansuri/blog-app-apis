package com.weblanch.spring_blog_rest_api.controller;

import com.weblanch.spring_blog_rest_api.payload.CategoryDto;
import com.weblanch.spring_blog_rest_api.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto savedCategory = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @GetMapping("{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") Long categoryId){
        CategoryDto categoryDto = categoryService.getCategoryByCategoryId(categoryId);
        return ResponseEntity.ok(categoryDto);
    }
    @GetMapping("/all")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{categoryId}")
    public ResponseEntity<CategoryDto> updateCategoryByCategoryId(@RequestBody CategoryDto categoryDto,
                                                                  @PathVariable("categoryId") Long categoryId){
        return ResponseEntity.ok(categoryService.updateCategoryById(categoryDto, categoryId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{categoryId}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable("categoryId") Long categoryId){
        return ResponseEntity.ok(categoryService.deleteByCategoryId(categoryId));
    }
}
