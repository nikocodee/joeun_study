package chapter06;

public class Test0123 {
	public static void main(String[] args) {
		int[][][] arrInt = new int[3][3][3];

		int value = 0;
		
		// 값 대입
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {
				for (int k = 0; k <= 2; k++) {
					arrInt[i][j][k] = value++;
				}
			}
		}
				
		// 향상된 for문 (내가 한것)
		int i=0;
		int j=0;
		int k=0;
		for(int[][] row : arrInt) { //3차원 반복하니까 2차원, 3x3으로 된 한 행
			System.out.println("행 : " + i);
			j=0;
			for (int[] col :  row) { //2차원 반복하니까 1차원
				System.out.println("행>행>(열) : " + "(" + i +"," + j + ")");
				k=0;
				for(int elm : col) { //1차원 반복하니까 원소
					System.out.println("행>(행,열) : " + "(" + i + ", " + j + ", " + k + ")");
//					System.out.println((i+1)+ " 면 " + (j+1) + " 행 " + (k+1) + " 열");
					k++;
				}
				j++;
			}
			i++;
		}	
	}
}
