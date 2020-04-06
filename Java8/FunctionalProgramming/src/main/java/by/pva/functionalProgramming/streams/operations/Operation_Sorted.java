package by.pva.functionalProgramming.streams.operations;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Operation_Sorted {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(2, 4, 1, 3, 7, 5, 9, 6, 8);

		List<Integer> sortedList = list.stream()
				                   .sorted()
				                   .collect(Collectors.toList());

		List<Integer> sortedList2 = list.stream()
                					.sorted(Comparator.reverseOrder())
                					.collect(Collectors.toList());
		
		List<Integer> sortedList3 = list.stream()
                				   .sorted( (i1, i2) -> i2.compareTo(i1) )
                				   .collect(Collectors.toList());
		
		System.out.println("Source: " + list);
		System.out.println("Result 1: " + sortedList);
		System.out.println("Result 2: " + sortedList2);
		System.out.println("Result 3: " + sortedList3);
	}
}
