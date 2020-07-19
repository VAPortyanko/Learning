package regex;

import java.util.regex.Pattern;

public class Quote_method {
	public static void main(String[] args) {
		
		// Quote() method
		// Uses string to match, not regex.
		System.out.println("foo".matches(".*")); // true
		System.out.println("foo".matches(Pattern.quote(".*"))); // false
		System.out.println(".*".matches(Pattern.quote(".*"))); // true
	}
}
