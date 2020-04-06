package by.pva.functionalProgramming.streams.operations.collect;

import java.util.function.Function;
import by.pva.functionalProgramming.baseClasses.Artist;

import static by.pva.functionalProgramming.baseClasses.SampleData.*;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class MaxBy_ex {
	public static void main(String[] args) {
		
		Function<Artist,Long> getCount = artist -> artist.getMembers().count();
		
		System.out.println(threeArtists().collect(maxBy(comparing(getCount))));
	}
}
