package chapter08.poly;

public class PolyEx {

	public static void main(String[] args) {

		Child c = new Child();

		c.run();
		c.eat();

		// 부모클래스의 자료형으로 선언 (자동형변환)
		Parent p = new Child();
		p.run(); // 재정이된 메서드가 실행
//		p.eat(); // 에러

		// Child로 형변환
		Child cp = (Child) p;
		cp.eat();

		// 잠시 형변환해서 한번만 쓰려고 변수에 안 담음(괄호 우선)
//		((Child) p).eat();
//		(Child)p.eat(); // 이건 p.eat()부터 호출하고 형변환한거라 에러
	}

}
