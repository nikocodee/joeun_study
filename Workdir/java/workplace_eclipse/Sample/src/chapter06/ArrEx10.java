package chapter06;

public class ArrEx10 {

	public static void main(String[] args) {
		
		// 첫번째
		int[][] matrix = new int[3][4];
		
		matrix[0][0] = 1;
		matrix[0][1] = 2;
		matrix[0][2] = 3;
		matrix[0][3] = 10;
		matrix[1][0] = 4;
		matrix[1][1] = 5;
		matrix[1][2] = 6;
		matrix[1][3] = 11;
		matrix[2][0] = 7;
		matrix[2][1] = 8;
		matrix[2][2] = 9;
		matrix[2][3] = 12;
		
		// 내가 한 거
//		for (int i = 0; i <matrix.length; i++) {
//			for( int j= 0; j<matrix[i].length; j++) {
//				if (matrix[i][j] == 8) {
//					System.out.println((i+1) + "행 " + (j+1) + "열" + "\n값 : " + matrix[i][j]);
//				} 
//			}
//		}
		
		// 강사님
		System.out.println(matrix.length); //행의 길이
		System.out.println(matrix[0].length); //열의 길이
		int value;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				value = matrix[i][j];
				if (value == 8) {
					System.out.println("행 : " + (i+1));
					System.out.println("행 : " + (i+1) + " || 열 : " + (j+1));
				}
			}
		}
		// 향상된 for문
		int i=0;
		int j=0;
		for (int[] row : matrix) {
			j=0;
			for (int col : row) {
				if(col==8) {
					System.out.println("행 : " + (i+1) + " || 열 : " + (j+1));
				}
				j++;
			}
			i++;
		}
		
		// 두번째
//		int[][] matrix2 = {{1,2,3}, {4,5,6}, {7,8,9}};
//		
//		// 세번째
//		int[][] matrix3 = {
//				{1,2,3}, 
//				{4,5,6}, 
//				{7,8,9}
//		};
//		
//		System.out.println("[첫번째]");
//		for (int i=0; i<matrix.length; i++) {
//			for (int j=0; j<matrix[i].length; j++) {
//				System.out.print(matrix[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println("[두번째]");
//		for (int i=0; i<matrix2.length; i++) {
//			for (int j=0; j<matrix2[i].length; j++) {
//				System.out.print(matrix2[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println("[세번째]");
//		for (int i=0; i<matrix3.length; i++) {
//			for (int j=0; j<matrix3[i].length; j++) {
//				System.out.print(matrix3[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		
	}

}
