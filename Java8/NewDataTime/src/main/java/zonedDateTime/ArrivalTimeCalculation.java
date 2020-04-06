package zonedDateTime;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

public class ArrivalTimeCalculation {

	public static void main(String[] args) {
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy  hh:mm a");
		 
		// ����������� �� ���-��������� 20 ���� 2013 � 19:30.
		LocalDateTime leaving = LocalDateTime.of(2013, Month.JULY, 20, 19, 30);
		ZoneId leavingZone = ZoneId.of("America/Los_Angeles");

		ZonedDateTime departure = ZonedDateTime.of(leaving, leavingZone);
		 
		try {
		    String out1 = departure.format(format);
		    System.out.printf("LEAVING:  %s (%s)%n", out1, leavingZone);
		} catch (DateTimeException exc) {
		    System.out.printf("%s can't be formatted!%n", departure);
		    throw exc;
		}
		 
		// ���� ������ 10 ����� � 50 ����� (650 �����)
		ZoneId arrivingZone = ZoneId.of("Asia/Tokyo"); 
		ZonedDateTime arrival = departure.withZoneSameInstant(arrivingZone)
		                                 .plusMinutes(650);
		 
		try {
		    String out2 = arrival.format(format);
		    System.out.printf("ARRIVING: %s (%s)%n", out2, arrivingZone);
		} catch (DateTimeException exc) {
		    System.out.printf("%s can't be formatted!%n", arrival);
		    throw exc;
		}
		 
		if (arrivingZone.getRules().isDaylightSavings(arrival.toInstant())) 
		    System.out.printf("  (%s daylight saving time will be in effect.)%n",
		                      arrivingZone);
		else
		    System.out.printf("  (%s standard time will be in effect.)%n",
		                      arrivingZone);

		String tokyoOffset      = arrival.getOffset().getId();
		String LosAngelesOffset = departure.getOffset().getId();
		long lTokyoOffset      = arrival.getOffset().getLong(ChronoField.OFFSET_SECONDS);
		long lLosAngelesOffset = departure.getOffset().getLong(ChronoField.OFFSET_SECONDS);
		
		System.out.println("\nLos Angeles offset: " + LosAngelesOffset + " (" + lLosAngelesOffset + " seconds).");
		System.out.println("Tokyo offset: " + tokyoOffset + " (" + lTokyoOffset + " seconds).");
		
		System.out.println("Arrival time = Departure time + (Dest.Offset - Arriv.Offset) + flight length + daylight saving (DST) time length:");
		System.out.println("7.30pm + ((+9h)-(-7h)) + (10.50h) + 0h = 7.30pm + 16h + 10.50h = 7.30pm + 26.50h = 7.30pm(nextDay) + 2.50h = 10.20pm(nextDay)");
		
	}
}
