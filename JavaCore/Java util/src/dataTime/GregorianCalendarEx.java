package dataTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class GregorianCalendarEx {
	public static void main(String[] args) {
		GregorianCalendar cln = new GregorianCalendar(2015, 10, 10);
		System.out.println("Is the year '" + cln.get(1) + "' a leap year ? -  " +  cln.isLeapYear(cln.get(Calendar.YEAR)));
	}
}
