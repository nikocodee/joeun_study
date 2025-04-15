package com.learn.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.learn.vo.CategoryVO;

@Repository
public interface CategoryRepository extends MongoRepository<CategoryVO, String>{
	List<CategoryVO> findByNameContainingIgnoreCase(String name);
	
	List<CategoryVO> findByName(String name);
	
	//계층형 구조를 위한 쿼리 메서드
	List<CategoryVO> findByParentIdIsNull();//대분류만 조회
	
	CategoryVO findByParentId(String parentId); //특정 부모 카테고리의 하위 카테고리
	
	List<CategoryVO> findByDepth(int depth); //특정 깊이의 카테고리 조회
	
	List<CategoryVO> findByParentIdAndDepth(String parentId, int depth); //특정 깊이의 카테고리 조회
}
