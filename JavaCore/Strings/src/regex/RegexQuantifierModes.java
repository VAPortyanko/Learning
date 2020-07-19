package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexQuantifierModes {
	public static void main(String[] args) {
	    String text = "Egor Alla Alexandr";
	    
	    System.out.println("Serch string: " + text);
	    
	    Pattern pattern1 = Pattern.compile("A.+a");  // Greedy mode.
	    Pattern pattern2 = Pattern.compile("A.++a"); // Super greedy mode.
	    Pattern pattern3 = Pattern.compile("A.+?a"); // lazy mode.
	    
	    Matcher matcher1 = pattern1.matcher(text);
	    Matcher matcher2 = pattern2.matcher(text);
	    Matcher matcher3 = pattern3.matcher(text);
	    
	    System.out.println("\nGreedy mode:");
	    while (matcher1.find()) {
	        System.out.println("   " + text.substring(matcher1.start(), matcher1.end()));
	    }
	    System.out.println("\nSuper greedy mode:");	    
	    while (matcher2.find()) {
	        System.out.println("   " + text.substring(matcher2.start(), matcher2.end()));
	    }
	    System.out.println("\nlazy mode:");
	    while (matcher3.find()) {
	        System.out.println("   " + text.substring(matcher3.start(), matcher3.end()));
	    }
	}
}

// https://javarush.ru/groups/posts/regulyarnye-vyrazheniya-v-java.
// +, *, ? and {n} are quantifiers.