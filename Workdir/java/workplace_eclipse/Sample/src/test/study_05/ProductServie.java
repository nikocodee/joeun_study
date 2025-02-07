package test.study_05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductServie {
	// 순서대로 관리하고 싶으면 LinkedHashMap
	// HashMap는 순서 보장 못함 (인덱스x)
	// final은 products에 새로운 객체 생성 못하게 함
	private final Map<Integer, Product> products = new HashMap<>();

	public Map<Integer, Product> getProducts() {
		return products;
	}

	// 상품 추가
	public void addProduct(Product product) {
		products.put(product.getId(), product);
		product.getCategory().addProduct(product);
		System.out.println(product.getId() + " 등록 완료");
	}
	
	// 전체 상품 목록 반환
	public List<Product> getAllProducts(){
		List<Product> result = null;
		// Collection이 더 넓어서 List로 > 강제 형변환 불가
//		result = (List<Product>) products.values();
		result = new ArrayList<>(products.values());
		return result;
	}
	
	// 특정 카테고리 상품 목록 반환
	public List<Product> getProductsByCategory(String catName){
		List<Product> result = null;
		result = getAllProducts().stream()
									.filter(product -> product.getCategory().getName().toLowerCase().contains(catName.toLowerCase()))
									.collect(Collectors.toList());
		return result;
	}
	
	// 가격 범위 내 상품 목록 조회
	public List<Product> getProductsByPriceRange(double minPrice, double maxPrice){
		List<Product> result = null;
		// 필수조건을 맨 앞에 두기 > product 데이터가 없을 경우 not null > NPE(null point exception)
		// price 일 경우 price > 0
		result = getAllProducts().stream()
									.filter(product -> product != null && product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
									.collect(Collectors.toList());
		return result;
	}
	
	// 가격순 정렬
	public List<Product> sortProductsByPrice(boolean sortDir){ //sortdir : false(ascending) / true(descending)
		List<Product> result = null;
		result = getAllProducts().stream()
									.sorted(sortDir?Comparator.comparingDouble(Product::getPrice).reversed():Comparator.comparingDouble(Product::getPrice))
//									.sorted(Comparator.comparingDouble(Product::getPrice))
									.collect(Collectors.toList());
		return result;
	}
	
	// 재고 부족 상품 조회
	public List<Product> getLowStockProducts(int threshold){
		List<Product> result = null;
		result = getAllProducts().stream()
									.filter(product -> product != null && product.getStock() < threshold)
									.collect(Collectors.toList());
		return result;
	}
	
	
	@Override
	public String toString() {
		return "ProductServie [products=" + products + "]";
	}

}
