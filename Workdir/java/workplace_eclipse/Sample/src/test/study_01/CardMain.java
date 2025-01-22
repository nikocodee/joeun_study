package test.study_01;

public class CardMain {
	public static void main(String[] args) {
//		GraphicCard gc = new GraphicCard(2);
//		gc.process();

		Amd amd = new Amd(4);
//		amd.process();
//		amd.card();

		Nvidia nvidia = new Nvidia(16);
//		nvidia.process();
//		nvidia.card();

		GraphicCard[] ga = new GraphicCard[5];
//		ga[0] = gc;
		// amd, nvidia는 부모가 더 커서 자동 형변환돼서 저장됨
		ga[1] = amd;
		ga[2] = nvidia;

//		ga[0].process();
		ga[1].process();
		ga[2].process();

//		ga[0].card(); // 부모에는 원래 card()가 없으니까 에러
		// GraphicCard 타입이여서 card()가 있는지 모름, 부모꺼만 알고 있음
//		ga[1].card();
//		ga[2].card();

		// 한번 쓰고 사용 안하려고 바로 강제 형변환
//		((Amd) ga[1]).card();
//		((Nvidia) ga[2]).card();

		// 변수 선언 후, 강제 형변환
		Amd at = (Amd) ga[1];
		at.card();
		
		Nvidia nt = (Nvidia) ga[2];
		nt.card();
	}
}
