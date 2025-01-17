package chapter06;

public class ArrEx1 {

	public static void main(String[] args) {
		
//		int[] arrInt;
		int arrInt2[] = null;
		
//		System.out.println(arrInt[0]); // 에러 발생, arrInt 초기화 하지 않아서
//		System.out.println(arrInt2[0]); // 에러 발생, null이기 때문에
//		
		if ("".equals(arrInt2)) { //null이 들어간 값은 뒤에 위치시킴
//		if (arrInt2.equals("")) { //null은 속성이 없음
			System.out.println("null이 아님");
		} else {
			System.out.println("null");
		}
		
//		System.out.println(null);

	}

}
