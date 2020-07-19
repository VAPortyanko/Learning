package uncaughtExceptionHandler;

public class UncaughtExceptionHandler {
	public static void main(String[] args) {
		
		Thread.UncaughtExceptionHandler handler = new MyExceptionHandler(); 
		Thread.setDefaultUncaughtExceptionHandler(handler);
		
		try {
			throw new NullPointerException("Object is not exist!");			
		} catch (Exception e) {
			System.out.println("First exception was catched!");
		}
	
		throw new NullPointerException("Object is not exist!!");
	}
	
	public static class MyExceptionHandler implements Thread.UncaughtExceptionHandler{
		@Override
		public void uncaughtException(Thread t, Throwable e) {
			System.out.println("Second exception was not catched! It processed by the method uncaughtExeption of the MyException class!");
			System.out.println("In the Thread '" + t.getName() + "' occurred exeption: " + e.getMessage());
		}
	}
}

/****************************************************************/
/* We can set our own handler for a particular thread           */
/* SetUncaughtExceptionHandler(UncaughtExceptionHandler)        */
/* or do it for all threads                                     */
/* setDefaultUncaughtExceptionHandler(UncaughtExceptionHandler) */
/****************************************************************/