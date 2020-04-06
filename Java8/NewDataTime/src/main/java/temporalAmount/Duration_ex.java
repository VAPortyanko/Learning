package temporalAmount;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Duration_ex {
	public static void main(String[] args) {

		Instant t1 = Instant.now();
		Instant t2 = t1.plus(4, ChronoUnit.HOURS);

		System.out.println("Instant t1: " + t1);
		System.out.println("Instant t2: " + t2);
		
		long ns = Duration.between(t1, t2).toHours();
		System.out.println("t2-t1=" + ns + " hours.");
		
		Duration gap = Duration.ofSeconds(10);
		t2 = t2.plus(gap);
		System.out.println("t2 + " + gap.getSeconds() + " seconds = " + t2);
		
	}
}
