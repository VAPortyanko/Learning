package temporalQuery;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;

public class AdjustInto {
	 public static void main(String... args) {
		 
	        Year y = Year.of(2011);
	        System.out.println(y);

	        adjust(y, LocalDate.now());
	        adjust(y, LocalDateTime.now());
	        adjust(y, OffsetDateTime.now());
	        adjust(y, ZonedDateTime.now());
	        adjust(y, YearMonth.now());
	        adjust(y, Year.now());
	    }

	    private static void adjust(Year y, Temporal t) {
	        Temporal t2 = y.adjustInto(t);
	        System.out.printf("%15s > %s > %s%n",
	                t.getClass().getSimpleName(),
	                t, t2);
	    }
}
