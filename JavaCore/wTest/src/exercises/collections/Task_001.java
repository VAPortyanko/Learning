package exercises.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Write a program that prints its arguments in random order. Do not make a copy of the argument array. 
// Demonstrate how to print out the elements using both streams and the traditional enhanced for statement. 

public class Task_001 {

	public static void main(String[] args) {
		List<String> coll = Arrays.asList(args);
		
		Collections.shuffle(coll);
		
		System.out.println("Using stream:");
		printArgsUsingStream(coll);
		
		System.out.println("\nIn traditional way:");
		printArgsInTradtionalWay(coll);
	}

	static void printArgsUsingStream(List<String> coll) {
		coll.stream().forEach(System.out::print);
	}
	
	static void printArgsInTradtionalWay(List<String> coll) {
		for(String arg:coll) {
			System.out.print(arg);
		}
	}
}
