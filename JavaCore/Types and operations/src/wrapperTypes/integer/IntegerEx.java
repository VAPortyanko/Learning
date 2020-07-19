package wrapperTypes.integer;

public class IntegerEx {
	public static void main(String[] args) {
		System.out.println("Decimal value of binary number 101 - " + Integer.parseInt("101", 2));  // The second parameter is a base of system.
		System.out.println("Decimal value of octal number 77 - " + Integer.parseInt("77", 8));
		
		System.out.println("---------------------------------------------------------");
		
		Integer i = new Integer(100);
		System.out.println("100 to binary system - " + Integer.toBinaryString(i));
	}
}
