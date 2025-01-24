package exam.study_01;

import java.util.List;
import java.util.Scanner;

public class StudentInfoSystem {
	public static void main(String[] args) {
		// 수도 코드(pseudo code)
		// 학생정보 : 아이디, 이름, 학생, 주소 : 클래스 정의 : Student
		// 일반 학생 : 성적 : GeneralStudent
		// 특기생 : 성적, 장학금 수여여부 : SpecialStudent
		
		// 학생 정보 관리 : 클래스 정리 : StudentManagement
		// 학생정보 입력
		// 학생정보 출력
		// 학생정보 검색
		
		// 메뉴 표기 : 학생정보 입력, 전체 학생정보 출력, 학생정보 검색
//		System.out.println("메뉴표시");
		System.out.println("*** Menu ***");
		System.out.println("1. 학생정보 입력");
		System.out.println("2. 학생정보 출력");
		System.out.println("3. 학생정보 검색");
		System.out.println("4. 종료");
		
//		System.out.println("메뉴선택");
		Scanner sc = new Scanner(System.in);
		int menu = sc.nextInt();
		boolean condLoop = true;
		StudentManagement sm = new StudentManagement();
		List<Student> students = null;
		while (condLoop) {
			switch (menu) {
			case 1:
				sm.addStudent(new GeneralStudent("Alice", 1, new int[]{77, 86, 92}));
				sm.addStudent(new SpecialStudent("Bob", 2, true, new int[]{94, 85}));
				sm.addStudent(new GeneralStudent("Charlie", 3, new int[]{88, 94, 97, 1000}));
				break;
			case 2:
				students = sm.getStudents();
				for (Student student : students) {
					sm.displayInfo(student);
				}
				break;
			case 3:
				String searchKeyword = "ab";
				students = sm.getStudents();
				for (Student student : students) {
					sm.retrieve(searchKeyword, student);
				}
				break;
			case 4:
				// false보다 반전한다고 시키는게 좋음
				condLoop = !condLoop;
				break;
			}
		}
		
//		// 반복 : 메뉴 4 선택 시 종료
//		System.out.println("반복문 : 플래그 처리 / 메뉴 4 선택 시 종료");
//			// 메뉴 1 선택 : 학생정보 입력
//			System.out.println("메뉴 1 선택 : 학생정보 입력");
//				// 학생 배열 생성
//				// 학생 추가
//			// 메뉴 2 선택 : 전체 학생정보 출력
//			System.out.println("메뉴 2 선택 : 전체 학생정보 출력");
//				// 학생 정보 출력 : 전체 학생
//					// 학생 : 아이디, 성명, 평균 점수, 학점
//					// 특기생 : 장학생 지급 여부
//			// 메뉴 3 선택 : 학생정보 검색 : 아이디, 이름, 주소, 장학금 수여여부 // 성적, 학점 결석 여부는
//			System.out.println("메뉴 3 선택 : 학생정보 검색");

			sc.close();
	}
}

