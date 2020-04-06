package by.pva.functionalProgramming.streams.operations;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Operation_Filter {
	
	public static void main(String[] args) {
		
		List<String> list = Stream.of("a56bba", "1hfj", "password", "op44")
				                  .filter(Operation_Filter::stringContainsNumber)
				                  .collect(Collectors.toList());
		
		System.out.println(list);
		
		List<String> list2 = Stream.of("qa34", "ifelse", "counter1", "gg")
                .filter(s->Pattern.compile("[0-9]").matcher(s).find())
                .collect(Collectors.toList());
		
		System.out.println(list2);
	}
	
	private static boolean stringContainsNumber(String s){
	    return Pattern.compile("[0-9]").matcher(s).find();
	}
}
