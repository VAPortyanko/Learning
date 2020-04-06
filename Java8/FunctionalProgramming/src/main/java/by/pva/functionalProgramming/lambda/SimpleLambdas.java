package by.pva.functionalProgramming.lambda;

import java.text.ParseException;

public class SimpleLambdas {
	
	public static void main(String[] args) throws ParseException {
		String phrase = "The current thread's name is "; 
		
		// phrase = "new phrase"; It will lead to compile time error in the statement below.
		// phrase must be final or effective final;
		
		Runnable run1 = () -> System.out.println(phrase + Thread.currentThread().getName());
		new Thread(run1).start();

	}
}

/*
Different ways to write lambda expressions:
	
	1). Runnable noArguments = () -> System.out.println("Hello World");
	
	2). ActionListener oneArgument = event -> System.out.println("button clicked");
	
	3). Runnable multiStatement = () -> {
			System.out.print("Hello");
			System.out.println(" World");
		};
	
	4). BinaryOperator<Long> add = (x, y) -> x + y;
	
	5). BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;
*/
