package by.pva.functionalProgramming.exercises.chapter4;

import java.util.stream.Stream;

import by.pva.functionalProgramming.baseClasses.Artist;
import static java.util.stream.Stream.concat;

public interface Exercise_4_1 {
    public String getName();

    public Stream<Artist> getMusicians();

    public default Stream<Artist> getAllMusicians() {
        return getMusicians()
              .flatMap(artist -> concat(Stream.of(artist), artist.getMembers()));
    }
}
