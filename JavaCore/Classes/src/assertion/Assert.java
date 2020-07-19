package assertion;

public class Assert {
	
	public static void main(String[] args) throws InterruptedException {
	
		for (int i = 10; i>0; i--){
			Thread.sleep(500);
			assert i!=5 : "i = 5"; // If the condition is true, nothing happens, else the java.lang.AssertionError is thrown.
			System.out.println(i);
		}
	}
}
// !!! run -> run configuration -> arguments -> vm arguments = -ea for work (default - disable).