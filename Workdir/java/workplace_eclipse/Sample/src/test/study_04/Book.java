package test.study_04;

import java.util.List;

public class Book {
	private String title;
	private String author;
	private List<String> genres;
	private boolean isAvailable;


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

	public Book(String title, String author, List<String> genres, boolean isAvailable) {
		this.title = title;
		this.author = author;
		this.genres = genres;
		this.isAvailable = true;
	}
	
	public boolean borrowBook() {
		isAvailable = !isAvailable;
		return isAvailable;
	}
	
	public boolean returnBook() {
		isAvailable = !isAvailable;
		return isAvailable;
	}
	
	public void updateBook() {
		isAvailable = !isAvailable;
	}
	
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", genres=" + genres + ", isAvailable=" + isAvailable
				+ "]";
	}
}
