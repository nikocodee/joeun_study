package com.learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.learn.mapper.ProductMapper;
import com.learn.vo.ProductVO;

@Service
public class ProductService {
	@Autowired
	private ProductMapper productMapper;
	
	public int createProduct(ProductVO product){
		int result = 0;
		result = productMapper.createProduct(product);
		return result;
	}
	
	public List<ProductVO> getAllProducts(){
		List<ProductVO> result = null;
		result = productMapper.getAllProducts();
		return result;
	}
	
	public ProductVO getProductDetail(String id) {
		ProductVO result = null;
		result = productMapper.getProductDetail(id);
		return result;
	}
	
	public List<ProductVO> searchProduct(String keyword){
		List<ProductVO> result = null;
		result = productMapper.searchProduct(keyword);
		return result;
	}
	
	public List<ProductVO> getProductByCategory(String category){
		List<ProductVO> result = null;
		result = productMapper.getProductByCategory(category);
		return result;
	}
	
	public int updateProduct(ProductVO product){
		int result = 0;
		result = productMapper.updateProduct(product);
		return result;
	}
	
	public int deleteProduct(String id){
		int result = 0;
		result = productMapper.deleteProduct(id);
		return result;
	}
}
