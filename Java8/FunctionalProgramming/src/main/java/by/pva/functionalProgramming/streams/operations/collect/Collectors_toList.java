package by.pva.functionalProgramming.streams.operations.collect;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import by.pva.functionalProgramming.baseClasses.Artist;

public class Collectors_toList {

	List<Artist> artists = Arrays.asList(new Artist("Teresa"      , "London"),
            							 new Artist("Aleksandr"   , "Minsk"),
            							 new Artist("Elizabeth II", "London"),
            							 new Artist("Vladimir"    , "Moscow"));
	
	public static void main(String[] args) {
		List<String> collection = Stream.of("a", "b", "c", "d")
				                        .collect(Collectors.toList());
		
		List<String> unmCollection = Stream.of("a", "b", "c", "d")
                                           .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
		
		System.out.println(collection);
		System.out.println(unmCollection);
	}
}
