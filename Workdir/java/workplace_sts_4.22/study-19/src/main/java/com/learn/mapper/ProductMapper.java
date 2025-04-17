package com.learn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.learn.vo.ProductVO;

@Mapper
public interface ProductMapper {
	int createProduct(ProductVO product);
	
	List<ProductVO> getAllProducts();
	
	ProductVO getProductDetail(String id);
	
	List<ProductVO> searchProduct(String keyword);
	
	int updateProduct(ProductVO updateInfo);
	
	int deleteProduct(String id);
}
