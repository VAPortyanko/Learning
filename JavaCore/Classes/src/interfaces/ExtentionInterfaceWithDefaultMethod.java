package interfaces;

interface Note{
	default void say(String s) {
		System.out.println("It's a default method!");
	}
	String read();
}

interface Note1 extends Note{
	// We need to implement only one method - read (), the default method is inherited 
}

interface Note2 extends Note{
	void say(String s); // The say() method is now abstract.
}

interface Note3 extends Note{
	default void say(String s) { // The say() method is overridden.
		System.out.println("The say() method is overridden!");
	}
}