package by.pva.functionalProgramming.exercises.chapter5;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exercise_5_2_2 {
	public static void main(String[] args) {
		Stream<String> names = Stream.of("John", "Paul", "George", "John", "Paul", "John");
		
		Map<String, Long> map = names.collect(Collectors.groupingBy((element)->element, Collectors.counting()));
		System.out.println(map);
	}
}
