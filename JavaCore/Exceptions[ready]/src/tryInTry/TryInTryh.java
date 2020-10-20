package tryInTry;

public class TryInTryh {
	public static void main(String[] args) {
		try{
			try{
			int a = 10/0;
			System.out.println(a);
			}catch(NullPointerException e){}
		}catch(ArithmeticException e){
			System.out.println("External try block - " + e);
		}
	}
}
