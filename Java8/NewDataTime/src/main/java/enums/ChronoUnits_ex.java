package enums;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class ChronoUnits_ex {
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		// Consts.
		ChronoUnit u1 = ChronoUnit.YEARS;
		ChronoUnit u2 = ChronoUnit.CENTURIES;
		ChronoUnit u3 = ChronoUnit.FOREVER;
		ChronoUnit u4 = ChronoUnit.ERAS;
		ChronoUnit u5 = ChronoUnit.NANOS;
		ChronoUnit u6 = ChronoUnit.MONTHS;
		
		// Between method.
        System.out.println("--- Duration.between --- ");
        LocalDateTime oldDate = LocalDateTime.of(2016, Month.AUGUST, 31, 10, 20, 55);
        LocalDateTime newDate = LocalDateTime.of(2016, Month.NOVEMBER, 9, 10, 21, 56);
        
        System.out.println("Duration is " + u1.between(oldDate, newDate) + " years.");
        System.out.println("Duration is " + u6.between(oldDate, newDate) + " month.");
        
        // Birthday.
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(1985, Month.MARCH, 26);
         
        Period p = Period.between(birthday, today);
        long p2 = ChronoUnit.DAYS.between(birthday, today);
        System.out.println("You are " + p.getYears() + " years, " + p.getMonths() +
                           " months, and " + p.getDays() +
                           " days old. (" + p2 + " days total)");
        
        System.out.println("Period to String: " + p);
        
	}
}
