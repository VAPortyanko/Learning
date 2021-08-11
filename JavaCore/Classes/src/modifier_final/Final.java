package modifier_final;

final public class Final{
	
	public final static double PI = 3.14;
	
	final void printPI() {  // The method can't be overridden.
		// PI = PI++; // The value of the variable can't be changed.
		System.out.println(PI);
	}
}

// class Child extends Final{ // A child class can't extends a final class.
// }