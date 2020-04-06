package localTime;

import java.time.LocalTime;
import java.util.stream.IntStream;

public class Timer {
	public static void main(String[] args) {

		final int TimerValue = 20;

		IntStream.range(0, TimerValue).forEach((e) -> {
			LocalTime thisSec = LocalTime.now();
			display(thisSec.getHour(), thisSec.getMinute(), thisSec.getSecond(), ":");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		});
	}

	private static void display(int Hours, int Minutes, int Seconds, CharSequence delimeter) {
		System.out.println(String.valueOf(Hours)
				           + delimeter
				           + String.format ("%02d", Minutes)
				           + delimeter
				           + String.format ("%02d", Seconds));
	}
}
