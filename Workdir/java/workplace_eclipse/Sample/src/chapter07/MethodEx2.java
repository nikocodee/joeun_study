package chapter07;

public class MethodEx2 {

	public static void main(String[] args) {

//		System.out.println(divide(pow(add(3,3))));
		System.out.println(divide(pow(add(imp(3), imp(4)))));

	}

	static int imp(int x) {
		System.out.println("imp : " + x);
		return x;
	}

	static int add(int x, int y) {
		System.out.println("add : " + (x + y));
		return x + y;
	}

	static int pow(int x) {
		System.out.println("pow : " + (x * x));
		return x * x;
	}

	static int divide(int x) {
		// 정수 나누기 정수는 정수
		System.out.println("div : " + (x / 2));
		return x / 2;
	}

}
