package by.pva.functionalProgramming.exercises.chapter4;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import by.pva.functionalProgramming.baseClasses.Artist;

import static by.pva.functionalProgramming.baseClasses.SampleData.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Exercise_4_1_Test {

	static Exercise_4_1 testClass;
	
	@BeforeAll
	static void init() {
		
		testClass = new Exercise_4_1(){

			private final String PERFORMANCE = "Eurovision";
			private final Stream<Artist> MUSICANS = Stream.of(theBeatles);
			
			@Override
			public String getName() {
				return PERFORMANCE;
			}

			@Override
			public Stream<Artist> getMusicians() {
				return MUSICANS;
			}
			
		};
	}

	@Test
	public void getsAllMusicans() {
		
		assertTrue(testClass.getAllMusicians().collect(Collectors.toSet())
				            .equals(Stream.of(theBeatles, johnLennon, paulMcCartney, georgeHarrison, ringoStarr).collect(Collectors.toSet())));
	}
}
