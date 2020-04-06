package by.pva.functionalProgramming.streams.operations;

import java.util.List;
import by.pva.functionalProgramming.baseClasses.Track;
import java.util.Comparator;

import static java.util.Arrays.asList;

public class Operations_Max_Min {
	public static void main(String[] args) {
		List<Track> tracks = asList(new Track("Bakai", 524),
				                    new Track("Violets For Your Furs", 378),
								    new Track("Time was", 451));
		
		Track shortestTrack = tracks.stream()
				                    .min(Comparator.comparing(track->track.getLength()))
				                    .get();
		
		System.out.println(shortestTrack.getName());
		
	}
}
