package test.study_06;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post {
	private final int id;
	private final String title; // 수정할꺼면 final 제거
	private final String content;
	private final User author;
	// tags, comments > 시작점 주소는 안 바뀌고 add는 가능
	// 다름 사람꺼로 덮어씌어지지 않기 위해
	// 일부 데이터만 불러오기 위해서 나중에 paging 처리 고려
	private final List<String> tags;
	private final List<Comment> comments;
	private LocalDateTime createdAt;
	private int createUserId;
	private LocalDateTime updateAt;
	private int updateUserId;
	private String useYn; // deleteYn; default y

	public Post(int id, String title, String content, User author, String useYn) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author;
		this.tags = new ArrayList<>();
		this.comments = new ArrayList<>();
		this.useYn = useYn;
	}

	// final이기 때문에 변경 못해서 getter만 사용
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public User getAuthor() {
		return author;
	}

	public List<String> getTags() {
		return tags;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public int getCreateUserId() {
		return createUserId;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public int getUpdateUserId() {
		return updateUserId;
	}

	public String getUseYn() {
		return useYn;
	}

	public void addComment(Comment comment) {
		comments.add(comment);
		System.out.println(comment.getAuthor().getName() + " 댓글 등록 완료");
	}

	public void addTag(String tag) {
		tags.add(tag);
		System.out.println("태그 : [" + tag + "] 등록 완료");
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", content=" + content + ", author=" + author + ", tags=" + tags
				+ ", comments=" + comments + ", createdAt=" + createdAt + ", createUserId=" + createUserId
				+ ", updateAt=" + updateAt + ", updateUserId=" + updateUserId + ", useYn=" + useYn + "]";
	}

}
