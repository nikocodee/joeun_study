package test.study_03;

import java.util.ArrayList;
import java.util.List;

public class Student {
	private int id;
	private String name;
	private List<Grade> grades;

	public Student(int id, String name) {
		this.id = id;
		this.name = name;
		// <>제네릭이 비어있으면 Object가 들어가있는 것
		this.grades = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void addGrade(Grade grade) {
		grades.add(grade);
	}

	@Override
	public String toString() {
		return "Student@@@ [id=" + id + ", name=" + name + ", grades=" + grades + "]";
	}
}
