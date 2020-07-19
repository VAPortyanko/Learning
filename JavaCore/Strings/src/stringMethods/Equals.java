package stringMethods;

public class Equals {

	public static void main(String[] args) {
		String s1 = "hello";
		String s2 = "hello";
		String s3 = s1;
		String s4 = "h" + "e" + "l" + "l" + "o";
		String s5 = new String("hello");
		String s6 = new String(new char[]{'h', 'e', 'l', 'l', 'o'});

		System.out.println("s1 = \"hello\"");
		System.out.println("s2 = \"hello\"");
		System.out.println("s3 = s1");
		System.out.println("s4 = \"h\" + \"e\" + \"l\" + \"l\" + \"o\"");
		System.out.println("s5 = new String(\"hello\")");
		System.out.println("s6 = new String(new char[]{'h', 'e', 'l', 'l', 'o'})");
		
		System.out.println();
		
		System.out.println(("s1 == s2: " + (s1 == s2)));
		System.out.println("s1 equals s2: " + (s1.equals(s2)));
		System.out.println("s1 == s3: " + (s1 == s3));
		System.out.println("s1 equals s3: " + (s1.equals(s3)));
		System.out.println("s1 == s4: " + (s1 == s4));
		System.out.println("s1 equals s4: " + (s1.equals(s4)));
		System.out.println("s1 == s5: " + (s1 == s5));
		System.out.println("s1 equals s5: " + (s1.equals(s5)));
		System.out.println("s1 == s6: " + (s1 == s6));
		System.out.println("s1 equals s6: " + (s1.equals(s6)));
	}

}
