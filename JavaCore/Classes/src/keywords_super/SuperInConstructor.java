package keywords_super;

public class SuperInConstructor {
	
	public static void main(String[] args){
		
		SuperInConstructor classMain = new SuperInConstructor();
		A a0 = classMain.new A();
		A a3 = classMain.new A(2, true, "Hello!");
		B b0 = classMain.new B();
		B b4 = classMain.new B(2, true, "Hello!", 56.9);
		
		System.out.println("A().a = "+ a0.a);
		System.out.println("A().b = "+ a0.b);
		System.out.println("A().c = "+ a0.c);
		
		System.out.println("A(2, true, 'Hello!').a = "+ a3.a);
		System.out.println("A(2, true, 'Hello!').b = "+ a3.b);
		System.out.println("A(2, true, 'Hello!').c = "+ a3.c);
		
		System.out.println();
		
		System.out.println("B().a = "+ b0.a);
		System.out.println("B().b = "+ b0.b);
		System.out.println("B().c = "+ b0.c);
		System.out.println("B().d = "+ b0.d);
		
		System.out.println("B(2, true, 'Hello!', 56.9).a = "+ b4.a);
		System.out.println("B(2, true, 'Hello!', 56.9).b = "+ b4.b);
		System.out.println("B(2, true, 'Hello!', 56.9).c = "+ b4.c);
		System.out.println("B(2, true, 'Hello!', 56.9).d = "+ b4.d);
	}
	
	class A{

		int a;
		boolean b;
		String c;
		
		A(){
			a = -1;
			b = false;
			c = "Empty";
		}
		
		A(int a, boolean b, String c){
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}
	
	class B extends A{

		double d;
		
		B(){
			super();
			d = -1; 
		}
		
		B(int a, boolean b, String c, double d){
			// this.d = d; Constructor call must be the first statement in a constructor
			super(a, b, c); // If we ommit this statement then default constructor will be invoked.
			this.d = d;
		}
	}
	

}

