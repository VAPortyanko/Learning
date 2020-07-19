import java.util.Calendar;
import java.util.Formatter;

public class FormatterEx {
	public static void main(String[] args) {
		Formatter frmt = new Formatter(System.out);
		
		// Simple format.
		frmt.format("Formatting string - %s, integer - %d, and float number %f.",  "string", 10, 3.14);
		frmt.format("%nNumber 1 000 000 (scientific form of number notation) - %e",  1000000d);
		frmt.format("%nIf we use %%g it is automaticaly selected %%f or %%e, for example %g and % g", 10000d, 1000000000000d);
		frmt.format("%nHexadecimal representation of a number 20 - %x and its octal form - %o", 20, 20);
		
		// Date format
		Calendar cln = Calendar.getInstance();
		frmt.format("%nCurrent date - " + cln.getTime());
		frmt.format("%nformatted date - %tc", cln);
		frmt.format("%nTime zone - %tz", cln);
		frmt.format("%nTime after and before noon - %tp", cln);
		frmt.format("%nFormated date - %tD", cln);
		frmt.format("%nFull month name - %tB", cln);
		// ...
		
		// Min width.
		frmt.format("%nNumber 2014 (width 30) - %30d", 2014);// spaces by default.
		frmt.format("%nNumber 2014 (width 30) - %030d", 2014); // zero by default.
		frmt.format("%n3-column table max width 4 symbol");
		frmt.format("%n%4d %4d %4d", 123, 1, 90);
		frmt.format("%n%4d %4d %4d", 1213, 12, 9000);
		frmt.format("%n%4d %4d %4d", 13, 1222, 9);
		
		// replacements
		frmt.format("%nThe second argument - %2$d, the first - %1$d", 10, 20);
		// "<" - repeats last element.
		frmt.format("%n first argument - %d, repeat it two times - %<d %<d, and then print the last two - %d %d", 10, 20, 30);
		
		// flags
		frmt.format("%n |%5d| - right alignment", 6);  
		frmt.format("%n |%-5d| - left alignment", 6);
		frmt.format("%n |% d| - positive numbers preceded by space", 60);
		frmt.format("%n |%+d| - positive numbers preceded by +", 60);
		frmt.format("%n |%(d| - negative numbers are enclosed in brackets", -60);
		
		frmt.format("%n %,d - number with delimeter", 1000123456); 
		
		frmt.close();
	}
}
