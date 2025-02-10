package test.study_06;

import java.util.ArrayList;
import java.util.List;

public class User {
	private int id;
	private String name;
	private List<Post> posts;

	public User(int id, String name) {
		this.id = id;
		this.name = name;
		this.posts = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public List<Post> getPosts(){
		return posts;
	}
	
	public void addPost(Post post) {
		posts.add(post);
		System.out.println("사용자 : " + name + " | 게시글 : " + post.getTitle() + "등록 완료");
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}

}
