package user.order.controller;

public class MainCar {
	public static void main(String[] args) {
		Car tico = new Car("화이트","대우","경차");
		Car pride = new Car("블랙","기아","소형");

		// 객체의 변수
//		tico.color = "화이트";
//		tico.company = "대우";
//		tico.type = "경차";

//		pride.color = "블랙";
//		pride.company = "기아";
//		pride.type = "소형";

		tico.go();
		pride.go();

		System.out.println(tico.company);
		System.out.println(pride.company);
		System.out.println(tico.toString());
		
	}
}