package com.learn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.learn.vo.ProductVO;

@Mapper
public interface ProductMapper {
	List<ProductVO> getAllProducts();
}
