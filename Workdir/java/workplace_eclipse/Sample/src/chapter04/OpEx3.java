package chapter04;

public class OpEx3 {

	public static void main(String[] args) {

//		int a = 10;
//		int b = 10;
//		
//		++a; // 전위연산
//		b++; // 후위연산
//		
//		System.out.println(a);
//		System.out.println(b);

//		int a = 10;
//		int b = ++a;
//		
//		System.out.println("전위연산 결과 : "+b);
//		
//		int x = 10;
//		int y = x++;
//		int y = ++x;
//		System.out.println("후위연산 결과 : " + y);
//		System.out.println("x : " + x);
//
//		int a = 10;
//		a++;
//		System.out.println("a++ : " + a);
//		int b = 10;
//		b += 1;
//		System.out.println("b += 1 : " + b);
//		int c = 10;
//		c = c + 1;
//		System.out.println("c = c + 1 : " + c);
//		System.out.println(Integer.toBinaryString(x));
		// 문자열이 먼저
//		System.out.println("2.0" + 1 + 2.0);

//		long b = 0;
//		switch ((int) b) {
//			case 0: 
//				System.out.println("0");
///				System.out.println("0");
//				// break 없으면 switch문 안 끝나서 뒤에도 다 출력
//			case 1:
//				System.out.println("1");
//				System.out.println("1");
//				break;
//			default:
//				System.out.println("other");
//				System.out.println("other1");
//		}

//		for (int i = 0; i < 10; i++) {
//			System.out.println(i);
//		}

//		int i = 0;
//		do {
//			i++;
//			if (i % 2 == 0) {
//				continue;
//			}
//			System.out.println("i : " + i);
//		} while (i < 10);

		temp: for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (j % 2 != 0) {
					break temp;
				}
				System.out.println(j);
			}
		}
	}
}
