package by.pva.functionalProgramming.exercises.chapter3;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Exercise_6_and_7 {

	public static int countLowercaseLetters(String string) {
		
		return (int) string.chars()
                .filter(Character::isLowerCase)
                .count();

/* Possibly this decision doesn't work correctly (Need to read about encoding) */		
//		return (int) string.chars()
//					 .filter(charCode -> charCode >= (int) "a".charAt(0) && charCode <= (int) "z".charAt(0))
//					 .count();
	}
	
	public static Optional<String> mostLowercaseString(List<String> strings) {
	    return strings.stream()
	                  .max(Comparator.comparing(Exercise_6_and_7::countLowercaseLetters));
	}

}