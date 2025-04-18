package com.learn.vo;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
public class CategoryVO {
	@Id
	private String id;
	private String name;
	private String parentId; // null이면 대분류
	private int depth; // 0: 대분류, 1: 소분류
	private String useYn;
//	private Boolean isActive;
	private String createdId;
	private Timestamp createdAt;
	private String updatedId;
	private Timestamp updatedAt;

	public CategoryVO() {
	}

	public CategoryVO(String name, String parentId, int depth) {
		this.name = name;
		this.parentId = parentId;
		this.depth = depth;
	}
	
	public boolean isPresent() {
		boolean result = false;
		result = ((getId() != null) && (!"".equals(getId().trim())));
		return result;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
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

	@Override
	public String toString() {
		return "CategoryVO [id=" + id + ", name=" + name + ", parentId=" + parentId + ", depth=" + depth + ", useYn="
				+ useYn + ", createdId=" + createdId + ", createdAt=" + createdAt + ", updatedId=" + updatedId
				+ ", updatedAt=" + updatedAt + "]";
	}

	

}
