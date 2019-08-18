package by.pva.testing.classesForTesting;

public class Calculator {

	public int add(int a, int b) {
		return a + b;
	}

	public Object subtract(int i, int j) {
		return i - j;
	}
	
	public int multiply(int i, int j) {
		return i * j;
	}

	public double divide(int i, int j) {
		if (j == 0) 
			throw new ArithmeticException("/ by zero");
		
		return i/(double)j;   // If j = 0 the result is "Infinity". 
		                      // (If we omit the typecasting then an ArithmeticException will be thrown).
	}
}
