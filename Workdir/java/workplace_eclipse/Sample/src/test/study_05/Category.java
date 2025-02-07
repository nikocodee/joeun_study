package test.study_05;

import java.util.ArrayList;
import java.util.List;

public class Category {
	private int id;
	private String name;
	private List<Product> products;

	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.products = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void addProduct(Product product) {
		products.add(product);
		System.out.println("카테고리 : " + this.name + " | 상품등록 완료 : " + product.getName());
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", products=" + products.size() + "]";
	}
}
