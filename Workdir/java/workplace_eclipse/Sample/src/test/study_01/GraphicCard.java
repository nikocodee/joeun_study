package test.study_01;

// 추상 메소드면 추상 클래스로 바껴야 함
// new 대상이 아니라서 객체 생성 못함 (상속만 가능)
// CardMain에서 에러 발생 
// Cannot instantiate the type GraphicCard
public abstract class GraphicCard {
	int memory;

	// 생성자는 리턴값이 없으니까 void 자동으로 붙여줌
	GraphicCard() {
		memory = 1;
	}

	GraphicCard(int mem) {
		memory = mem;
	}

	public void process() {
		System.out.println("대표 클래스 GraphicCard > process 호출 : 메모리 : " + memory + " G");
	}
	
	// 선언만 있오 구현부가 없음 ( {} 중괄호가 없음 )
	// 강제적으로 자식 클래스에서 구현해야 함 (아니면 자식에서 에러남)
	// The type Amd must implement the inherited abstract method GraphicCard.card()
	// The type Nvidia must implement the inherited abstract method GraphicCard.card()
	abstract void card();
}
