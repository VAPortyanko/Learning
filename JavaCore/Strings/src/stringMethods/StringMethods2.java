package stringMethods;

public class StringMethods2 {
	public static void main(String[] args) {
		// Constructors.
		char[] chars = {'a', 'b', 'c', 'd', 65535}; // 16 bit (from 0 to 65535).
		
		String str1 = new String(chars);
		System.out.println(str1);
		
		str1 = new String(chars, 1, 2);
		System.out.println(str1);		
		
		str1 = new String(new byte[]{-122, 66, 67}); // 8 bit (from -128 to 127)
		System.out.println(str1);	
		
		str1 = new String(new StringBuffer("aaa"));
		System.out.println(str1);	
		
		str1 = new String(new StringBuilder("bbb"));
		System.out.println(str1);
		
		int[] ints = {66, 65000};
		str1 = new String(ints, 0, ints.length);
		System.out.println(str1);	
		
		str1 = "String" + ' ' + "!";
		System.out.println(str1);	
		
		// methods
		/* ValueOf */
		str1 = String.valueOf(false);
		System.out.println(str1);	
		str1 = String.valueOf(10.6d);
		System.out.println(str1);
		
		/* charAt */
		char c = "Lock!".charAt(0);
		System.out.println(c);
		
		/* getChars */
		char sChars[] = new char[20];
		"Hello, world!".getChars(0, 12, sChars, 3);
		System.out.println(sChars);
		"Hello, world!".getChars(0, 12, sChars, 0);
		System.out.println(sChars);
		
		/* getBytes */
		
		/* equals, equalsIgnoreCase */
		String str2, str3, str4;
		str2 = "Hello";
		str3 = "Hello";
		str4 = new String("Hello");
		System.out.println(str2 == str3);
		System.out.println(str2 == str4);
		System.out.println(str3 == str4);
		System.out.println(str2.equals(str3));
		System.out.println(str2.equals(str4));
		System.out.println(str3.equals(str4));
		
		/* regionMatches */
		System.out.println("Hello, World!".regionMatches(7, "World!", 0, 6));
		
		/* startWith, endWith */
		/* compareTo */
		/* indexOf */
		/* substring */
		/* concat */
		/* replace */
		/* trim */
		/* toLowerCase, toUpperCase */
		
		/* split */
		String[] strings = "Hello World !".split(" ");
		for(String a:strings){
			System.out.println(a);
		}
		
		/* ... */
		
	}
}

