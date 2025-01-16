package chapter03;

public class ScopeEx { // 1. 클래스 블럭

	//static, 객체화하지 않아도 호출 가능 
	static int no; // 1. 클래스 블럭 내에서 사용 가능한 변수
	
	public static void main(String[] args) { // 2. main 메서드 블럭
	
		String name; // 2. main 메서드 블럭 내에서 사용 가능한 변수
		
		if (true) {
			// 메서드 블럭 안에서 선언한 변수 사용 가능
			name = "홍길동";
			no = 7; 
			
			// if문 블럭안에서 변수 선언
			String email = "hong@test.com";
		}
		
//		System.out.println("email : " + email);
		System.out.println("no : " + no);
		System.out.println("name : " + name);
		
		Temp user;
		//user에는 객체의 시작 주소만 담김, 객체라서 heap메모리로 이동
		user = new Temp();
		System.out.println("객체 함수 결과 : " + user.calc());
		
		// if문 블럭 밖에서 email 변수를 사용하면 에러 발생 
		//email = "hong@test.com";
		System.out.println(user.test());
	}
	
}

//main 함수에서 new Temp() 했기때문에 static 필요 없음
class Temp {
	int userNo;
	String userName;
	
	Temp() {
		System.out.println("생성자 호출");
	}
	
	public int calc() {
		userNo = 10;
		return userNo;
	}
	
	public int test(){
		return 10;
	}
}