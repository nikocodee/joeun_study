package chapter08;

public class SlidingPhone extends Phone {
	void sliding() {
		System.out.println("슬라이딩");
	}

	@Override
	void call() {
		// TODO Auto-generated method stub
//		super.call();
		System.out.println("슬라이드폰 호출");
	}

}
