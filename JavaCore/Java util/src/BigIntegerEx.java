import java.math.BigInteger;

public class BigIntegerEx {
	public static void main(String[] args) {
		BigInteger bi1 = new BigInteger("12837491273908471293749127349712937491723947192734917239479");
		BigInteger bi2 = new BigInteger("12837491273908471293749127349712937491723947192734917239479");
		bi1 = bi1.multiply(bi2);
		System.out.println(bi1);
	}
}

