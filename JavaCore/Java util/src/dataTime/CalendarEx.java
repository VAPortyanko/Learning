package dataTime;
import java.util.Calendar;
import java.util.Locale;

public class CalendarEx {
	public static void main(String[] args) {
		Calendar cln = Calendar.getInstance(); 
		// int year, int month, int date, int hourOfDay, int minute, int second
		cln.set(2014, 12, 15, 12, 00, 00);
		
		// Available locales:
		System.out.println("Available locales:");
		for (Locale a: Calendar.getAvailableLocales())
			System.out.println("locale: " + a + "; ");
		System.out.println();
		
		System.out.println("Time before an adding 30 minutes- " + cln.getTime());
		// add 30 minutes ...
		cln.add(Calendar.MINUTE, 30);
		System.out.println("Time after an adding 30 minutes - " + cln.getTime());
		
		// Clear date and time.
		cln.clear();
		System.out.println("Time after clear - " + cln.getTime());
		
		System.out.println("Set new date...");
		cln.set(2014, 12, 15, 12, 00, 00);
		
		cln.clear(Calendar.HOUR_OF_DAY);
		System.out.println("Clear hours - " + cln.getTime());
		System.out.println("Time zone - " + cln.getTimeZone());
		
		
	}
}
