package chapter03;

public class test {

	public static void main(String[] args) {
		int a = 5; // 0101 in binary
		int b = 3; // 0011 in binary

		// AND 연산자
		int andResult = a & b; // 0101 & 0011 = 0001 (1 in decimal)
		System.out.println("AND 연산 결과: " + andResult);

		// OR 연산자
		int orResult = a | b; // 0101 | 0011 = 0111 (7 in decimal)
		System.out.println("OR 연산 결과: " + orResult);

		// XOR 연산자
		int xorResult = a ^ b; // 0101 ^ 0011 = 0110 (6 in decimal)
		System.out.println("XOR 연산 결과: " + xorResult);

		// NOT 연산자
		int notResult = ~a; // ~0101 = 1010 (10 in binary, -6 in decimal due to two's complement)
		System.out.println("NOT 연산 결과: " + notResult);

		// 왼쪽 시프트 연산자
		int leftShiftResult = a << 1; // 0101 << 1 = 1010 (10 in decimal)
		System.out.println("왼쪽 시프트 연산 결과: " + leftShiftResult);

		// 오른쪽 시프트 연산자
		int rightShiftResult = a >> 1; // 0101 >> 1 = 0010 (2 in decimal)
		System.out.println("오른쪽 시프트 연산 결과: " + rightShiftResult);
		
		// 데이터 타입의 연산
		String a1 = 1 + 2.0 + "A";
		System.out.println("a1 : " + a1);
				
	}
}
