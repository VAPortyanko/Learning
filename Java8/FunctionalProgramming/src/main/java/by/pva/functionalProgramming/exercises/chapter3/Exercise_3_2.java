package by.pva.functionalProgramming.exercises.chapter3;

import java.util.List;

import by.pva.functionalProgramming.baseClasses.Artist;

public class Exercise_3_2 {

	public static int countBandMembersInternal(List<Artist> artists) {
		return artists.stream()
			   .flatMap(artist -> artist.getMembers())
			   .mapToInt(artist -> 1)
			   .sum();
	}
	
	public static int countBandMembersInternal2(List<Artist> artists) {
		return artists.stream()
			   .map(artist -> artist.getMembers().count())
			   .reduce(0L, Long::sum)
			   .intValue();
	}
	
	public static int countBandMembersInternal3(List<Artist> artists) {
		return (int) artists.stream()
				     .flatMap(artist -> artist.getMembers())
				     .count();
	}	
	
}

/*
 *  countBandMembersInternal[n] - analog of the code below:
 *  
 *   int totalMembers = 0;
 *   for (Artist artist : artists) {
 *      Stream<Artist> members = artist.getMembers();
 *      totalMembers += members.count();
 *   }
*/