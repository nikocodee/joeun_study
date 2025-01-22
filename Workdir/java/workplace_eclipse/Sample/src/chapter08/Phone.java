package chapter08;

public class Phone {

	String name;
	String color;
	String company;
	
	//이 생성자가 없으면, 컴파일러가 Phone(){}생성자를 자동으로 만들어줌
	Phone(String n){
		name = n;
	}
	
	Phone(){
		
	}

	void call() {
		System.out.println("전화를 건다");
	}

	void receive() {
		System.out.println("전화를 받다");
	}

}
