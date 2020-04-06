package by.pva.functionalProgramming.streams.operations;

import java.util.stream.Stream;

public class Opertion_Reduce {
	public static void main(String[] args) {
		
		int sum = Stream.of(1,2,3,4,5)
				        .reduce(0, (acc, element) -> acc + element);
		
		System.out.println(sum);
	}
}
