package by.pva.functionalProgramming.exercises.chapter5;

import java.util.Comparator;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exercise_5_2_1 {
	public static void main(String[] args) {
		
		Supplier<Stream<String>> streamSuplier = ()-> Stream.of("John Lennon",
                												"Paul McCartney",
                												"George Harrison", 
                												"Ringo Starr", 
                												"Pete Best", 
                												"Stuart Sutcliffe"); 
		
		System.out.println(streamSuplier.get()
		                                .reduce((acc, element)->acc.length()<element.length() ? element : acc)); 
		
		System.out.println(streamSuplier.get()
                .collect(Collectors.maxBy(Comparator.comparing(String::length))));
	}
}
