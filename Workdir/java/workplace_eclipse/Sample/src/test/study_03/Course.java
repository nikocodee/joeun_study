package test.study_03;

public class Course {
	private int id; // 과목 ID
	private String name; // 과목명
	private int credits; // 과목점수

	public Course(int id, String name, int credits) {
		this.id = id;
		this.name = name;
		this.credits = credits;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getCredits() {
		return credits;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", credits=" + credits + "]";
	}

}
