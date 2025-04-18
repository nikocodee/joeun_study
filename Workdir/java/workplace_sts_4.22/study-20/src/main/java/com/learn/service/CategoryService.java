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
	
	public List<CategoryVO> getCategoryByParentId(String id){
		List<CategoryVO> result = null;
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
	
	public CategoryVO createCategory(CategoryVO category) throws Exception{
		CategoryVO result = new CategoryVO();
//		if(category.getName() == null || "".equals(category.getName().trim())) {
//			throw new Exception("Name이 비어있습니다.");
//		}
//		if(!category.isPresent()) {
//			category.setParentId(null);
//			category.setDepth(0);
//		}else {
//			CategoryVO parentCategory = categoryRepository.findById(category.getParentId()).orElse(result);
//			if(parentCategory.getId()==null) {
////				category.setParentId(null);
////				category.setDepth(0);
//				throw new Exception("Parent객체가 비어있습니다.");
//			}else {
//				category.setDepth(parentCategory.getDepth()+1);
//			}
//		}
		result = categoryRepository.save(category);
		return result;
	}
	
	public CategoryVO updateCategory(CategoryVO category){
		CategoryVO result = null;
		CategoryVO res = categoryRepository.findById(category.getId()).orElse(new CategoryVO());
		CategoryVO parentCategory = categoryRepository.findById(category.getParentId()).orElse(new CategoryVO());
		if(!res.isPresent()) {
			return res;
		}else {
			res.setName(category.getName());
//			res.setParentId(category.getParentId());
//			res.setDepth(parentCategory.getDepth()+1);
//			updateChildrenCategoryDepth(res);
		}
		result = categoryRepository.save(res);
		return result;
	}
	
	public void updateChildrenCategoryDepth(CategoryVO parent){
		List<CategoryVO> children = categoryRepository.findByParentIdAndDepth(parent.getId(), parent.getDepth()+1);
		if(!children.isEmpty()) {
			for(CategoryVO child:children) {
				child.setDepth(parent.getDepth()+1);
				categoryRepository.save(child);
				updateChildrenCategoryDepth(child);
			}
		}
	}
	
	public String deleteCategory(String id){
//		CategoryVO result = new CategoryVO();
//		CategoryVO res = categoryRepository.findById(id).orElse(result);
//		List<CategoryVO> children = categoryRepository.findByParentId(id);
//		if(children.isEmpty()) {  // 자식객체가 없는 경우에만 플래그 수정
//			if(!res.isPresent()) {
//				return res;
//			}else {
//				res.setUseYn("N");
//			}
//			result = categoryRepository.save(res);
//		}
		String result = "Delete";
		try {
			categoryRepository.deleteById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			result = "Error";
		}
		
		return result;
	}
	
	
}
