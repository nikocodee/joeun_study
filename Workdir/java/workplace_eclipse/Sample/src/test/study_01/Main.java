package test.study_01;

// Main.java - 프로그램 실행 부분
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 학생 데이터를 생성
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", new int[]{85, 90, 88}));
        students.add(new Student("Bob", new int[]{72, 75, 70}));
        students.add(new Student("Charlie", new int[]{95, 93, 96}));
        students.add(new Student("Diana", new int[]{60, 62, 58}));

        // 모든 학생의 평균 점수와 학점 출력
        for (Student student : students) {
            System.out.println("학생 이름: " + student.getName());
            System.out.println("평균 점수: " + student.calculateAverage());
            System.out.println("학점: " + student.calculateGrade());
            System.out.println("---------------");
        }
    }
}