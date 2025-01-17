package chapter06;

public class ArrEx4 {

	public static void main(String[] args) {
		
		String[] arrStr = new String[5];
		
		System.out.println("0 : " + arrStr[0]);
		
		char[] arrChar = new char[3];
		System.out.println("1 : " + arrChar[0]*2);
		// \u0000 유니코드 null, 0
		System.out.println("2 : " + '\u0000'*2);
		
		int[] arrInt = new int[5];
		System.out.println("2 : " + arrInt[0]);
		
	}

}
