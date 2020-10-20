package regExpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherGroup {

	public static void main(String[] args) {
		
		   String text = "John writes about this, and John Doe writes about that, and John Wayne writes about everything. (John Gard)";

	        String patternString1 = "(John) (.+?) "; // Whitespases after groups.

	        Pattern pattern = Pattern.compile(patternString1);
	        Matcher matcher = pattern.matcher(text);

	        while(matcher.find()) {
	        	System.out.println("found: " + matcher.group(1) + " " + matcher.group(2));
	        }
	}
}
//http://tutorials.jenkov.com/java-regex/matcher.html
//http://proglang.su/java/regular-expressions?category=java&alias=147