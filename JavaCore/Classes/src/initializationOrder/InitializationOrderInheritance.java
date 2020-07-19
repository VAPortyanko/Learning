package initializationOrder;

public class InitializationOrderInheritance {
	public static void main(String[] args) {
		System.out.println("The first invocation of the C class constructor:\n");
		new C();
		System.out.println("\nThe second invocation of the C class constructor:\n");
		new C();
	}
}

class A {
	static {System.out.println("Static content of A class");}
	
	{System.out.println("Non static content of A class");}
	
	A(){
		System.out.println("A class constructor"); 
	}
}

class B extends A {
	static {System.out.println("Static content of B class");}
	
	{System.out.println("Non static content of B class");}
	
	B(){
		System.out.println("B class constructor"); 
	}
}

class C extends B {
	static {System.out.println("Static content of C class");}
	
	{System.out.println("Non static content of C class");}
	
	C(){
		System.out.println("C class constructor"); 
	}
}