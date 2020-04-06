package zonedDateTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class CurrentTimeByCountry {
	public static void main(String[] args) {
		
		// We are in Minsk.
		LocalDate localDate = LocalDate.now();
		LocalTime localTime = LocalTime.now();
		ZoneId zoneMinsk = ZoneId.of("Europe/Minsk");
		ZoneId zoneKiev  = ZoneId.of("Europe/Kiev");
		ZoneId zoneNY    = ZoneId.of("America/New_York");

		 
		ZonedDateTime minskTime  = ZonedDateTime.of(localDate, localTime, zoneMinsk);
		ZonedDateTime kievTime   = minskTime.withZoneSameInstant(zoneKiev);
		ZonedDateTime nyTime     = minskTime.withZoneSameInstant(zoneNY);              
		ZonedDateTime japanTime  = minskTime.withZoneSameInstant(ZoneOffset.of("-09:00"));
		
		System.out.println("Minsk now: " + minskTime);
		System.out.println("Kiev now:  " + kievTime);
		System.out.println("NY now:    " + nyTime);
		System.out.println("Japan now: " + japanTime);
		
	}
}

