package test.study_01;

import java.util.Scanner;

public class RandomGame2 {
	public static void main(String[] args) {
		int grade = (int) ((Math.random() * 100) + 1);
		System.out.println("정답 " + grade);

		Scanner sc = new Scanner(System.in);

		System.out.println("도전할 횟수를 입력하세요.: ");
		int num = sc.nextInt();
		String input = "";
		
		for (int i = 0; i < num; i++) {
			System.out.println("숫자를 입력하세요 (종료하려면 'q' 입력): ");
			input = sc.nextLine();

			if (input.equals("q")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}

			int keyInput;
			// 숫자가 아닌 문자, 공백 입력 시
			try {
				keyInput = Integer.parseInt(input);
			} catch (Exception e) {
				System.out.println("유효한 숫자를 입력하세요.");
				i--; // 잘못 입력했으니 기회를 소진하지 않도록
				continue;
			}

			if (grade == keyInput) {
				System.out.println("정답!!!");
				break;
			} else if (i < (num - 1)) {
				System.out.println("다시 입력해주세요.");
			}

			if (i == (num - 1)) {
				System.out.println(num + "번의 기회가 끝났습니다.");
			}
		}

		sc.close();
	}
}
