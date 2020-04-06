package exersices;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MondaysOfYearMonth {

	public static void main(String[] args) {
		Month month = Month.APRIL;
		Year year = Year.now();
		DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
		
		List<LocalDate> dates = getMondaysOfYearMonth(year, month, dayOfWeek);

		dates.forEach(System.out::println);
	}
	
	public static List<LocalDate> getMondaysOfYearMonth(Year year, Month month, DayOfWeek dayOfWeek){
		
		Objects.requireNonNull(year,      "Year must not be null!");
		Objects.requireNonNull(month,     "Month must not be null!");
		Objects.requireNonNull(dayOfWeek, "DayOfWeek must not be null!");
		
		LocalDate startDate = LocalDate.of(year.getValue(), month.getValue(), 1);
		ArrayList<LocalDate> list = new ArrayList<>(5);
		
		LocalDate nextDate = startDate.with(TemporalAdjusters.nextOrSame(dayOfWeek));
		while (nextDate.getMonth() == month) {
			list.add(nextDate);
			nextDate = nextDate.with(TemporalAdjusters.next(dayOfWeek));
		}
		return list;
	}
}

// https://docs.oracle.com/javase/tutorial/datetime/iso/QandE/questions.html   - Exercise.
// https://docs.oracle.com/javase/tutorial/datetime/iso/QandE/ListMondays.java - Answer.

//import java.time.Month;
//import java.time.Year;
//import java.time.DayOfWeek;
//import java.time.LocalDate;
//import java.time.DateTimeException;
//
//import java.time.temporal.TemporalAdjuster;
//import java.time.temporal.TemporalAdjusters;
//
//import java.io.PrintStream;
//import java.lang.NumberFormatException;
//
//public class ListMondays {
//    public static void main(String[] args) {
//        Month month = null;
//
//        if (args.length < 1) {
//            System.out.printf("Usage: ListMondays <month>%n");
//            throw new IllegalArgumentException();
//        }
//
//        try {
//            month = Month.valueOf(args[0].toUpperCase());
//        } catch (IllegalArgumentException exc) {
//            System.out.printf("%s is not a valid month.%n", args[0]);
//            throw exc;      // Rethrow the exception.
//        }
//
//        System.out.printf("For the month of %s:%n", month);
//        LocalDate date = Year.now().atMonth(month).atDay(1).
//              with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
//        Month mi = date.getMonth();
//        while (mi == month) {
//            System.out.printf("%s%n", date);
//            date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
//            mi = date.getMonth();
//        }
//    }
//}