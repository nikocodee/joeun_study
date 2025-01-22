package test.study_01;

public class Amd extends GraphicCard {

	Amd() {
		memory = 2;
	}

	Amd(int mem) {
		memory = mem;
	}

	public void process() {
		System.out.println("Amd > process 호출 : 메모리 : " + memory + " G");
	}

	@Override
	void card() {
		System.out.println("Amd > card 호출");
	}
//	void card() {
//		System.out.println("Amd > card 호출");
//	}
}
