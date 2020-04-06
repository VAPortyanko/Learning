package d_t_dt;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class D_T_DT_PlusAndMinus {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		LocalDate now           = LocalDate.now();                   // 2018-01-21
		LocalDate plus2Days     = now.plusDays(2);                   // 2018-01-23
		LocalDate plusWeek      = now.plusWeeks(1);                  // 2018-01-28
		LocalDate plus3Months   = now.plusMonths(3);                 // 2018-04-21
		LocalDate plusPeriod    = now.plus(Period.ofDays(3));        // 2018-01-24
		LocalDate plusMillennia = now.plus(1, ChronoUnit.MILLENNIA); // 3018-01-21
		LocalDate plusNegative  = now.plusDays(-1);                  // 2018-01-20
		
		LocalDate minusDays     = now.minusDays(3);                   // 2018-01-18
		LocalDate minusWeeks    = now.minusWeeks(2);                  // 2018-01-07
		LocalDate minusMonths   = now.minusMonths(4);                 // 2017-09-21
		LocalDate minusPeriod   = now.minus(Period.ofDays(1));        // 2018-01-20
		LocalDate minusEras     = now.minus(1, ChronoUnit.CENTURIES); // 1918-01-21
		LocalDate minusNegative = now.minusDays(-1);                  // 2018-01-22
		
		LocalTime now2 = LocalTime.now();                           // 08:49:39.678703
		LocalTime plusNanos   = now2.plusNanos(100_000);            // 08:49:39.678803
		LocalTime plusSeconds = now2.plusSeconds(20);               // 08:49:59.678703
		LocalTime plusMinutes = now2.plusMinutes(20);               // 09:09:39.678703
		LocalTime plusHours   = now2.plusHours(6);                  // 14:49:39.678703
		LocalTime plusMillis  = now2.plus(Duration.ofMillis(100));  // 08:49:39.778703
		LocalTime plusHalfDay = now2.plus(1, ChronoUnit.HALF_DAYS); // 20:49:39.678703

		// Minus methods of localTime ...
		// ...

		LocalDateTime now3 = LocalDateTime.now();                       // 2018-01-21T09:11:48.486298
		LocalDateTime minusNanos3   = now3.plusNanos(780_000_000);      // 2018-01-21T09:11:49.266298
		LocalDateTime minusSeconds3 = now3.plusSeconds(59);             // 2018-01-21T09:12:47.486298
		LocalDateTime minusMinutes3 = now3.plusMinutes(5);              // 2018-01-21T09:16:48.486298
		LocalDateTime minusHours3   = now3.plusHours(3);                // 2018-01-21T12:11:48.486298
		LocalDateTime minusDays3    = now3.plusDays(7);                 // 2018-01-28T09:11:48.486298
		LocalDateTime minusWeeks3   = now3.plusWeeks(3);                // 2018-02-11T09:11:48.486298
		LocalDateTime minusMonths3  = now3.plusMonths(5);               // 2018-06-21T09:11:48.486298
		LocalDateTime minusYears3   = now3.plusYears(2);                // 2020-01-21T09:11:48.486298
		LocalDateTime minusPeriod3  = now3.plus(Period.ofWeeks(2));     // 2018-02-04T09:11:48.486298
		LocalDateTime minusDecades3 = now3.plus(1, ChronoUnit.DECADES); // 2028-01-21T09:11:48.486298
		
		// Minus methods of localDateTime ...
		// ...
		
	}
}
