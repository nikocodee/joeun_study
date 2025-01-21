package chapter07;

//import chapter07.test.TestPackage;
import chapter07.test.*;

public class PackageEx2 {

	public static void main(String[] args) {
		
		TestPackage test = new TestPackage();
		test.method();
		
		ClassA a = new ClassA();
		a.print();
		
	}
	
}
