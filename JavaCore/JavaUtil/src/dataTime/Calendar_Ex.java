package dataTime;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Calendar_Ex {
	public static void main(String... args) {

		// getting available Ids for offset
		String[] availId = TimeZone.getAvailableIDs(3*60*60*1000);    // offset = 3 hours (in milliseconds).  

		// checking available Ids for offset
		System.out.println("Available Ids for offset are: \n");
		for (int i=0; i<availId.length; i++){
			System.out.println(availId[i]);
		} 
		
		TimeZone tz = TimeZone.getTimeZone("Europe/Minsk");
		Calendar cldr = Calendar.getInstance(tz);
		
		System.out.println("\nCurrent date --> " + cldr.getTime());  // Ignores TimeZone (?)
	    DateFormat df = DateFormat.getDateTimeInstance();
	    df.setTimeZone(tz);
	    System.out.println("Current time in Minsk --> " + df.format(cldr.getTime()) + "\n");
		
		System.out.println("Year = " + cldr.get(Calendar.YEAR));
		System.out.println("Month number = " + (cldr.get(Calendar.MONTH)+1)); // Juniary = 0.
		System.out.println("Day number = " + cldr.get(Calendar.DATE));
		System.out.println("Hour = " + cldr.get(Calendar.HOUR));              // Ignores TimeZone
		System.out.println("Minute = " + cldr.get(Calendar.MINUTE));
		System.out.println("Second = " + cldr.get(Calendar.SECOND));
		System.out.println("MilliSecond = " + cldr.get(Calendar.MILLISECOND));
	}
}
