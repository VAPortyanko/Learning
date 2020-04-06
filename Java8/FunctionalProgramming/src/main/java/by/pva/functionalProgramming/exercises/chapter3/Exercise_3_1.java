package by.pva.functionalProgramming.exercises.chapter3;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import by.pva.functionalProgramming.baseClasses.Album;
import by.pva.functionalProgramming.baseClasses.Artist;

public class Exercise_3_1 {
	
	public static int addUp(Stream<Integer> numbers) {
		return numbers.reduce(0, (acc, element) -> acc + element );
	}
	
	
		public static List<String> getNamesAndOrigins(List<Artist> artists) {

		return artists.stream()
			          .flatMap(artist-> asList(artist.getName(), artist.getNationality()).stream())
			        //.flatMap(artist -> Stream.of(artist.getName(), artist.getNationality()))
			          .collect(Collectors.toList());
	}

		
	public static List<Album> getAlbumsWithAtMostThreeTracks(List<Album> albums){
		
		return albums.stream()
		             .filter(album->album.getTrackList().size() < 4)
		             .collect(Collectors.toList());
	}
	
}

/* 
	public static int addUp(Stream<Integer> numbers); - Returns a sum of numbers;
	public static List<String> getNamesAndOrigins(List<Artist> artists); - Returns a list of strings like "Name1, Nationality1, Name2, Nationality2, ..., Name[n], Nationality[n]" for a list of artists; 
	public static List<Album> getAlbumsWithAtMostThreeTracks(List<Album> albums); - Returns a list of albums, which contain 3 or less tracks.
*/