package com.learn.vo;

import java.sql.Timestamp;

public class ProductVO {

	private Long 	id			;
	private String 	name		;
	private Long 	price		;
	private String 	imageUrl	;
	private String 	description	;
	private Long 	stock		;
	private Double 	rating		;
	private String 	specs		;
	private String 	category	;
	private String createdId	;
	private Timestamp createdAt	;
	private String updatedId;
	private Timestamp updatedAt;
	private String useYn;

	public ProductVO() {
	}

	public ProductVO(String name, Long price) {
		this.name = name;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCreatedId() {
		return createdId;
	}

	public void setCreatedId(String createdId) {
		this.createdId = createdId;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedId() {
		return updatedId;
	}

	public void setUpdatedId(String updatedId) {
		this.updatedId = updatedId;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	@Override
	public String toString() {
		return "ProductVO [id=" + id + ", name=" + name + ", price=" + price + ", imageUrl=" + imageUrl
				+ ", description=" + description + ", stock=" + stock + ", rating=" + rating + ", specs=" + specs
				+ ", category=" + category + ", createdId=" + createdId + ", createdAt=" + createdAt + ", updatedId="
				+ updatedId + ", updatedAt=" + updatedAt + ", useYn=" + useYn + "]";
	}


}
