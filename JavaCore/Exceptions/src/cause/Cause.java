package cause;

public class Cause {
	public static void main(String[] args) {
		
		ArithmeticException ex1 = new ArithmeticException(); // Create a new exception.
		ex1.initCause(new NullPointerException());           // Set the reason for the exception.
		
		try{
			throw ex1;
		}catch(NullPointerException e){
			System.out.println(e);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
// The reason you can also specify when you create a new exception with constructors containing the reason.