package test.study_06;

public class Main {

	public static void main(String[] args) {
		BlogService blogService = new BlogService();
		
		//사용자 생성
		User user1 = new User(1, "홍길동");
		User user2 = new User(2, "박철수");
		
		//게시글 추가
		blogService.addPost("테스트1", "임시 내용1", user1, "Y");
		blogService.addPost("테스트2", "임시 내용2", user2, "Y");
		blogService.addPost("테스트3", "임시 내용3", user2, "Y");
		
		System.out.println("***** 전체 목록 *****");
		blogService.getAllPosts().forEach(post -> System.out.println(post.toString()));
		
		//댓글 추가
		blogService.addComment("댓글2", user2, "Y", blogService.findPostById(2).orElse(null));
		
		System.out.println("***** 전체 목록 *****");
		blogService.getAllPosts().forEach(post -> System.out.println(post.toString()));
	}

}
