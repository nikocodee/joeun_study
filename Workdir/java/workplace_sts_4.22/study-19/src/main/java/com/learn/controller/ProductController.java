package com.learn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.dao.CategoryRepository;
import com.learn.service.ProductService;
import com.learn.vo.CategoryVO;
import com.learn.vo.ProductVO;

//리액트 사용하면 controller사용 안하고 api로서만 데이터만 제공
@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryRepository categoryRepository;

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
//	public List<ProductVO> getAllProducts(@RequestBody HashMap<String, Object> form) {
	public List<ProductVO> getAllProducts() {
		// 클래서처럼 모아서 보려고 임시로 Map 사용
		// 순서 상관 없음 HashMap, 순서 상관 있으면 LinkedHashMap
//		List<Map<String, Object>> result = new ArrayList<>();
//		Map<String, Object> data1 = new HashMap<>();
//		data1.put("id", 1);
//		data1.put("name", "Laptop");
//		data1.put("price", 1200);
//		
//		Map<String, Object> data2 = new HashMap<>();
//		data2.put("id", 2);
//		data2.put("name", "Phone");
//		data2.put("price", 800);
//		
//		Map<String, Object> data3 = new HashMap<>();
//		data3.put("id", 3);
//		data3.put("name", "Tablet");
//		data3.put("price", 400);
//		
//		result.add(data1);
//		result.add(data2);
//		result.add(data3);

		List<ProductVO> result = null;
		result = productService.getAllProducts();

		return result;
	}

	@GetMapping("/cat/{categoryId}")
	public List<CategoryVO> getProductByCategory(@PathVariable String categoryId) {
//	public List<ProductVO> getAllProducts() {
//		Map<String, Object> result = new HashMap<>();
//		result.put("id", category);
//		result.put("name", "Laptop");
//		result.put("price", 1200);
//		result.put("description", "description test");
//		result.put("imageUrl", "/img/1234");

		List<CategoryVO> result = null;
		result = categoryRepository.findByParentId(categoryId);

		return result;
	}

	@GetMapping("/{id}")
	public ProductVO getProductDetail(@PathVariable String id) {
		ProductVO result = null;
		result = productService.getProductDetail(id);
		return result;
	}

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
