package by.pva.servletapi.jsp;

public class Factorial {

	private static int factorial = 1;
	private static int i = 0;

	public static synchronized String getFactorial() {
		
		if (i == 10) {
			factorial = 1;
			i = 0;
		}
		
		i++;
		factorial = factorial * i;
		
		return "Factorial of " + i + " is " + (factorial);
	}
	
}
