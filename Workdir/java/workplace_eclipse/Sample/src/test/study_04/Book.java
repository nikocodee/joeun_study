package test.study_04;

import java.util.List;

public class Book {
	private String title; //도서 제목
	private String author; //저자
	private List<String> genres; //장르
	private boolean isAvailable; //대출 가능 여부


	public Book(String title, String author, List<String> genres) {
		this.title = title;
		this.author = author;
		this.genres = genres;
		this.isAvailable = true; // 기본적으로 대출 가능 상태
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public List<String> getGenres() {
		return genres;
	}

	public boolean isIsAvailable() {
		return isAvailable;
	}

	
	public void borrowBook() {
		isAvailable = false;
	}
	
	public void returnBook() {
		isAvailable = true;
	}
	
//	public void updateBook() {
//		isAvailable = !isAvailable;
//	}
	
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", genres=" + genres + ", isAvailable=" + isAvailable
				+ "]";
	}
}
