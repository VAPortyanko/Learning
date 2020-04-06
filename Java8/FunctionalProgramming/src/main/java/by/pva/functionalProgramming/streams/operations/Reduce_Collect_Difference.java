package by.pva.functionalProgramming.streams.operations;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Reduce_Collect_Difference {
	public static void main(String[] args) {
		
		
		// This works similarly because StringBuilder::append returns the same Stringbuilder.
		System.out.println(Stream.of("M", "i", "n", "s", "k")
		      .reduce(new StringBuilder(), StringBuilder::append, StringBuilder::append));
		
		System.out.println(Stream.of("M", "i", "n", "s", "k")
			      .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append));
		
		
		// The First expression below doesn't work because ArrayList.add() return a boolean value. Only collect will work. 
		// System.out.println(Stream.of("M", "i", "n", "s", "k")
		//	      .reduce(new ArrayList<String>(), ArrayList::add, ArrayList::addAll));
			
		System.out.println(Stream.of("M", "i", "n", "s", "k")
		                   .collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
		
	}
}

/*
Reduce:
    U result = identity;
    for (T element : this stream)
        result = accumulator.apply(result, element)
    return result;
*/

/*
Collect:
	R result = supplier.get();
    for (T element : this stream)
    	accumulator.accept(result, element);
    return result;
*/
 