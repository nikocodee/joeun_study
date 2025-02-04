package test.study_03;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GradeService {
	private final Map<Integer, Student> students = new HashMap<>();

	public void addStudent(Student student) {
		students.put(student.getId(), student);
	}

	public void addGrade(int studentId, Course course, double score){
		students.get(studentId).addGrade(new Grade(course, score));
		System.out.println(students.get(studentId).toString());
	}
	
	public List<Student> getAllStudents(){
		return new ArrayList<>(students.values());
	}
	
	public List<Grade> getGradeByCourse(String courseName){
		return students.values().stream()
				.flatMap(student -> student.getGrades().stream())
				.filter(grade -> grade.getCourse().getName().equalsIgnoreCase(courseName))
				.collect(Collectors.toList());
	}
	
	public double getStudentAverage(int studentId) {
		double meanScore = students.get(studentId).getGrades().stream()
							.mapToDouble(Grade::getScore) // 메서드 참조는 람다 표현식을 더욱 간결하게 작성할 수 있도록 도와줌
//							.mapToDouble(grade -> grade.getScore()) // 람다식
							.average() // 평균값을 계산
							.orElse(0.0); // 없으면 기본값 0.0 반환
		return meanScore;
	}
	
	public double getStudentMean(Student student) {
		return student.getGrades().stream()
				.mapToDouble(Grade::getScore)
				.average()
				.orElse(0.0);
	}
	
	public double getOverallAverage() {
		double result = students.values().stream() // 모든 student 리스트
						.flatMap(student -> student.getGrades().stream()) // 원소별로 가져와서 스트림으로 변환
						.mapToDouble(Grade::getScore)
						.average()
						.orElse(0.0);
		return result;
	}
	
	public List<Student> sortStudentsByAverage(){
		List<Student> result = students.values().stream()
								// 원소 student로 넘겨줘야함 int는 안됨
//								.sorted(Comparator.comparingDouble(this::getStudentAverage).reversed())
								.sorted(Comparator.comparingDouble(this::getStudentMean).reversed())
								.collect(Collectors.toList());
		return result;
	}
}
