package by.pva.functionalProgramming.methodReference;

import java.util.function.Consumer;
import java.util.function.Function;

public class MethodReferenceTypes {
	public static void main(String[] args) {
		
		// Static method reference.
		// ContainingClass::staticMethodName
		// Function<String, Boolean> function = e -> Boolean.valueOf(e);
		Function<String, Boolean> function = Boolean::valueOf;
		System.out.println(function.apply("TRUE"));
		
		// Non static method particular object reference.
		// containingObject::instanceMethodName
		// Consumer<String> consumer = e -> System.out.println(e);
		Consumer<String> consumer = System.out::println;
		consumer.accept("Hello");
		
		// Non static method any object reference.
		// ContainingType::methodName
		// Function<String, String> function = s -> s.toLowerCase();
		Function<String, String> function2 = String::toLowerCase;
		System.out.println(function2.apply("HELLO"));
		
		// Constructor reference.
		// ClassName::new
		// Function<String, Integer> function = (d) -> new Integer(d);
		Function<String, Integer> function3 = Integer::new;
		System.out.println(function3.apply("4"));
	}
}

// https://www.examclouds.com/ru/java/java-core-russian/method-references-russian