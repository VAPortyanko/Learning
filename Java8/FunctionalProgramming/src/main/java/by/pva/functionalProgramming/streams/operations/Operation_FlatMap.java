package by.pva.functionalProgramming.streams.operations;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class Operation_FlatMap {
	public static void main(String[] args) {
		List<Integer> together = Stream.of(asList(1, 2), asList(3, 4))
				                       .flatMap(numbers->numbers.stream())
				                       .collect(Collectors.toList());
		
		System.out.println(together);

	}
}

// https://mkyong.com/java8/java-8-flatmap-example/