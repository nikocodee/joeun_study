package com.learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.mapper.ProductMapper;
import com.learn.vo.ProductVO;

@Service
public class ProductService {
	@Autowired
	private ProductMapper productMapper;
	
	public List<ProductVO> getAllProducts(){
		List<ProductVO> result = null;
		result = productMapper.getAllProducts();
		return result;
	}
}
