package test.study_05;

public class Main {
	public static void main(String[] args) {
		// 카테고리 생성
		Category electronics = new Category(1, "Electronics");
		Category fashion = new Category(2, "Fashion");

		// 상품 추가
		Product product1 = new Product(1, "Laptop", 1200.00, electronics, 10);
		Product product2 = new Product(2, "Smartphone", 800.00, electronics, 5);
		Product product3 = new Product(3, "T-shirt", 20.00, fashion, 50);
		Product product4 = new Product(4, "Headphones", 100.00, electronics, 15);
		
		ProductServie productService = new ProductServie();
		
		productService.addProduct(product1);
		productService.addProduct(product2);
		productService.addProduct(product3);
		productService.addProduct(product4);
		
		System.out.println("\nAll Products:");
		productService.getAllProducts().forEach(System.out::println);

        // 특정 카테고리 상품 조회
        System.out.println("\nElectronics Category Products:");
        productService.getProductsByCategory("Electronics").forEach(System.out::println);
        
        // 가격 범위 내 상품 조회
        System.out.println("\nProducts between $50 and $1000:");
        productService.getProductsByPriceRange(50, 1000).forEach(System.out::println);
        
        // 가격순 정렬 후 조회
        System.out.println("\nSorted Products by Price: 내림차순");
        productService.sortProductsByPrice(true).forEach(System.out::println);
        
        // 가격순 정렬 후 조회
        System.out.println("\nSorted Products by Price: 오름차순");
        productService.sortProductsByPrice(false).forEach(System.out::println);
        
        // 재고 부족 상품 조회
        System.out.println("\nLow Stock Products (Below 10 items):");
        productService.getLowStockProducts(10).forEach(System.out::println);
	}
}
