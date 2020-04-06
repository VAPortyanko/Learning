package by.pva.test;

public class Test_0003 {
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Third c = new Third();
	}
}

class First {
	int i = 1;
	static int j = 10;
	public First() {
		int k = 1;
		System.out.println("Constructor A");
	}
}

class Second extends First {
	int i = 2;
	public Second() {
		System.out.println("Constructor B");
		int k = 2;
	}
}

class Third extends Second {
	int i = 3;
	public Third() {
		System.out.println("Constructor C");
		int k = 3;
	}
}


// ToDo ... Superclass constructors always call constructor of parent class -> Documented it (for repetition purposes).