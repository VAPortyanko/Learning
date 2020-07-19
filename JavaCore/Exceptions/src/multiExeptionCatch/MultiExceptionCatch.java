package multiExeptionCatch;

public class MultiExceptionCatch {

	public static void main(String[] args) {
		// An alternative form of the catch block.
		try{
			int a  = 0;
			int b = 10;
			System.out.println(b/a);
		}
		catch(ArithmeticException | NullPointerException e){ // Multi-catch parameters are not allowed for source level below 1.7.
			System.out.println(e);
		}
	}
}
