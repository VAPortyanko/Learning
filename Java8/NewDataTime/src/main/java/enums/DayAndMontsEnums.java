package enums;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class DayAndMontsEnums {
    
	public static void main(String[] args) {
		DayOfWeek dow = DayOfWeek.MONDAY;
		Locale locale = new Locale("be", "BY");
		System.out.println(dow.getDisplayName(TextStyle.FULL, locale));
		System.out.println(dow.getDisplayName(TextStyle.NARROW, locale));
		System.out.println(dow.getDisplayName(TextStyle.SHORT, locale));
		
		Month monthAugust = Month.AUGUST;
		System.out.println(monthAugust.getDisplayName(TextStyle.FULL, locale));
		System.out.println(monthAugust.getDisplayName(TextStyle.NARROW, locale));
		System.out.println(monthAugust.getDisplayName(TextStyle.SHORT, locale));
		
		System.out.println("There are " + monthAugust.length(true) + " days in " + monthAugust.name());
		
	}
}
