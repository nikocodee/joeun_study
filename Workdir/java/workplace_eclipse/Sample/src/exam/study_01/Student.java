package exam.study_01;

// 전부 abstract만 있다면 인터페이스로
public abstract class Student {
	// 외부에 공개 안함, 캡슐화
	private String name;
	private int studentId;

	Student(String name, int studentId) {
//		super();	// object 받을일 없으니 주석
		this.name = name;
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public int getStudentId() {
		return studentId;
	}

	// 선언만 (추상 메서드 있으면 추상 클래스가 됨)
	// new 불가, 상속만 가능 (자식에서 생성자는 사용)
	abstract double calcMean();
	abstract String getGrade();
	
	@Override
	public String toString() {
		return "Student [name=" + name + ", studentId=" + studentId + ", getName()=" + getName() + ", getStudentId()="
				+ getStudentId() + "]";
	}
	
}
