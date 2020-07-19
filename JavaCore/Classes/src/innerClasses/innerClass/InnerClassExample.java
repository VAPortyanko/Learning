package innerClasses.innerClass;

public class InnerClassExample {
	
	private int a1 = 3;
	
	public static void main(String[] args){
		InnerClassExample classMain = new InnerClassExample();
		
		InnerClass innerclass = classMain.new InnerClass();
		
		System.out.println(innerclass.b1); // Access to a private variable of a inner non-static class.
		System.out.println(innerclass.getB1());
		innerclass.call();
		
	}
	
	//  Directly create an inner class can only be non-static methods of the enclosing class.
	public void createInnerClass(){
		@SuppressWarnings("unused")
		InnerClass class3 = new InnerClass();
	}
	public static void createInnerClass2(){
		@SuppressWarnings("unused")
		InnerClass class3 = new InnerClassExample().new InnerClass();
	}
	
	public class InnerClass{
		// It's impossible in a nested static class to create static members.
		// static private int b0 =30; // - error.
		// except constants.
		final static int B0 = 80;
		private int b1 = 20;
		
		public int getB1(){
			return b1;
		}
		
		public void call(){
			System.out.println("Call by name to members of the surrounding class: " + a1);
			System.out.println("Call with \"this\" to members of the surrounding class " + InnerClassExample.this.a1);
		}
	}
	
}
