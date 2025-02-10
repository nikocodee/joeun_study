package test.study_06;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

//회사의 알고리즘이 들어감
public class BlogService {
	private final Map<Integer, Post> posts = new HashMap<>();
	private int postIdCounter = 1;
	private int commentIdCounter = 1;

	public Map<Integer, Post> getPosts() {
		return posts;
	}

	// 게시글 추가
	public void addPost(String title, String content, User author, String useYn) {
		Post post = new Post(postIdCounter++, title, content, author, useYn);
		posts.put(post.getId(), post);
		author.addPost(post);
		System.out.println(post.getId()+"블로그 등록 완료");
	}
	
	// 게시글 목록 반환
	public List<Post> getAllPosts(){
		List<Post> result = null;
		result = posts.values().stream()
								.collect(Collectors.toList());
		return result;
	}

	// 특정 태그를 포함한 게시글 필터링
	public List<Post> getPostsByTag(String tag){
		List<Post> result = null;
		result = posts.values().stream()
								.filter(post -> post.getTags().contains(tag))
								.collect(Collectors.toList());
		return result;
	}
	
	// 게시글 ID로 검색
	// 코드의 가독성을 높이고 NullPointerException(NPE)을 방지
	// ptional.ofNullable()는 입력 값이 null일 수 있음을 허용하고, null일 경우 빈 Optional 객체를 반환
	public Optional<Post> findPostById(int id) {
		Optional<Post> result = null;
		result = Optional.ofNullable(posts.get(id));
		return result;
	}
	
	// 게시글 제목순 정렬
	public List<Post> sortPostsByTitle(){
		List<Post> result = null;
		result = posts.values().stream()
								.sorted(Comparator.comparing(Post::getTitle))
								.collect(Collectors.toList());
		return result;
	}
	
	// 댓글 추가
	public void addComment(String content, User author, String useYn, Post post) {
		Comment comment = new Comment(commentIdCounter++, content, author, useYn);
		post.addComment(comment);
		System.out.println(comment.getId()+"댓글 등록 완료 ");
	}
	
	@Override
	public String toString() {
		return "BlogService [posts=" + posts + "]";
	}

}
