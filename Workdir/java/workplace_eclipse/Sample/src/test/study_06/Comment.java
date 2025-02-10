package test.study_06;

import java.time.LocalDateTime;

public class Comment {
	private final int id;
	private final String content;
	private final User author;
	private LocalDateTime createdAt;
	private int createUserId;
	private LocalDateTime updateAt;
	private int updateUserId;
	private String useYn; // deleteYn; default y

	public Comment(int id, String content, User author, String useYn) {
		this.id = id;
		this.content = content;
		this.author = author;
		this.useYn = useYn;
		this.createdAt = LocalDateTime.now();
	}

	public int getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public User getAuthor() {
		return author;
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

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", author=" + author + ", createdAt=" + createdAt
				+ ", createUserId=" + createUserId + ", updateAt=" + updateAt + ", updateUserId=" + updateUserId
				+ ", useYn=" + useYn + "]";
	}
}
