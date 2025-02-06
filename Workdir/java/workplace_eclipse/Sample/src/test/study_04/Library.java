package test.study_04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Library {
	List<Book> books = new ArrayList<>();

	// 초기화할 값이 없으니까 생성자(매개변수 있는)는 안 만들었음

	// 전체 도서 목록 반환
	public List<Book> getBooks() {
		return books;
	}

	// 도서 추가
	public void addBook(Book book) {
		books.add(book);
		System.out.println("등록 : " + books.toString());
	}
	
	// 특정 장르의 도서 목록 반환
	public List<Book> getBooksByGenre(String genre){
		List<Book> result = null;
		// map, filter(조건을 걸어서 참일때만 통과), reduce(여러개 넣어서 하나로 통합하는거)
		result = books.stream()
				.filter(book -> book.getGenres().contains(genre))
				.collect(Collectors.toList());
		return result;
	}
	
	// 도서 제목으로 검색
	// Optional은 NullPointerException을 방지
	public Optional<Book> findBookByTitle(String title) {
		Optional<Book> result = null;
		result = books.stream()
//				.filter(book -> book.getTitle().equalsIgnoreCase(title))
				//toLowerCase 모든 문자를 소문자로 변환
				//contains 제목의 일부가 포함된 경우
				//equalsIgnoreCase 대소문자 무시, 정확히 제목이 일치하는 것 찾음
				.filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase())) 
				.findFirst(); //첫번째 요소만 반환
		return result;
	}
	
	public List<Book> sortBooksByTitle(){
		List<Book> result = null;
		result = books.stream()
				.sorted(Comparator.comparing(Book::getTitle))
				.collect(Collectors.toList());
		return result;
	}

	@Override
	public String toString() {
		return "Library [books=" + books + "]";
	}
}
