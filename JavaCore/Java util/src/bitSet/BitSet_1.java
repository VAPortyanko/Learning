// http://src-code.net/klass-bitset/
package bitSet;

import java.util.BitSet;

public class BitSet_1 {

	private BitSet used = new BitSet();

	public BitSet_1(String str) {

		for (int i = 0; i < str.length(); i++)
			used.set(str.charAt(i)); // Set bit to 1 in position charAt(i). 
	}
	

	public String toString() {
		String desc = "[";

		int size = used.size();

		for (int i = 0; i < size; i++) {
			if (used.get(i))
				desc += (char) i;
		}

		return desc + "]";
	}
	
	public static void main(String[] args) {
		BitSet_1 ec = new BitSet_1("zxcaaaaaaavbnmsadfjkl");
		System.out.println(ec);
	}
}