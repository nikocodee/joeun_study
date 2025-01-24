package exam.study_02;

//1. 추상 클래스 정의 (Student)
abstract class Student {
 private String name;  // 캡슐화
 private int studentId;

 public Student(String name, int studentId) {
     this.name = name;
     this.studentId = studentId;
 }

 // Getter 메서드
 public String getName() {
     return name;
 }

 public int getStudentId() {
     return studentId;
 }

 // 추상 메서드
 public abstract double calculateAverage(); // 평균 점수 계산
 public abstract String getGrade();        // 등급 계산
}

//2. 인터페이스 정의
interface ScholarshipEligible {
 boolean isEligibleForScholarship(); // 장학금 여부 확인
}

//3. 일반 학생 클래스 (GeneralStudent)
class GeneralStudent extends Student {
 private int[] scores; // 과목별 점수 배열

 public GeneralStudent(String name, int studentId, int[] scores) {
     super(name, studentId);
     this.scores = scores;
 }

 @Override
 public double calculateAverage() {
     int sum = 0;
     for (int score : scores) {
         sum += score;
     }
     return (double) sum / scores.length;
 }

 @Override
 public String getGrade() {
     double average = calculateAverage();
     if (average >= 90) return "A";
     else if (average >= 80) return "B";
     else if (average >= 70) return "C";
     else if (average >= 60) return "D";
     else return "F";
 }
}

//4. 특기생 클래스 (SpecialStudent)
class SpecialStudent extends Student implements ScholarshipEligible {
 private int[] scores;
 private boolean participatesInCompetition;

 public SpecialStudent(String name, int studentId, int[] scores, boolean participatesInCompetition) {
     super(name, studentId);
     this.scores = scores;
     this.participatesInCompetition = participatesInCompetition;
 }

 @Override
 public double calculateAverage() {
     int sum = 0;
     for (int score : scores) {
         sum += score;
     }
     return (double) sum / scores.length;
 }

 @Override
 public String getGrade() {
     double average = calculateAverage();
     if (average >= 85) return "A";
     else if (average >= 75) return "B";
     else if (average >= 65) return "C";
     else return "F";
 }

 @Override
 public boolean isEligibleForScholarship() {
     return calculateAverage() >= 85 && participatesInCompetition;
 }
}

//5. 학생 성적 관리 메인 클래스
public class StudentManagementSystem {
 public static void main(String[] args) {
     // 학생 배열 생성
     Student[] students = new Student[3];

     // 학생 추가
     students[0] = new GeneralStudent("Alice", 1, new int[]{85, 78, 92}); // 일반 학생
     students[1] = new SpecialStudent("Bob", 2, new int[]{88, 91, 95}, true); // 특기생
     students[2] = new SpecialStudent("Charlie", 3, new int[]{70, 65, 80}, false); // 특기생

     // 학생 정보 출력
     for (Student student : students) {
         System.out.println("ID: " + student.getStudentId() +
                            ", Name: " + student.getName() +
                            ", Average: " + student.calculateAverage() +
                            ", Grade: " + student.getGrade());

         // 장학금 대상 여부 확인
         if (student instanceof SpecialStudent) {
             SpecialStudent specialStudent = (SpecialStudent) student;
             System.out.println("  Scholarship Eligible: " + specialStudent.isEligibleForScholarship());
         }
     }
 }
}