package innerClasses.localClass;

public class LocalClassExample {
	
	private int a=200;
	public int b;
	protected int c;
	int d = 1000;
	static int e;
	
	public static void main(String[] args) {
		LocalClassExample a1 = new LocalClassExample();
		a1.methodD();
	}
	
	private int methodA(){
		return a;
	}
	
	public int methodB(){
		return b;
	}
	
	protected int methodC(){
		return c;
	}
	
	private Object methodD(){
		
		class LocalClass {
			
			public LocalClass(){}
			
			private int la;
			public int lb;
			protected int lc;
			int ld;
			//static int le; - compilation error.
			static final int lf = 90; 
			 
			int methodLA(){
				System.out.println(la+lb+lc+ld+lf);
				System.out.println(LocalClassExample.this.methodA());
				return d;
			}
		}
		
		LocalClass local1 = new LocalClass();
		System.out.println(local1.methodLA());
		
		return local1; // Returns a r to the local class in the main method.
	}
}