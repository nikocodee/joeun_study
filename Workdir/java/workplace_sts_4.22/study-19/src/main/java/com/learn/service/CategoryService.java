package com.learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.dao.CategoryRepository;
import com.learn.vo.CategoryVO;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<CategoryVO> getAllCategories(){
		List<CategoryVO> result = null;
		result = categoryRepository.findAll();
		return result;
	}
	
	public CategoryVO getCategoryById(String id){
		CategoryVO result = new CategoryVO();
		result = categoryRepository.findById(id).orElse(result);
		return result;
	}
	
	public List<CategoryVO> getCategoryByName(String name){
		List<CategoryVO> result = null;
		result = categoryRepository.findByNameContainingIgnoreCase(name);
		return result;
	}
	
	public CategoryVO getCategoryByParentId(String id){
		CategoryVO result = null;
		result = categoryRepository.findByParentId(id);
		return result;
	}
	
	public List<CategoryVO> getCategoryByParentIdIsNull(){
		List<CategoryVO> result = null;
		result = categoryRepository.findByParentIdIsNull();
		return result;
	}
	
	public List<CategoryVO> getCategoryByParentIdAndDepth(String parentId, int depth){
		List<CategoryVO> result = null;
		result = categoryRepository.findByParentIdAndDepth(parentId, depth);
		return result;
	}
	
	public CategoryVO createCategory(CategoryVO category) throws Exception {
		CategoryVO result = new CategoryVO();
		if(category.getName() == null || "".equals(category.getName().trim())) {
			throw new Exception("Name이 비어있습니다.");
		}
		if(category.getParentId() == null || "".equals(category.getParentId().trim())) {
			category.setParentId(null);
			category.setDepth(0);
		}else {
			CategoryVO parentCategory = categoryRepository.findById(category.getParentId()).orElse(result);
			if(parentCategory.getId() == null) {
//				category.setParentId(null);
//				category.setDepth(0);
				throw new Exception("Parent객체가 비어있습니다.");
			}else {
				category.setDepth(parentCategory.getDepth()+1);
			}
		}
		result = categoryRepository.save(category);
		return result;
	}
	
}
