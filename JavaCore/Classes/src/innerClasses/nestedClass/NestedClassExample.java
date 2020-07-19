package innerClasses.nestedClass;

public class NestedClassExample {
	static private final int CONSTANT1 = 10;
	final int CONSTANT3 = 33;
	
	public static void main(String[] args) {
		// Nested static class has access to all the static members of the enclosing class.
		System.out.println(CONSTANT1*Nested.CONSTANT2);
		
		Nested b = new Nested();
		System.out.println(b.c); // access to non-static members of a nested static class. (Even private).
	}

	void foo(){
		Nested a = new Nested();
		a.metod2();

		// surrounding the class has access to all the static members of a nested static class (even private).
		System.out.println(Nested.CONSTANT2 * 3);
	}
	
	public static class Nested{
		static private final int CONSTANT2 = 10;
		private int c = 10;
		int d = 40;
		void metod2(){
			System.out.println(CONSTANT1*CONSTANT2);
		}
		
		void bar(){
			// You can't directly access non-static members of the enclosing class, but you can create an instance of it and get them.
			// System.out.println(CONSTANT3); - Error!!!
			NestedClassExample class1 = new NestedClassExample();
			System.out.println(class1.CONSTANT3);
		}
	}
}
