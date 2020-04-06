package by.pva.functionalProgramming.streams.primitiveStreams;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntStream_RangeMethod {
	public static void main(String[] args) {
		IntStream.range(1, 10).forEach(System.out::print);
		System.out.println();
		
		IntStream.rangeClosed(1, 10).forEach(System.out::print);
		System.out.println();
		
		String result = IntStream.rangeClosed(1, 10)
		                         .mapToObj(Integer::toString)         // map is not applicable for IntStream *(see the signature of the map method). 
		                         .collect(Collectors.joining(", "));
		
		System.out.println(result);
		

	}
}

// * IntStream.map(java.util.function.IntUnaryOperator)