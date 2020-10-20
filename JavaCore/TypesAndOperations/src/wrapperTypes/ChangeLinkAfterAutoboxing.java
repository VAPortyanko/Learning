package wrapperTypes;

public class ChangeLinkAfterAutoboxing {
	public static void main(String[] args) {
		Integer a = new Integer(10);
		Integer b = a;

		System.out.println(a==b);
		System.out.println(a.equals(b));
		
		a = a + 1; // variable "a" now has a new reference.
		System.out.println(a==b);
		System.out.println(a.equals(b+1));
		
	}
}
