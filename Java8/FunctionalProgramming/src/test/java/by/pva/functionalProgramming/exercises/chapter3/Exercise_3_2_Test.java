package by.pva.functionalProgramming.exercises.chapter3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import by.pva.functionalProgramming.baseClasses.SampleData;

public class Exercise_3_2_Test {
    @Test
    public void internal() {
        assertEquals(4, Exercise_3_2.countBandMembersInternal(Arrays.asList(SampleData.johnColtrane, SampleData.theBeatles)));
    }
    
    @Test
    public void internal2() {
        assertEquals(4, Exercise_3_2.countBandMembersInternal2(Arrays.asList(SampleData.johnColtrane, SampleData.theBeatles)));
    }
    
    @Test
    public void internal3() {
        assertEquals(4, Exercise_3_2.countBandMembersInternal3(Arrays.asList(SampleData.johnColtrane, SampleData.theBeatles)));
    }
}
