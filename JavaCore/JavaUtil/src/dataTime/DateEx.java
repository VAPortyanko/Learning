package dataTime;
import java.util.Date;

public class DateEx {
	public static void main(String[] args) {
		Date dt = new Date(); // Current date
		
		Date dt2 = new Date();
		dt2.setTime(1000000000);
		
		System.out.println("Date1 = " + dt);
		System.out.println("Date2 = " + dt2);	
		
		System.out.println("Is data1 after data2? - " + dt.after(dt2));
		System.out.println("Is data1 before data2? - " + dt.before(dt2));
		
		System.out.println("The number of milliseconds after midnight on the first of January 1970  - " + dt.getTime());
		
	}
}
