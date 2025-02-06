package test.study_04;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String name;
	private List<Book> borrowBook;

	public User(String name) {
		this.name = name;
		this.borrowBook = new ArrayList<>();
	}

	public String getName() {
		return name;
	}
	
	// 대여한 책 목록 확인
	public List<Book> getBorrowedBooks() {
		return borrowBook;
	}

	// 도서 대출
	public void borrowBook(Book book) {
		if (book.isIsAvailable()) {
			borrowBook.add(book); // 책 대여
			book.borrowBook(); // 대여 불가 상태로 만들기
			System.out.println("대여성공 : " + book.getTitle());
		} else {
			System.out.println("대여중입니다."+ book.toString());
		}
	}

	// 도서 반납
	public void returnBook(Book book) {
		// remove(Object o)-반환 형태 boolean-list
		if (borrowBook.remove(book)) { // true
			book.returnBook();
			System.out.println("반납 성공 : " + book.getTitle());
		} else {
			System.out.println("책을 확인해주세요.");
		}
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", borrowBook=" + borrowBook + "]";
	}

}
