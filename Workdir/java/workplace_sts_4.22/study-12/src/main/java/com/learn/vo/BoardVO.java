package com.learn.vo;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BoardVO {
	private Long id;
	private String title;
	private String writer;
	private String content;
	private int views;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy년 MM월 dd일", timezone = "Asia/Seoul")
	private Timestamp createdAt;
	private String createdDisp;
	private String createdId;
	private Timestamp updatedAt;
	private String updatedId;
	private String useYn;
	private String formattedDate;

	public BoardVO() {
	}

	public BoardVO(String title, String writer, String content) {
		this.title = title;
		this.writer = writer;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedId() {
		return createdId;
	}

	public void setCreatedId(String createdId) {
		this.createdId = createdId;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedId() {
		return updatedId;
	}

	public void setUpdatedId(String updatedId) {
		this.updatedId = updatedId;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getCreatedDisp() {
		return createdDisp;
	}

	public void setCreatedDisp(String createdDisp) {
		this.createdDisp = createdDisp;
	}

	@Override
	public String toString() {
		return "BoardVO [id=" + id + ", title=" + title + ", writer=" + writer + ", content=" + content + ", views="
				+ views + ", createdAt=" + createdAt + ", createdDisp=" + createdDisp + ", createdId=" + createdId
				+ ", updatedAt=" + updatedAt + ", updatedId=" + updatedId + ", useYn=" + useYn + "]";
	}

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

}
