package test;

public class CallMethodFromConstructor {

	public CallMethodFromConstructor() {
		say();
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		CallMethodFromConstructor a = new CallMethodFromConstructor(); 
	}

	void say(){
		System.out.println("Called from constructor");
	}
}

