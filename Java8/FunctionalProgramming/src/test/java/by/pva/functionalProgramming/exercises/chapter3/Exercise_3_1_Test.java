package by.pva.functionalProgramming.exercises.chapter3;

import static by.pva.functionalProgramming.baseClasses.SampleData.*;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import by.pva.functionalProgramming.baseClasses.Album;
import by.pva.functionalProgramming.baseClasses.SampleData;

public class Exercise_3_1_Test {
    @Test
    public void addsEmptyList() {
        int result = Exercise_3_1.addUp(Stream.empty());
        assertEquals(0, result);
    }

    @Test
    public void addsListWithValues() {
        int result = Exercise_3_1.addUp(Stream.of(1, 3, -2));
        assertEquals(2, result);
    }
    
	@Test
	public void extractsNamesAndOriginsOfArtists() {
		List<String> namesAndOrigins = Exercise_3_1.getNamesAndOrigins(SampleData.getThreeArtists());
		assertEquals(asList("John Coltrane", "US", "John Lennon", "UK", "The Beatles", "UK"), namesAndOrigins);
	}
	
	@Test
	public void findsShortAlbums() {
		List<Album> input = asList(manyTrackAlbum, sampleShortAlbum, aLoveSupreme);
		List<Album> result = Exercise_3_1.getAlbumsWithAtMostThreeTracks(input);
		assertEquals(asList(sampleShortAlbum, aLoveSupreme), result);
	}
}
