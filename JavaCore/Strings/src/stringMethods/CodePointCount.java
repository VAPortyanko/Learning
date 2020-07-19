package stringMethods;

public class CodePointCount {

	 public static void main(String[] args) {

	      String str = "JAVA programming language";
	      System.out.println("String = " + str);

	      // codepoint from index 1 to index 8
	      int retval = str.codePointCount(1, 8);

	      // prints character from index 1 to index 8
	      System.out.println("Codepoint count = " + retval);
	   }
}
// https://stackoverflow.com/questions/12280801/what-exactly-does-string-codepointat-do.

// Code points support characters above 65535 which is Character.MAX_VALUE.
// If you have text with such high characters you have to work with code points or int instead of chars.
// It doesn't this by support UTF-16 which can use one or two 16-bit char and turn it into an int