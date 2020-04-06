package year;

import java.time.Year;

public class Year_ex {
	public static void main(String[] args) {
		boolean validLeapYear = Year.of(2012).isLeap();
		System.out.println(validLeapYear);
	}
}
