package by.pva.functionalProgramming.testingGround;

import java.util.stream.Stream;

import by.pva.functionalProgramming.baseClasses.Artist;

import static by.pva.functionalProgramming.baseClasses.SampleData.theBeatles;

public class Tetetst {
	public static void main(String[] args) {
		class My implements Performance {

			public String getName() {
				return null;
			}

			@Override
			public Stream<Artist> getMusicians() {
				return Stream.of(theBeatles);
			}

		}

		My classs = new My();
		classs.getAllMusicians().forEach(System.out::println);

		System.out.println();

		String[] list = { "Red", "Green", "Blue" };
		Stream<String> s = Stream.of(list);
		s.forEach(System.out::println);

	}
}


interface Performance {
	public String getName();

	public Stream<Artist> getMusicians();

	public default Stream<Artist> getAllMusicians() {
		return getMusicians().flatMap(artist -> {
			return artist.isSolo() ? Stream.of(artist) : Stream.of(artist.getMembers().toArray(Artist[]::new));
		});
	}
}