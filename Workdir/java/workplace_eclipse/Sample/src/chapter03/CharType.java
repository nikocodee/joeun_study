package chapter03;

public class CharType {

	public static void main(String[] args) {
		
		char a = 'A';
		System.out.println("a:"+a);
		
		//문자 입력하면 숫자로 입력됨
		int b = a;
		System.out.println("b:"+b);
		
		//숫자 입력하면 문자로 입력됨
		char c = 66;
		System.out.println("c:"+c);
		
//		int d = a+b; // 65 + 65
//		int d = a+1; // 66 B
		int d = a+2; // 67 C
//		System.out.println("d:"+d);
		System.out.println("d:"+(char)d); //형변환
	}

}
