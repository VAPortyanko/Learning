package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class SplitStringToSimilarParts {

	public static void main(String[] args) {
		final String regex = "(?<=\\G.{5})";
		final String string = "Ineverdreamedaboutsuccess,Iworkedforit";

		final Pattern pattern = Pattern.compile(regex);
		final Matcher matcher = pattern.matcher(string);

		while (matcher.find()) {
		    System.out.println("Full match: start with " + matcher.start(0) + " length: " + matcher.group(0).length());
		    for (int i = 1; i <= matcher.groupCount(); i++) {
		        System.out.println("Group " + i + ": " + matcher.group(i));
		    }
		}
		
		Stream.of(string.split(regex))
		      .forEach(element -> System.out.println("{" + element + "}"));
	}
}

