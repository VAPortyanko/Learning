package d_t_dt;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class D_T_DT_SetAndGet {
	public static void main(String[] args) {
		
		// Get current values.
		LocalDate.now();    // 2020-03-24
		LocalTime.now();    // 10:05:12.801
		LocalDateTime.now();// 2020-03-24T10:05:12.801
		
		// Set new values.
		// Date.
		LocalDate.of(2020, Month.SEPTEMBER, 23); // 2020-09-23
		LocalDate.of(2021, 1, 1);                // 2021-01-01
		LocalDate.ofYearDay(2020, 361);          // 2020-12-26
		
		// Time.
		LocalTime.of(12, 10); // 12:10
		LocalTime.of(18, 15, 10); // 18:15:10
		LocalTime.of(23, 59, 59, 700); // 23:59:59.000000700
		LocalTime.ofSecondOfDay(9_124); // 02:32:04
		LocalTime.ofNanoOfDay(100_000_000_000L); // 00:01:40

		// DateTime.
		LocalDateTime.of(1992, Month.AUGUST, 24, 12, 0); // 1992-08-24T12:00
		LocalDateTime.of(2004, Month.AUGUST, 23, 17, 15, 2); // 2004-08-23T17:15:02
		LocalDateTime.of(2008, Month.JANUARY, 6, 20, 45, 2, 2); // 2008-01-06T20:45:02.000000002
		LocalDateTime.of(2009, 1, 13, 9, 7); // 2009-01-13T09:07
		LocalDateTime.of(2012, 4, 4, 6, 16); // 2012-04-04T06:16
		LocalDateTime.of(2018, 10, 14, 4, 20); // 2018-10-14T04:20
		LocalDateTime.of(LocalDate.now(), LocalTime.now()); // 2018-01-20T09:19:48.603054
	}
}
