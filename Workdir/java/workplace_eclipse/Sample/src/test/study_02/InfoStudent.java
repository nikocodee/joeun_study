package test.study_02;

import java.util.Arrays;

public class InfoStudent {
	private String name;
	
	public String getName() {
		return name;
	}

	private double scores[];
	
	/**
	 * 생성자 2025.01.23 홍길동 최초작성
	 * @param name 학생명
	 * @param scores 과목별 점수 실수 배열
	 */
	public InfoStudent(String name, double[] scores) {
//		super();
		this.name = name;
		this.scores = scores;
	}

	public double calcAverage() {
		double average = 0.0;
		double sum = 0;
		for (double d : scores) {
			sum += d;
		}
		average = sum/scores.length;
		return average;
	}
	
	public char calcGrade(){
		char grade = 'F';
		double studentAverage = calcAverage();
		if (studentAverage<=100.0) {
			double ceilScore = Math.floor(studentAverage / 10);
			int scoreGrade = (int) ceilScore;
			switch (scoreGrade) {
			case 10:
				grade = 'A';
				break;
			case 9:
				grade = 'A';
				break;
			case 8:
				grade = 'B';
				break;
			case 7:
				grade = 'C';
				break;
			case 6:
				grade = 'D';
				break;
			default:
				grade = 'F';
				break;
			}
		}else {
			grade = '-';
		}
		return grade;
	}

	@Override
	public String toString() {
		return "InfoStudent [name=" + name + ", scores=" + Arrays.toString(scores) + "]";
	}
}
