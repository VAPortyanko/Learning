package localDate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class LocalDateEssential {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		// Creating.
		LocalDate date = LocalDate.of(2000, Month.NOVEMBER, 20);
		LocalDate dateNow = LocalDate.now();
		
		
		// Receiving the information.
		DayOfWeek dotw = dateNow.getDayOfWeek();
		
		System.out.println("Date: " + dateNow);
		System.out.println(dotw);
		

	}
}
