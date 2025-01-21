package chapter08;

public class FolderPhone extends Phone {
	void folding() {
		System.out.println("폴딩/접기");
	}

	@Override
	void call() {
		// TODO Auto-generated method stub
//		super.call();
		System.out.println("폴더폰 호출");
	}
}
