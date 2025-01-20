package chapter06;

public class Test0120 {

	public static void main(String[] args) {

		int[][][] arrInt = new int[2][3][5];

		int value = 0;
		// 값 대입
		for (int i = 0; i <= 1; i++) {
			for (int j = 0; j <= 2; j++) {
				for (int k = 0; k <= 4; k++) {
					arrInt[i][j][k] = value++;
				}
			}
		}

		// 값 출력
		System.out.println("{");
		for (int i = 0; i <= 1; i++) {
			System.out.println("    {");
			for (int j = 0; j <= 2; j++) {
				System.out.print("        {");
				for (int k = 0; k <= 4; k++) {
					System.out.print(arrInt[i][j][k]);
					if (k < 4) {
						System.out.print(", ");
					}
				}
				System.out.print("}");
				if (j < 2) {
					System.out.println(",");
				} else {
					System.out.println();
				}
			}
			System.out.print("    }");
			if (i < 1) {
				System.out.println(",");
			} else {
				System.out.println();
			}
		}
		System.out.println("}");
		
//		int[] a = {1,2,6,4,9};
//		for(int num: a) {
//			System.out.println(num);
//		}
////		for(int i=0; i<a.length; i++) {
////			System.out.println(a[i]);
//		}
	}
}
