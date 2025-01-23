package test.study_02;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<InfoStudent> studentList = new ArrayList<>();
		studentList.add(new InfoStudent("홍길동", new double[]{89.1, 90.4, 72.7}));
		studentList.add(new InfoStudent("김철수", new double[]{77.6, 32.3}));
		
		for (InfoStudent infoStudent : studentList) {
			System.out.println("학생 : " + infoStudent.getName());
			System.out.println("평균 : " + infoStudent.calcAverage());
			System.out.println("학점 : " + infoStudent.calcGrade());
			System.out.println("=================================");
		}
	}
}
