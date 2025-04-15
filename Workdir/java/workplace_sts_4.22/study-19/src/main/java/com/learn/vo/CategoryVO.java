package com.learn.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
public class CategoryVO {
	@Id
	private String id;
	private String name;
	private String parentId; // null이면 대분류
	private int depth; // 0: 대분류, 1:소분류

	public CategoryVO() {
	}

	public CategoryVO(String name, String parentId, int depth) {
		this.name = name;
		this.parentId = parentId;
		this.depth = depth;
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

	@Override
	public String toString() {
		return "CatetoryVO [id=" + id + ", name=" + name + ", parentId=" + parentId + ", depth=" + depth + "]";
	}

}
