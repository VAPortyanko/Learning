package by.pva.functionalProgramming.streams;

import java.util.stream.Stream;

public class IterateMethod_ex {
	public static void main(String[] args) {
		Stream.iterate(0, n -> n + 1)
		      .filter(x -> x % 2 != 0) //odd
		      .limit(10)
		      .forEach(x -> System.out.println(x));
	}
}
