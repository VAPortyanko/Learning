package by.pva.functionalProgramming.testingGround;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import by.pva.functionalProgramming.baseClasses.Album;
import by.pva.functionalProgramming.baseClasses.Artist;

public class Test {
	public static void main(String[] args) {
		
		List<Artist> artists = Arrays.asList(new Artist("Teresa"          , "London"),
				 							 new Artist("The Aleksandr"   , "Minsk"),
				 							 new Artist("The Elizabeth II", "London"),
				 							 new Artist("The Vladimir"    , "Moscow"));
		
		Album album = new Album("New album", Collections.emptyList(), artists);
		
		Set<String> nationalities = album.getAllMusicians()
				                          .filter(artist->artist.getName().startsWith("The"))
				                          .map(group->group.getNationality())
				                          .collect(Collectors.toSet());
		
		System.out.println(nationalities);
		
	}
}
