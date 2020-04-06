package by.pva.functionalProgramming.streams;

import java.util.Arrays;
import java.util.List;
import by.pva.functionalProgramming.baseClasses.Artist;

public class StreamExample {
	public static void main(String[] args) {

		List<Artist> artists = Arrays.asList(new Artist("Teresa"      , "London"),
				                             new Artist("Aleksandr"   , "Minsk"),
				                             new Artist("Elizabeth II", "London"),
				                             new Artist("Vladimir"    , "Moscow"));
		
		int count = 0;
		for (Artist artist : artists) {
			if (artist.isFrom("London")) {
				count++;
			}
		}
		
		System.out.println(count);
		
		long groupCount = artists
				          .stream()
				          .filter(artist->artist.isFrom("London"))
		                  .count();
		
		System.out.println(groupCount);

	}
}
