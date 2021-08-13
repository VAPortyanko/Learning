package tryInTry;

public class TryInTry2 {
	
	public static void main(String[] args) {
		try{
			say();
		}catch(NullPointerException e){
			System.out.println("NPE!");
		}
	}

	@SuppressWarnings("null")
	static void say(){
		try{
			String s = null;
			s.charAt(10);	
		}catch(ArithmeticException e){
			System.out.println("ArithmeticException!");
		}
	}
}
