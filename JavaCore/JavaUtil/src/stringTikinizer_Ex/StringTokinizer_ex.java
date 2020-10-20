package stringTikinizer_Ex;

import java.util.StringTokenizer;

public class StringTokinizer_ex {

	public static void main(String[] args) {
		
		String str = "Skazika, Diadia, ved' ne darom, Moskva, spalennaia pozgarom";
		String delim=",";
		
		System.out.println("String for parsing: \"" + str + "\"");
		System.out.println("Delimeters: \"" + delim + "\"");
		
		StringTokenizer st = new StringTokenizer(str, delim, true);
		
		System.out.println("\nStringTokinizer with param [returnDelim = true].\n");
		System.out.println("\nToken counts: " + st.countTokens());
		
		int i = 1;
		while (st.hasMoreTokens()){
			System.out.println(i++ + ": [" + st.nextToken() + "]");
		}
		
		
		// StringTokinizer with param [returnDelim = false].
		System.out.println("\nStringTokinizer with param [returnDelim = false].\n");
		StringTokenizer st2 = new StringTokenizer(str, delim, false);
		
		System.out.println("Token counts: " + st2.countTokens());
		
		i = 1;
		while (st2.hasMoreTokens()){
			System.out.println(i++ + ": [" + st2.nextToken() + "]");
		}
	}

}
