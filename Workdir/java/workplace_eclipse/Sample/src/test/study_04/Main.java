package test.study_04;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		Library library = new Library();

		// 도서 추가
		// new 객체 생성 변수에 담지 않은 경우
		// 메서드에 직접 전달
		// 일반적으로 한번만 사용하고 더 이상 참조되지 않을 것을 예상하는 경우
		library.addBook(new Book("Effective Java", "Joshua Bloch", List.of("Programming", "Java")));
		library.addBook(new Book("Clean Code", "Robert C. Martin", List.of("Programming", "Software Engineering")));
		library.addBook(new Book("Harry Potter", "J.K. Rowling", List.of("Fantasy", "Adventure")));

		// 전체 도서 목록 출력
		System.out.println("All Books:");
		library.getBooks().forEach(System.out::println);

		// 특정 장르의 도서 검색
		System.out.println("\nProgramming Books:");
		library.getBooksByGenre("Programming").forEach(System.out::println);

		// 제목으로 도서 검색
		library.findBookByTitle("lean COD").ifPresent(book -> System.out.println("\nFound Book: " + book));
		
		// 도서 제목순 정렬 후 출력
		System.out.println("\nSorted Books by Title:");
		library.sortBooksByTitle().forEach(System.out::println);
		
		// Alice 사용자 생성 및 도서 대출/반납
		User user = new User("Alice");
		user.borrowBook(library.findBookByTitle("effective").orElse(null));
		user.borrowBook(library.findBookByTitle("harry").orElse(null));
		
		// 대출 도서 목록 확인
		System.out.println("\nAlice's Borrowed Books:");
		user.getBorrowedBooks().forEach(System.out::println);
		
		// Tom 사용자 생성
		System.out.println("\nTom's Borrowed Books:");
		User tom = new User("Tom");
		tom.borrowBook(library.findBookByTitle("harry").orElse(null));
		
//		tom.getBorrowedBooks().forEach(System.out::println);
		
		// 도서 반납
		user.returnBook(library.findBookByTitle("effective").orElse(null));
		
		// 반납 후 대출 도서 목록 확인
		System.out.println("\nAlice's Borrowed Books after returning:");
		user.getBorrowedBooks().forEach(System.out::println);
	}
}
