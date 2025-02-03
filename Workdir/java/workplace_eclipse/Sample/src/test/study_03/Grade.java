package test.study_03;

public class Grade {
	private final Course course;
	private final double score;

	public Grade(Course course, double score) {
		this.course = course;
		this.score = score;
	}

	public Course getCourse() {
		return course;
	}

	public double getScore() {
		return score;
	}

	@Override
	public String toString() {
		return "Grade [course=" + course + ", score=" + score + "]";
	}
}
