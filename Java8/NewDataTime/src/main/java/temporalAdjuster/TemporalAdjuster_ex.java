package temporalAdjuster;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class TemporalAdjuster_ex {
	public static void main(String[] args) {
		
		LocalDate date = LocalDate.now();
		
		TemporalAdjuster adj = TemporalAdjusters.next(DayOfWeek.WEDNESDAY); // TemporalAdjuster is a functional Interface.
		LocalDate nextWed = date.with(adj);
		
		System.out.printf("For the date of %s, the next Wednesday is %s.%n", date, nextWed);
	}
}
