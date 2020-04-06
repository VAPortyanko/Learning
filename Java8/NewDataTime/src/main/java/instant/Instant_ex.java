package instant;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Instant_ex {
	public static void main(String[] args) {
		
		System.out.println(Instant.EPOCH); // 1 January 1970 00:00:00Z.
		
		Instant timestamp = Instant.now();
		Instant oneDayLater = timestamp.plus(1, ChronoUnit.DAYS);
		
		System.out.println(timestamp);
		System.out.println(oneDayLater);
		
		long daysFromEpoch = Instant.ofEpochSecond(0L).until(Instant.now(), ChronoUnit.DAYS);
		System.out.println("Days from the begin of the EPOCH: " + daysFromEpoch + " days");
		
	}
}
