package exam.study_01;

public class GeneralStudent extends Student {
	private int[] scores;

	public GeneralStudent(String name, int studentId, int[] scores) {
		super(name, studentId);
		this.scores = scores;
	}

	@Override
	double calcMean() {
		double result = 0.0;
		double total = 0;
		// 원소는 scores면 단수로 써주는게 좋음
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
		String grade = "F";
		double meanScore = calcMean();
		if (meanScore >= 90) {
			grade = "A";
		} else if (meanScore >= 80) {
			grade = "B";
		} else if (meanScore >= 70) {
			grade = "C";
		} else if (meanScore >= 60) {
			grade = "D";
		}
		return result;
	}

}
