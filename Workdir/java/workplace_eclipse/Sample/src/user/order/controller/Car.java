package user.order.controller;

public class Car {
	// 필드 (클래스 변수)
	String color;
	String company;
	String type;

	// 매개변수 color 넘겨서 생성자 생성
//	Car(String color) {
//		// =뒤에 있는 color는 매개변수로 넘겨 준 값
//		// this.color는 클래스에 있는 color를 가르킴
//		this.color = color;
//	}

	// 생성자
//	Car(String color, String company, String type) {
//		this.color = color;
//		this.company = company;
//		this.type = type;
//	}

	//toString
	@Override
	public String toString() {
		return "Car [color=" + color + ", company=" + company + ", type=" + type + "]";
	}
	
	public Car(String color) {
		super();
		this.color = color;
	}

	public Car(String color, String company, String type) {
		super();
		this.color = color;
		this.company = company;
		this.type = type;
	}

	// 메서드
	public void go() {
		System.out.println("전진하다");
	}

	public void back() {
		System.out.println("후진하다");
	}
}
