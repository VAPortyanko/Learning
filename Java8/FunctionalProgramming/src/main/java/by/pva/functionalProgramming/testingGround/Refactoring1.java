// Find the names of all the tracks in the given albums that sound longer than one minute.

package by.pva.functionalProgramming.testingGround;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import by.pva.functionalProgramming.baseClasses.Album;
import by.pva.functionalProgramming.baseClasses.Artist;
import by.pva.functionalProgramming.baseClasses.Track;

import static java.util.Arrays.asList;

public class Refactoring1 {
	
	public static void main(String[] args) {
		Track track01 = new Track("Track01", 68),
			  track02 = new Track("Track02", 34),
			  track03 = new Track("Track03", 78),
			  track04 = new Track("Track04", 58),
			  track05 = new Track("Track05", 61);
		
		List<Artist> musicans = asList(new Artist("Liapis", "belarus"));
		
		Album album01 = new Album("Album1", asList(track01, track02, track03), musicans);
		Album album02 = new Album("Album2", asList(track04, track05), musicans);
		
		List<Album> albums = asList(album01, album02);
		
		System.out.println(getLongTracks1(albums));
		System.out.println(getLongTracks2(albums));
	}
    
	// Legacy code.
	public static Set<String> getLongTracks1(List<Album> albums) {
		Set<String> trackNames = new HashSet<>();
		for (Album album : albums) {
			for (Track track : album.getTrackList()) {
				if (track.getLength() > 60) {
					String name = track.getName();
					trackNames.add(name);
				}
			}
		}
		return trackNames;
	}
	
	// Modern style.	
	public static Set<String> getLongTracks2(List<Album> albums) {
		return albums.stream()
		      .flatMap(album->album.getTracks())
		      .filter(track->track.getLength() > 60)
		      .map(track->track.getName())
		      .collect(Collectors.toSet());
	}
}
