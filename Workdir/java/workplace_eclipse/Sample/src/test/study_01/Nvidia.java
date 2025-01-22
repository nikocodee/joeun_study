package test.study_01;

public class Nvidia extends GraphicCard {

	public Nvidia() {
//		super(); // GraphicCard의 생성자 호출됨 memory = 1
		memory = 4;
	}

	Nvidia(int mem) {
		memory = mem;
	}

	@Override
	public void process() {
//		super.process();
		System.out.println("Nvidia > process 호출 : 메모리 " + memory + " G");
	}

	void card() {
		System.out.println("Nvidia > card 호출");
	}

}
