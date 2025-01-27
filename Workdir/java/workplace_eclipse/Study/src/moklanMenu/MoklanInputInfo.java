package moklanMenu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MoklanInputInfo {
	public static void main(String[] args) {

		String[] menu1_name = { "짜장면", "짬뽕", "긴스면" };
		int[] menu1_price = { 10000, 15000, 11000 };

		String[] menu2_name = { "A코스", "B코스", "C코스" };
		String[] menu2_list = { "\n짜장면\n짬뽕\n탕수육\n", "\n짜장면\n짬뽕\n탕수육\n", "\n짜장면\n짬뽕\n탕수육\n" };
		int[] menu2_price = { 30000, 35000, 40000 };

		String[] dish_name = { "동파육", "멘보샤", "어향가지" };
		int[] dish_price = { 45000, 35000, 50000 };

		// 여기까지 데이터 배열로 넣기

		Scanner input = new Scanner(System.in);

		// 방문 몇명이니 물어보는 블록

		int howmany = 0; // 몇명이니 변수 선언 정수값 저장할 주머니 만들기
		

		boolean boolinput = false;
		while (!boolinput) {
			System.out.println("몇명이 방문예정인가요?");
			try {
				howmany = input.nextInt(); // 정수값을 받아요 몇명 올껀지
				boolinput = true;
			} catch (InputMismatchException e) {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.(인원수) ");
				input.next(); // 입력한 버퍼지우기
			}
		}

		Choice[] eachname = new Choice[howmany]; // 몇명이니 배열초기화
		
		// 배열에 몇명오는지 입력받기
 		System.out.println("이름을 입력하세요. 다수인 경우 콤마(,)로 입력해주세요.");
 		//김,이,박 (스페이스바x)
		String nameStr = input.next();
		String[] custName = nameStr.split(",");
		for (int i = 0; i < custName.length; i++) {
			eachname[i] = new Choice(custName[i]);
		}

		// 식사 or코스 선택시작 ////////////////////////////////////////////////

		int menuOrCourse = 0;

		boolean again = false;
		while (!again) {

			// 식사 또는 코스 선택하기
			System.out.println("선택한 번호를 눌러 주세요.\n1.식사류(1인1메뉴)\n2.코스요리");
			menuOrCourse = input.nextInt();

			boolean boolmenuOrCourse = false;
			while (!boolmenuOrCourse) {
				if (menuOrCourse == 1 || menuOrCourse == 2) {
//					boolmenuOrCourse = true;
					boolmenuOrCourse = !boolmenuOrCourse;
				} else {
					System.out.println("잘못입력하셨습니다. 다시 입력해주세요(식사or코스/1or2)");
				}

			}

			if (menuOrCourse == 1) {
				again = true;

				// 식사메뉴
				for (int i = 0; i < howmany; i++) {

					int menuChoice = 0;

					boolean boolmenuChoice = false;
					while (!boolmenuChoice) { // 조건 참일떄만 돌아줘.

						for (int j = 0; j < menu1_name.length; j++) {
							String menuList = ((j + 1) + ". " + menu1_name[j] + " - " + menu1_price[j] + "원");
							System.out.println(menuList);
						}
						System.out.println(eachname[i].name + "의 메뉴선택");
						System.out.println("메뉴번호를 선택하세요");
//						menuChoice = input.nextInt() - 1;
						int menuInput = input.nextInt();
						menuChoice = menuInput - 1;

						// 유효한 번호인지 확인

						if (menuChoice >= 0 && menuChoice < menu1_name.length) {
							eachname[i].menu1(menu1_name[menuChoice], menu1_price[menuChoice]);
							boolmenuChoice = true; // true 만나서 멈춘다.
						} else {
							System.out.println("잘못된 번호 입니다. 다시입력해주세요.(식사)");
						}

					}
				}

			} else if (menuOrCourse == 2) {
				again = true;

				// 코스메뉴

				for (int i = 0; i < howmany; i++) {

					boolean boolcourseChoice = false;
					while (!boolcourseChoice) {

						for (int j = 0; j < menu2_name.length; j++) {
							String courseList = ((j + 1) + ". " + menu2_name[j] + " - " + menu2_price[j] + "원");
							String courseList2 = ("코스구성" + menu2_list[j]);
							System.out.println(courseList + "\n" + courseList2);
						}

						System.out.println(eachname[i].name + "의 메뉴선택");
						System.out.println("원하는 코스번호를 선택하세요 :");
						int courseChoice = input.nextInt() - 1;

						if (courseChoice >= 0 && courseChoice < menu2_name.length) {
							eachname[i].menu2(menu2_name[courseChoice], menu2_list[courseChoice],
									menu2_price[courseChoice]);
							boolcourseChoice = true;
						} else {
							System.out.println("잘못 입력 하셨습니다. 다시 입력해주세요.(코스)");
						}

					}

				}
			} else {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.(agian)");
			}

		} // 식사 코스 while 중괄호

		// dish선택/////////////////////////////////////////////
		
		
		System.out.println("몇개의 요리를 선택하실 건가요?");
		int howmanydish = 0;
		howmanydish = input.nextInt();
		Choice[] eachdish = new Choice [howmanydish];
		
		

		for (int i = 0; i < dish_name.length; i++) { //디쉬 보여주기
			String dishlist = ((i + 1) + "." + dish_name[i] + "-" + dish_price[i] + "원");
			System.out.println(dishlist);
		}
		System.out.println("원하는 요리번호를 선택하세요");
		
		for (int i = 0; i < howmanydish; i++) {//디쉬 선택하기 
		}
		int dishChoice = input.nextInt() -1;
		 
//		eachdish[i] = new Choice(dish_name[dishChoice]);
//		eachdish[i].dish(dish_name[dishChoice], dish_price[dishChoice]);
		
		
		
		int totalDishPrice = dish_price[dishChoice];

        // 각 사람에게 동일한 디쉬 가격 분배
        int dishShare = totalDishPrice / howmany;

        // 각 사람에게 디쉬와 메뉴 정보 출력
        for (int i = 0; i < howmanydish; i++) {
            eachname[i].dish(dish_name[dishChoice], dish_price[dishChoice]);
            eachname[i].printOrder(dishShare);
        }

        // 전체 총비용 계산
        int totalCost = 0;
        for (int i = 0; i < howmany; i++) {
            totalCost += eachname[i].calculateTotal();
        }
        totalCost += totalDishPrice; // 디쉬의 총비용 추가

        System.out.println("전체 총비용: " + totalCost + "원");
        input.close();	
		
		
		
		
    }
}

