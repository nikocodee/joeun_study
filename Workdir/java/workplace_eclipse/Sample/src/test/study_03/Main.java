package test.study_03;

public class Main {

	public static void main(String[] args) {
		GradeService gradeService = new GradeService();
		
		// 과목 생성
		Course math = new Course(1, "Mathatics", 3);
		Course science = new Course(2, "Science", 4);
		Course history = new Course(3, "History", 2);
		
		// 학생 생성
		Student[] student = new Student[2];
//		Student hong = new Student(1, "홍길동");
//		Student bob = new Student(2, "bob");
		student[0] = new Student(1, "홍길동");
		student[1] = new Student(2, "bob");
		
		// 학생 추가
		gradeService.addStudent(student[0]);
		gradeService.addStudent(student[1]);
		
		// 과목추가
		gradeService.addGrade(1, math, 85);
		gradeService.addGrade(1, history, 80);
		gradeService.addGrade(2, history, 85);
		gradeService.addGrade(2, math, 90);
		gradeService.addGrade(2, science, 95);
		
		// 전체 학생 조회
		System.out.println("\nAll Students:");
//		gradeService.getAllStudents().forEach(System.out::println);
		gradeService.getAllStudents().forEach(std -> System.out.println(std)); // 람다 표현식

		// 학생별 평균 성적 출력
		System.out.println("\nStudent Averages: ");
		System.out.println("Student ID 1 : " + gradeService.getStudentAverage(1));
		System.out.println("Student ID 2 : " + gradeService.getStudentAverage(2));
		
		// 전체 평균 성적 출력
		System.out.println("\nOverall Average: "+gradeService.getOverallAverage());
		System.out.println("\nStudents Sorted: ");
		gradeService.sortStudentsByAverage().forEach(System.out::println);
		System.out.println("\nStudents Sorted: ");
		gradeService.sortStudentsByAverage().forEach(std -> System.out.println(gradeService.getStudentAverage(std.getId())));
		System.out.println("\nStudents Sorted: ");
		gradeService.sortStudentsByAverage().forEach(std -> System.out.println(gradeService.getStudentMean(std)));
	}

}
