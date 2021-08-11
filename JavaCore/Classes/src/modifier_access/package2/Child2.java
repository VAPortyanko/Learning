package modifier_access.package2;

import modifier_access.package1.Parent;

public class Child2 extends Parent{
	
	public static void main(String[] args) {
		
		Child2 child2 = new Child2();
		// System.out.println(child2.priv); // Unavailable.
		System.out.println(child2.prot);
		// System.out.println(child2.def); // Unavailable (not the same package).
		System.out.println(child2.pub);
		
	}
}
