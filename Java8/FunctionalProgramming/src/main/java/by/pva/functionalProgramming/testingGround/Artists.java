package by.pva.functionalProgramming.testingGround;

import java.util.List;
import java.util.Optional;

import by.pva.functionalProgramming.baseClasses.Artist;

public class Artists {
	
	private List<Artist> artists;

	public Artists(List<Artist> artists) {
		this.artists = artists;
	}

	public Optional<Artist> getArtist(int index) {
		if (index < 0 || index >= artists.size()) {
			return Optional.empty();
		}
		
		return Optional.of(artists.get(index));
	}
	
	public String getArtistName(int index) {
		return getArtist(index).map(artist -> artist.getName()).orElse("unknown");
	}
}
