package com.learn.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.dao.CategoryRepository;
import com.learn.service.ProductService;
import com.learn.vo.CategoryVO;
import com.learn.vo.ProductVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@PostMapping
	public String createProduct(@RequestBody ProductVO product) {
		String result = "N";
		product.setCreatedId("Test");// 세션에 기록된 로그인 사용자 ID
		int resultCnt = productService.createProduct(product);
		if (resultCnt > 0) {
			result = "Y";
		}
		return result;
	}

	@GetMapping
	public List<ProductVO> getAllProducts() {
		List<ProductVO> result = null;
		result = productService.getAllProducts();
		return result;
	}

	@GetMapping("/cat/{category}")
	public List<ProductVO> getProductByCategory(@PathVariable String category) {
		List<ProductVO> result = null;
		result = productService.getProductByCategory(category);

		return result;
	}

	@GetMapping("/{id}")
	public ProductVO getProductDetail(@PathVariable String id) {
		ProductVO result = null;
		result = productService.getProductDetail(id);
		return result;
	}

	@GetMapping("/search")
	public List<ProductVO> searchProduct(String keyword) {
		List<ProductVO> result = null;
		result = productService.searchProduct(keyword);
		return result;
	}

	@PostMapping("/update")
	public String updateProduct(@RequestBody ProductVO updateInfo) {
		String result = "N";
		int rstCnt = productService.updateProduct(updateInfo);
		if (rstCnt > 0) {
			result = "Update";
		}
		return result;
	}

	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable String id) {
		String result = "N";
		int rstCnt = productService.deleteProduct(id);
		if (rstCnt > 0) {
			result = "Delete";
		}
		return result;
	}

}
