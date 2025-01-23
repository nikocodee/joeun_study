package test.study_01;

// Student.java - 학생 정보를 담는 클래스
public class Student {
    private String name; // 학생 이름
    private int[] scores; // 과목별 점수 배열

    // 생성자
    public Student(String name, int[] scores) {
        this.name = name;
        this.scores = scores;
    }

    // 학생 이름 반환
    public String getName() {
        return name;
    }

    // 평균 점수 계산
    public double calculateAverage() {
        int total = 0;
        for (int score : scores) {
            total += score;
        }
        return (double) total / scores.length;
    }

    // 학점 계산
    public char calculateGrade() {
        double average = calculateAverage();
        if (average >= 90) return 'A';
        else if (average >= 80) return 'B';
        else if (average >= 70) return 'C';
        else if (average >= 60) return 'D';
        else return 'F';
    }
}