package by.pva.functionalProgramming.streams.operations;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Operation_Peek {
	public static void main(String[] args) {
		Stream.of("one", "two", "three", "four")
	          .filter(e -> e.length() > 3)
	          .peek(e -> System.out.println("Filtered value: " + e))
	          .map(String::toUpperCase)
	          .peek(e -> System.out.println("Mapped value: " + e))
	          .collect(Collectors.toList());
		
		// Output:
		// Filtered value: three
		// Mapped value: THREE
		// Filtered value: four
		// Mapped value: FOUR
		
		// Using peek method to change elements: 
		// https://www.baeldung.com/java-streams-peek-api
		// Stream<User> userStream = Stream.of(new User("Alice"), new User("Bob"), new User("Chuck"));
		// userStream.peek(u -> u.setName(u.getName().toLowerCase()))
		//           .forEach(System.out::println);
	}
}
