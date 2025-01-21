package chapter08;

public class SmartPhone extends Phone {

	public void installApp() {
		System.out.println("앱 설치");
	}

	@Override
	void call() {
		// TODO Auto-generated method stub
//		super.call();
		System.out.println("스마트폰 호출");
	}
	
}
