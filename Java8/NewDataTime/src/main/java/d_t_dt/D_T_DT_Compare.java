package d_t_dt;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class D_T_DT_Compare {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		LocalDate now   = LocalDate.now();
		LocalDate _2017 = LocalDate.of(2017, 9, 23);
		
		boolean after  = now.isAfter(_2017); // true
		boolean before = now.isBefore(_2017);// false
		
		LocalTime now2         = LocalTime.now();
		LocalTime _2HoursAfter = now2.plusHours(2);
		
		boolean after2  = now2.isAfter(_2HoursAfter);  // false
		boolean before2 = now2.isBefore(_2HoursAfter); // true

		LocalDateTime now3     = LocalDateTime.now();
		LocalDateTime monthAgo = now3.minusMonths(1);
		
		boolean after3  = now3.isAfter(monthAgo);  // true
		boolean before3 = now3.isBefore(monthAgo); // false
		
		LocalDate localDate = LocalDate.now();
		LocalDate tomorrow  = LocalDate.now().plusDays(1);
		boolean isDateAfter = localDate.compareTo(tomorrow) > 0; // false
		LocalTime localTime  = LocalTime.now();
		LocalTime hourLater  = localTime.plusHours(1);
		boolean isTimeBefore = localTime.compareTo(hourLater) < 0; // true
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDateTime lastYear      = localDateTime.minusYears(1);
		boolean isDateTimeAfter     = localDateTime.compareTo(lastYear) > 0; // true
	}
}
