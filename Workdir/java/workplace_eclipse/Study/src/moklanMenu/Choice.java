package moklanMenu;

public class Choice { // 선택한 메뉴와 그 가격을 저장하기위한 클래스
	String name; // 친구의이름 저장
	String menu1_name; // 첫번쨰 메뉴의 이름을 저장
	int menu1_price; // 첫번쨰 메뉴의 가격을 저장
	String menu2_name; // 두번쨰 메뉴의 가격을 저장
	String menu2_list;
	int menu2_price;
	String dish;
	String dish_name;
	int dish_price;

	Choice(String name) { // 생성자-친구의 이름을 받아서 객체를 초기화
		this.name = name;

	}

	// 메서드- 메뉴의 이름과 가격을 설정
	void menu1(String menu1_name, int menu1_price) {
		this.menu1_name = menu1_name;
		this.menu1_price = menu1_price;
	}

	void menu2(String menu2_name, String menu2_list, int menu2_price) {
		this.menu2_name = menu2_name;
		this.menu2_list = menu2_list;
		this.menu2_price = menu2_price;

	}
	
	void dish(String dish_name, int dish_price) {
		this. dish_name = dish_name;
		this. dish_price = dish_price;}
	
	
	// 총비용 계산
    public int calculateTotal() {
        int total = 0;
        if (menu1_price > 0) {
            total += menu1_price; // 식사 메뉴 가격 추가
        }
        if (menu2_price > 0) {
            total += menu2_price; // 코스 메뉴 가격 추가
        }
        return total;
    }

    // 주문 내역 출력
    public void printOrder(int dishshare) {
        System.out.println(name + "님의 주문 내역:");
        if (menu1_name != null) {
            System.out.println("식사 메뉴: " + menu1_name + " - " + menu1_price + "원");
        }
        if (menu2_name != null) {
            System.out.println("코스 메뉴: " + menu2_name + " - " + menu2_price + "원");
            System.out.println("코스 구성: " + menu2_list);
        }
        if (dish_name != null) {
            System.out.println("디쉬: " + dish_name + " - " + dish_price + "원");
        }
        System.out.println("총 비용: " + calculateTotal() + "원");
        System.out.println();
    }
}

