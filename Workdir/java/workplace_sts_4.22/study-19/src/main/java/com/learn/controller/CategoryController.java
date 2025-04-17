package com.learn.controller;

import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.service.CategoryService;
import com.learn.vo.CategoryVO;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public List<CategoryVO> getAllCategories(){
		List<CategoryVO> result = null;
		result = categoryService.getAllCategories();
		return result;
	}
	
	@GetMapping("/{id}")
	public CategoryVO getCategoryById(@PathVariable String id){
		CategoryVO result = null;
		result = categoryService.getCategoryById(id);
		return result;
	}
	
	//@RequestBody JSON으로 받음
	@PostMapping
	public ResponseEntity<CategoryVO> createCategory(@RequestBody CategoryVO category) {
		ResponseEntity<CategoryVO> result = null;
		try {
			CategoryVO res = categoryService.createCategory(category);
			result = ResponseEntity.ok(res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.SC_NOT_ACCEPTABLE).build();
		}
		return result;
	}
	
	@GetMapping("/{id}/{depth}")
	public List<CategoryVO> getCategoryByParentIdAndDepth(@PathVariable String id, @PathVariable int depth){
		List<CategoryVO> result = null;
		result = categoryService.getCategoryByParentIdAndDepth(id, depth);
		return result;
	}
	
	@PostMapping("/update")
	public CategoryVO updateCategory(@RequestBody CategoryVO category){
		CategoryVO result = null;
		result = categoryService.updateCategory(category);
		return result;
	}
	
	@GetMapping("/delete/{id}")
	public CategoryVO deleteCategory(@PathVariable String id){
		CategoryVO result = null;
		result = categoryService.deleteCategory(id);
		return result;
	}
}
