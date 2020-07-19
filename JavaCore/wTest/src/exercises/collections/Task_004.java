package exercises.collections;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Convert the following code into a new implementation that uses lambda expressions and aggregate operations instead of nested for loops.
// Hint: Make a pipeline that invokes the filter, sorted, and collect operations, in that order. 
//
// https://docs.oracle.com/javase/tutorial/collections/streams/QandE/answers.html
//
//List<Album> favs = new ArrayList<>();
//for (Album a : albums) {
//  boolean hasFavorite = false;
//  for (Track t : a.tracks) {
//      if (t.rating >= 4) {
//          hasFavorite = true;
//          break;
//      }
//  }
//  if (hasFavorite)
//      favs.add(a);
//}
//Collections.sort(favs, new Comparator<Album>() {
//                         public int compare(Album a1, Album a2) {
//                             return a1.name.compareTo(a2.name);
//                         }});

public class Task_004 {

	public static void main(String[] args) {
		
		Track t1 = new Track(3);
		Track t2 = new Track(5);
		Track t3 = new Track(4);
		Track t4 = new Track(5);
		Track t5 = new Track(3);
		Track t6 = new Track(2);
		Track t7 = new Track(1);
		
		Album alb2 = new Album("Kasta", Arrays.asList(t3, t4));
		Album alb1 = new Album("Kirkorov", Arrays.asList(t5, t6, t7));
		Album alb3 = new Album("Basta", Arrays.asList(t1, t2));
		
		List<Album> albums = Arrays.asList(alb1, alb2, alb3);
		
		List<Album> favs = albums.stream()
		                         .filter(album -> album.getTraks().stream()
		    		                                              .filter(track->track.getRating() >= 4)
		    		                                              .count() > 0)
		                         .sorted((e1, e2) -> e1.getname().compareToIgnoreCase(e2.getname()))
		                         .collect(Collectors.toList());
		      
		favs.forEach(e -> System.out.println(e.getname()));
	}
}

class Album{
	String name;
	List<Track> traks;
	
	public Album(String name, List<Track> traks) {
		this.name = name;
		this.traks = traks;
	}

	public List<Track> getTraks() {
		return traks;
	}
	
	public String getname() {
		return name;
	}
}

class Track{
	private int rating;
	
	public Track(int raiting){
		this.rating = raiting;
	}

	public int getRating() {
		return rating;
	}
}

