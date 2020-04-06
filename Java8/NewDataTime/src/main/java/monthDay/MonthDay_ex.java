package monthDay;

import java.time.Month;
import java.time.MonthDay;

public class MonthDay_ex {
	public static void main(String[] args) {
		
		MonthDay date = MonthDay.of(Month.FEBRUARY, 29);
		int year = 2010;
		
		System.out.println("Date " + date + " is valid for " + year + " year: " + date.isValidYear(year));
	}
}
