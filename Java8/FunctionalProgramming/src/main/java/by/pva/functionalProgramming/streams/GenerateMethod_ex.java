package by.pva.functionalProgramming.streams;

import java.util.Random;
import java.util.stream.Stream;

public class GenerateMethod_ex {
	public static void main(String[] args) {
		Stream.generate(new Random()::nextBoolean) 
	    .limit(10).forEach(System.out::println);
	}
}
