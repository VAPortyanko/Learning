package modifier_access.package1;

public class Child1 extends Parent{
	
	public static void main(String[] args) {
	
		Child1 child1 = new Child1();
		// System.out.println(child1.priv); // Unavailable.
		System.out.println(child1.prot);
		System.out.println(child1.def);
		System.out.println(child1.pub);
		
	}
	
}
