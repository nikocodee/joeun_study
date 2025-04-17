package com.learn.vo;

import java.sql.Timestamp;

public class ProductVO {
	// int는 21억까지만 됨
	// 경까지 하는 Long 사용
	private Long id;
	private String name;
	private Long price;
	private String imageUrl;
	private String createdId;
	private Timestamp createdAt;
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

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ProductVO [id=" + id + ", name=" + name + ", price=" + price + ", createdId=" + createdId
				+ ", createdAt=" + createdAt + ", updatedId=" + updatedId + ", updatedAt=" + updatedAt + ", useYn="
				+ useYn + "]";
	}

}
