package test.study_01;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrTest {
	public static void main(String[] args) {
		// 선언 후 길이 수정이 자유롭게 안됨
//		String[] arrStr = new String[5];
//		arrStr[0] = "홍길동";
//		arrStr[1] = "이순신1";
//		arrStr[2] = "이순신2";
//		arrStr[3] = "이순신3";
//		arrStr[4] = "이순신4";
//		arrStr[5] = "이순신5"; // 에러
//
//		System.out.println("배열의 길이 : " + arrStr.length);
//		System.out.println(Arrays.toString(arrStr)); // [홍길동, 이순신1, 이순신2, 이순신3, 이순신4]
//		System.out.println(arrStr.toString()); //[Ljava.lang.String;@19469ea2

		// 배열 사이즈 길이 지정안하고 자유롭게 가능
		ArrayList<String> arrStr = new ArrayList<String>();
		arrStr.add("홍길동");
		arrStr.add("홍길동2");

		System.err.println("배열의 길이 : " + arrStr.size());
		System.out.println(arrStr.toString()); // [홍길동, 홍길동2]
	}
}
