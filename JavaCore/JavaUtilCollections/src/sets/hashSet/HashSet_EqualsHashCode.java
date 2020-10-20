package sets.hashSet;

import java.util.HashSet;

public class HashSet_EqualsHashCode {
	public int hashCode, equalsField;
	public String Name;
	static int i = 0;
	
	public HashSet_EqualsHashCode(int hCode, int eqf, String name) {
		this.Name = name;
		hashCode = hCode;
		equalsField = eqf;
	}
	
	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (((HashSet_EqualsHashCode) obj).equalsField == this.equalsField)
			return true;
		else 
			return false;
	}

	public static void main(String[] args) {
		HashSet<HashSet_EqualsHashCode> hs = new HashSet<HashSet_EqualsHashCode>();
		System.out.println("Element with name 'A' was added: " + hs.add(new HashSet_EqualsHashCode(1, 1, "A")));
		System.out.println("Element with name 'B' was added: " + hs.add(new HashSet_EqualsHashCode(2, 2, "B")));
		System.out.println("Element with name 'C' was added: " + hs.add(new HashSet_EqualsHashCode(1, 2, "C")));
		System.out.println("Element with name 'D' was added: " + hs.add(new HashSet_EqualsHashCode(2, 1, "D")));
		System.out.println("Element with name 'E' was added: " + hs.add(new HashSet_EqualsHashCode(2, 2, "E")));
		System.out.print("Result set contains: ");
		for (HashSet_EqualsHashCode element: hs){
			System.out.print(element.Name);
		}
	}
}

