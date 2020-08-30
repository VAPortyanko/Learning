package codePoint;

public class StringFromCodePoint {
	public static void main(String[] args) {
		
		// Russian letters.
		System.out.print("Russian letters: ");
		for (int i = 1040; i<=1103; i++)
			System.out.print(newString(i));
		
		System.out.println();
		
		// Chinese symbol.
		System.out.println("Chinese symbol: " + newString(37328));
		
		char c = 37329;
		System.out.println(c);
		
		// char c2 = 65536; Compile error: char type is limited by 65535. 
		System.out.println("symbol: " + newString(65536));
		System.out.println();
	}
	
	static String newString(int codePoint) {
	    if (Character.charCount(codePoint) == 1) {
	        return String.valueOf((char) codePoint);
	    } else {
	        return new String(Character.toChars(codePoint));
	    }
	}
	
}
// http://www.oracle.com/us/technologies/java/supplementary-142654.html