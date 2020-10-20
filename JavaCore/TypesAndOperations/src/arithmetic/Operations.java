package arithmetic;

public class Operations {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
/* Division. */
		System.out.println("10/3 = " + 10/3);
		System.out.println("10.0/3 = " + 10.0/3);
		System.out.println("10/3.0 = " + 10/3.0);
		
		double d2 = 10/3;
		double d3 = 10;
		double d4 = 3;
		System.out.println("10/3 = " + d2);
		System.out.println("d3/d4 = " + d3/d4);
		System.out.println("d3/3 = " + d3/3);
		
/* Arithmetic operation with "byte" type. */
		byte bs1 = 1, bs2 = 2;
		byte sum = (byte) (bs1 + bs2);
		// or 
		int sum2 = (bs1 + bs2);
		byte sum3 = (byte) (bs1-bs2);
		
/* Overflow */
		int a = 2147483646, b = 2, c;  // 2147483647 - right board of int type range.
		c = a + b;
		System.out.println("int = int(2147483646) + int(2) = " + c);
		long l9 = a + b;
		System.out.println(l9);
		long l10 = (long)a + b;
		System.out.println(l10);
		
		int i10 = 130;
		byte b10 = (byte) i10;
		System.out.println(b10);

/* Rounding errors when using fractional */		
		double dtest = 2.0-1.1;     // = 0.8999999999999999
		System.out.println("2.0-1.1 = " + dtest); // If you need to eliminate rounding errors, use the BigDecimal class.
		

/* Shorthand */
		int var1 = 10;
		int var2 = 10;
		int i = 100;
		
		var1 += 1;      // var1 = var1 + 1.
		var1 -= 1;      // var1 = var1 - 1.
		var1 *= 10;     // var1 = var1 * 10.
		var1 /= 10;     // var1 = var1 / 10.
		var1 %= 3;      // var1 = var1 % 10.
		
		var2 = i ++;    // var2 = i; i = i + 1;
		var2 = ++ i;    // i = i + 1; var2 = i;
		
/* Modulo */
		System.out.println(var1 % 3);
		
	}

}
