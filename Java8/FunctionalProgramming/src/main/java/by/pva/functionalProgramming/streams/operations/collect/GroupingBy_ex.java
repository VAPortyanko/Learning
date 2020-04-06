package by.pva.functionalProgramming.streams.operations.collect;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import by.pva.functionalProgramming.baseClasses.Album;
import by.pva.functionalProgramming.baseClasses.Artist;

import static java.util.stream.Collectors.*;
import static by.pva.functionalProgramming.baseClasses.SampleData.*;

public class GroupingBy_ex {
	
	public static void main(String[] args) {
		Map<Artist, List<Album>> map = albumsByArtist(Stream.of(aLoveSupreme, sampleShortAlbum, manyTrackAlbum));
		System.out.println("John Coltrane's albums:");
		map.get(johnColtrane).forEach(album->System.out.println("   " + album.getName()));

		Map<Artist, Long> map2 = numberOfAlbums(Stream.of(aLoveSupreme, sampleShortAlbum, manyTrackAlbum));
		System.out.println("John Coltrane albums' count: " + map2.get(johnColtrane));
		
		Map<Artist, List<String>> map3 = nameOfAlbums(Stream.of(aLoveSupreme, sampleShortAlbum, manyTrackAlbum));
		map3.get(johnColtrane).forEach(System.out::println);
	}
	
	public static Map<Artist, List<Album>> albumsByArtist(Stream<Album> albums) {
		return albums.collect(groupingBy(album -> album.getMainMusician()));
	}
	
	
	/*
		Collector<T, ?, Map<K, D>> groupingBy(Function<? super T, ? extends K> classifier, Collector<? super T, A, D> downstream)
		Returns a Collector implementing a cascaded "group by" operation on input elements of type T, 
		grouping elements according to a classification function, and then performing 
		a reduction operation on the values associated with a given key using the specified downstream Collector. 
	 */
	public static Map<Artist, Long> numberOfAlbums(Stream<Album> albums) {
		return albums.collect(groupingBy(album -> album.getMainMusician(), counting()));
	}
	
	public static Map<Artist, List<String>> nameOfAlbums(Stream<Album> albums) {
		return albums.collect(groupingBy(Album::getMainMusician, mapping(Album::getName, toList())));
	}
}
