package test.study_01;

import java.util.Scanner;

public class RandomGame {
	public static void main(String[] args) {
		// 1~100까지의 숫자
		int grade = (int) ((Math.random() * 100) + 1);
		System.out.println("정답" + grade);
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 5; i++) {
			int keyInput = sc.nextInt();
			if (grade == keyInput) {
				System.out.println("정답!!!");
				break;
			} else {
				System.out.println("다시 입력해주세요.");
			}
		}
		System.out.println("5번의 기회가 끝났습니다.");
		sc.close();

		// 1~100까지의 숫자
//		int grade = (int) ((Math.random() * 100) + 1);
//		System.out.println("정답: " + grade);
//		Scanner sc = new Scanner(System.in);
//
//		for (int i = 0; i < 5; i++) {
//			System.out.print("숫자를 입력하세요 (종료하려면 'q' 입력): ");
//			String input = sc.next();
//
//			if (input.equalsIgnoreCase("q")) {
//				System.out.println("프로그램을 종료합니다.");
//				break;
//			}
//
//			int keyInput;
//			try {
//				keyInput = Integer.parseInt(input);
//			} catch (NumberFormatException e) {
//				System.out.println("유효한 숫자를 입력하세요.");
//				i--; // 기회를 소진하지 않도록
//				continue;
//			}
//
//			if (grade == keyInput) {
//				System.out.println("정답!!!");
//				break;
//			} else {
//				System.out.println("다시 입력해주세요.");
//			}
//
//			if (i == 4) {
//				System.out.println("5번의 기회가 끝났습니다.");
//			}
//		}
//
//		sc.close();
	}

}
