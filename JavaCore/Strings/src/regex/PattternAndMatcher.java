package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PattternAndMatcher {
	public static void main(String[] args) {
		Pattern pat;
		Matcher mat;
		boolean found;
		
		pat = Pattern.compile("Java");
		mat = pat.matcher("Java");
		found = mat.matches();
		
		System.out.println("Check if the \"Java\" matches \"Java\"");
		if (found)
			System.out.println("Matches!");
		else
			System.out.println("Doesn't match!");
		
		System.out.println();
		
		mat = pat.matcher("Java Se");
		System.out.println("Looking for a \"Java SE\" substring in a \"Java SE\" string");
		if (mat.find())
			System.out.println("Found!");
		else
			System.out.println("Not found!");
		
		
	}
}
