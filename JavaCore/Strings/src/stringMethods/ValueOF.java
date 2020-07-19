package stringMethods;

public class ValueOF {
	public static void main(String[] args) {
		
		boolean l = true;
		String str1 = String.valueOf(l);
		
		double d = 2.15; // + int, float, long
		String str2 = String.valueOf(d);
		
		char c = 't';
		String str3 = String.valueOf(c);
		
		char[] charArray = {'a', 'b', 'c', 'd'};
		String str4 = String.valueOf(charArray);
		
		Object o = new Object(){
			public String toString(){
				return "Overrided method toString";
			} 
		};
		
		String str5 = String.valueOf(o);
		
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);
		System.out.println(str4);
		System.out.println(str5);
	}
}
