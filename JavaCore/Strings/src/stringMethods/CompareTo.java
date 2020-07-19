package stringMethods;

public class CompareTo {
	public static void main(String[] args) {
		System.out.println("a".compareTo("b")); // a<b
		System.out.println("b".compareTo("a")); // b>a
		System.out.println("b".compareTo("b")); // b=b
		System.out.println("bbb".compareTo("b")); //bbb >b
	}
}
