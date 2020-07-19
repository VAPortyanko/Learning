package bitSet;
import java.util.BitSet;

// Bitset is an array of bits, which is to preserve each element uses 1 bit.
// boolean values have size of int(?). One element take 32 bit for store a value.
public class BitSet_2 {
	public static void main(String[] args) {
		BitSet bs = new BitSet(), bs2 = new BitSet();
		bs.set(0);
		bs.set(1);
		bs.set(3);
		bs.set(5);
		
		bs2.set(2);
		bs2.set(3);
		bs2.set(4);
		bs2.set(5);
		
		System.out.println(bs);
		System.out.println(bs2);
		
		bs.and(bs2); // Logical AND.
		System.out.println(bs);
	}
}
