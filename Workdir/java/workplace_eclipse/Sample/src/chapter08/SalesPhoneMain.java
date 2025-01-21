package chapter08;

public class SalesPhoneMain {
	public static void main(String[] args) {
		SlidingPhone p1 = new SlidingPhone();
		SlidingPhone p2 = new SlidingPhone();
		FolderPhone p3 = new FolderPhone();
		SmartPhone p4 = new SmartPhone();
		SlidingPhone p5 = new SlidingPhone();

//		SlidingPhone[] pa = new SlidingPhone[10]; //부모 같아도 에러
		Phone[] pa = new Phone[10];
		//phone 이라는 부모의 그릇안에 자식객체의 시작점의 주소가 대입
		pa[0] = p1;
		pa[1] = p2;
		pa[2] = p3;
		pa[3] = p4;
		pa[4] = p5;

//		FolderPhone fp = new FolderPhone();
//		SlidingPhone sp = new SlidingPhone();
//		SmartPhone sm = new SmartPhone();
		FolderPhone fp;
		SlidingPhone sp;
		SmartPhone sm;
		
//		for (int i = 0; i < pa.length; i++) {
//			// instanceof 뒤의 꺼에 객체인지 확인
//			if (pa[i] instanceof FolderPhone) {
//				fp = (FolderPhone) pa[i];
//				fp.folding();
//			} else if (pa[i] instanceof SlidingPhone) {
//				sp = (SlidingPhone) pa[i];
//				sp.sliding();
//			} else {
//				sm = (SmartPhone) pa[i];
//				sm.installApp();
//			}
//
//		}

		//변수에는 자식 객체의 시작점의 주소가 들어가있음
		for (Phone ph : pa) {
			if (ph != null) {
				// instanceof 뒤의 꺼에 객체인지 확인
//				if (ph instanceof FolderPhone) {
//					fp = (FolderPhone) ph;
//					fp.folding();
////					fp.call();
//				} else if (ph instanceof SlidingPhone) {
//					sp = (SlidingPhone) ph;
//					sp.sliding();
////					sp.call();
//				} else {
//					sm = (SmartPhone) ph;
//					sm.installApp();
////					sm.call();
//				}
				//함수의 이름을 규격화한다. 부모에서 결정됨
				ph.call();
			}

		}
	}
}