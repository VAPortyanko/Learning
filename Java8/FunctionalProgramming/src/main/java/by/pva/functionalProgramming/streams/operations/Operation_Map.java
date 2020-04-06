package by.pva.functionalProgramming.streams.operations;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Operation_Map {
	
	private final static CharSequence DELIMETER = " ";
	
	public static void main(String[] args) {
		List<String> list = Stream.of("republic","of","belarus")
				                  .map(element->element.toUpperCase())
				                  .collect(Collectors.toList());
		
		System.out.println(list);
		
		String name = list.stream()
				          .collect(Collectors.joining(Operation_Map.DELIMETER));
		System.out.println(name);
		
	}
}
