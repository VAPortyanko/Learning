package overloadingAndOverriding;

public class VisibilityExtension {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		A a = new A();
		B b = new B();
		C c = new C();
		D d = new D();
		E e = new E();
		
		b.say();
		c.say();
		d.say();
		e.say();
	}
}

class A{
	@SuppressWarnings("unused")
	private void say() {System.out.println("A");}
}

class B extends A {
	void say() {System.out.println("B");}
}

class C extends A {
	protected void say() {System.out.println("C");}
}

class D extends A {
	public void say() {System.out.println("D");}
}

class E extends C {
	public void say() {System.out.println("E");}
}
