package examples;

public class DigitsFromZeroToNine {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		Digits e; // Not initialize here.
		e = Digits.FIVE; // Initialization of all elements of the enum.

		System.out.println("\nThe next number after " + Digits.ONE + " is " + Digits.ONE.next());
		System.out.println("The next number after " + Digits.TWO + " is " + Digits.TWO.next());
		System.out.println("The next number after " + Digits.THREE + " is " + Digits.THREE.next());
		System.out.println("The next number after " + Digits.FOUR + " is " + Digits.FOUR.next());
		System.out.println("The next number after " + Digits.FIVE + " is " + Digits.FIVE.next());
		System.out.println("The next number after " + Digits.SIX + " is " + Digits.SIX.next());
		System.out.println("The next number after " + Digits.SEVEN + " is " + Digits.SEVEN.next());
		System.out.println("The next number after " + Digits.EIGHT + " is " + Digits.EIGHT.next());
		System.out.println("The next number after " + Digits.NINE + " is " + Digits.NINE.next());
		System.out.println("The next number after " + Digits.ZERO + " is " + Digits.ZERO.next());
		
	}

	enum Digits {
		
		ONE,
		TWO,
		THREE,
		FOUR,
		FIVE,
		SIX,
		SEVEN,
		EIGHT,
		NINE,
		ZERO;

		private static final Digits[] DIGITS = Digits.values(); // https://stackoverflow.com/questions/609860/convert-from-enum-ordinal-to-enum-type (CASHING)

		Digits() {
			System.out.println("Init enum instance - " + this);
		}

		public Digits next() {
			return DIGITS[(this.ordinal() + 1) % 10];
		}
	}
}
