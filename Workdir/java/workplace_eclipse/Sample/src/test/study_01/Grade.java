package test.study_01;

import java.util.Scanner;

public class Grade {
	public static void main(String[] args) {

		double score = -1;
		String grade = "";
		String gradeA = "A";
		String gradeB = "B";
		String gradeC = "C";
		String gradeD = "D";
		String gradeF = "F";
		// 학점 판단
		boolean condGradeOut;
		boolean condGradeA;
		boolean condGradeB;
		boolean condGradeC;
		boolean condGradeD;

		// 성적 입력
		Scanner scanner = new Scanner(System.in);
		// flag 처리 (기본값은 보통 false)
		boolean flag = false;
		String keyInput = "";
		while (!flag) {
			try {
				keyInput = scanner.nextLine(); // nextLine은 공백과, 개행문자 포함
//				score = Integer.parseInt(keyInput);
				score = Double.parseDouble(keyInput);
				condGradeOut = (score > 100) || (score < 0);
				condGradeA = score >= 90;
				condGradeB = score >= 80;
				condGradeC = score >= 70;
				condGradeD = score >= 60;
				if(condGradeOut) {
					System.out.println("이상한 점수??? : "+ keyInput);
					continue;
				}
				grade = (condGradeA) ? (gradeA)
						: (condGradeB) ? (gradeB) : (condGradeC) ? (gradeC) : (condGradeD) ? (gradeD) : (gradeF);
				System.out.println("정수 : [" + score + "]");
				// 학점 출력
				System.out.println("학점 : " + grade);
			} catch (Exception e) {
				System.out.println("입력 : [" + keyInput + "]");
				System.out.println("잘못된 입력 > 종료");
				// flag값을 반전 시킴(종료시키기 위해)
				flag = !flag;
			}
		}
		scanner.close();
	}
}
