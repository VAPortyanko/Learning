package exersices;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IsFriday13 {

	public static void main(String[] args) {
		LocalDate date1 = LocalDate.now();            // Now;
		LocalDate date2 = LocalDate.of(2020, 03, 13); // Friday 13;
		LocalDate date3 = LocalDate.of(2020, 03, 20); // Friday 20;
		LocalDate date4 = LocalDate.of(2020, 04, 13); // Monday 13;
		
		Stream.of(date1, date2, date3, date4)
		      .collect(Collectors.groupingBy(IsFriday13::isFriday13))
		      .forEach((key, value)->System.out.println("Is " + (key ? "": "not ") + "Friday 13th: " + value));
	}
	
	public static boolean isFriday13(LocalDate date) {
		
		Objects.requireNonNull(date, "Date must not be null!");
		
		if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY) && date.getDayOfMonth() == 13)
			return true;
		else
			return false;
	} 
}

// https://docs.oracle.com/javase/tutorial/datetime/iso/QandE/questions.html   - Exercise.
// https://docs.oracle.com/javase/tutorial/datetime/iso/QandE/Superstitious.java       - Answer (Part 1).
// https://docs.oracle.com/javase/tutorial/datetime/iso/QandE/FridayThirteenQuery.java - Answer (Part 2).

//import java.time.Month;
//import java.time.Year;
//import java.time.LocalDate;
//import java.time.DayOfWeek;
//import java.time.DateTimeException;
//
//import java.time.temporal.TemporalQuery;
//import java.time.temporal.TemporalAccessor;
//
//import java.io.PrintStream;
//
//public class Superstitious {
//    
//    public static void main(String[] args) {
//        Month month = null;
//        LocalDate date = null;
//
//        if (args.length < 2) {
//            System.out.printf("Usage: Superstitious <month> <day>%n");
//            throw new IllegalArgumentException();
//        }
//
//        try {
//            month = Month.valueOf(args[0].toUpperCase());
//        } catch (IllegalArgumentException exc) {
//            System.out.printf("%s is not a valid month.%n", args[0]);
//            throw exc;     // Rethrow the exception.
//        }
//
//        int day = Integer.parseInt(args[1]);
//
//        try {
//            date = Year.now().atMonth(month).atDay(day);
//        } catch (DateTimeException exc) {
//            System.out.printf("%s %s is not a valid date.%n", month, day);
//            throw exc;     // Rethrow the exception.
//        }
//
//        System.out.println(date.query(new FridayThirteenQuery()));
//    } 
//}

//public class FridayThirteenQuery implements TemporalQuery<Boolean> {
//    
//    // Returns TRUE if the date occurs on Friday the 13th.
//    public Boolean queryFrom(TemporalAccessor date) {
//        
//        return ((date.get(ChronoField.DAY_OF_MONTH) == 13) &&
//                (date.get(ChronoField.DAY_OF_WEEK) == 5));
//    }
//}