package by.pva.functionalProgramming.streams.parallel;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ParallelStream_ex {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		Stream<String> parallel1 = Stream.of("1","2", "3").parallel();
		Stream<String> parallel2 = Arrays.asList("1", "2", "3").parallelStream();
		
		Supplier<Stream<Integer>> streamSupplier = () -> Stream.generate(new Random()::nextInt) 
			                                                   .limit(200);
		System.out.println("Sequental execution:");
		streamSupplier.get().forEach((value) -> System.out.println(Thread.currentThread().getName() + " : value = " + value));
		
		System.out.println("\nParallel execution:");		
		streamSupplier.get().parallel().forEach((value) -> System.out.println(Thread.currentThread().getName() + " : value = " + value));
		
		System.out.println("Available processors: " + Runtime.getRuntime().availableProcessors());
		
	}
}
