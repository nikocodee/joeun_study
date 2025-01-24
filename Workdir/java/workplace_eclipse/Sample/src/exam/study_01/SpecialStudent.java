package exam.study_01;

import java.util.Arrays;

public class SpecialStudent extends Student implements ScholarshipInfo {
	private boolean scholarship;
	private int[] scores;
	// getter, setter는 안 알려주고 싶어서 안함

	public SpecialStudent(String name, int studentId, boolean scholarship, int[] scores) {
		super(name, studentId);
		this.scholarship = scholarship;
		this.scores = scores;
	}

	@Override
	public boolean checkScholar() {
		boolean result = false;
		result = scholarship;
		return result;
	}

	@Override
	double calcMean() {
		double result = 0;
		int total = 0;
		for (int score : scores) {
			total += score;
		}
		double meanTotal = total / scores.length;
		result = meanTotal;
		return result;
	}

	@Override
	String getGrade() {
		String result = "-";
		String grade = "";
		double meanScore = calcMean();
		if (meanScore >= 90) {
			grade = "A";
		} else if (meanScore >= 80) {
			grade = "B";
		} else if (meanScore >= 70) {
			grade = "C";
		} else {
			grade = "F";
		}
		result = grade;
		return result;
	}

	@Override
	public String toString() {
		return "SpecialStudent [scholarship=" + scholarship + ", scores=" + Arrays.toString(scores)
				+ ", checkScholar()=" + checkScholar() + ", calcMean()=" + calcMean() + ", getGrade()=" + getGrade()
				+ "]";
	}

}
