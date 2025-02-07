package test.study_05;

public class Product {
	private final int id;
	private final String name;
	private final double price;
	private final int stock;
	private final Category category;

	public Product(int id, String name, double price, Category category, int stock) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getStock() {
		return stock;
	}

	public Category getCategory() {
		return category;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", stock=" + stock + ", category="
				+ category + "]";
	}

}
