package exam.study_01;

import java.util.ArrayList;
import java.util.List;

public class StudentManagement {
	List<Student> students = new ArrayList<>();
	
	List<Student> getStudents(){
		return students;
	}

	// 학생정보 입력
	void addStudent(Student student) {
//		students.add(student); 
		// GeneralStudent, SpecialStudent 개별적으로 바꿔서 넣어줘야함
		
	}

	// 학생정보 출력
	void displayInfo(Student student) {
		String result = "-";
		result = "[학생정보] [성명]" + student.getName() + "| [학번]" + student.getStudentId() + "| [평균]" + student.calcMean()
				+ "| [학점]" + student.getGrade();
		System.out.println(result);
	}

	// 학생정보 검색
	void retrieve(String searchWord, Student student) {
		// 소문자로 변환
		searchWord = searchWord.toLowerCase();
		Student result = null;
		// getStudentId는 정수라서 string으로 변환
		String id = String.valueOf(student.getStudentId());
		// 먼저 실행하는 것부터 괄호
		String studentName = (student.getName()).toLowerCase();
		boolean condContain = id.contains(searchWord);
		boolean condContainName = studentName.contains(searchWord);
		if (condContain || condContainName) {
			result = student;
			displayInfo(result);
		}
	}
}
