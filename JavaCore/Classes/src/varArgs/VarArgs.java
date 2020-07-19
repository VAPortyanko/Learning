package varArgs;

public class VarArgs {

	public static void main(String[] args) {
		VarArgs classA = new VarArgs();
		// classA.vargs(); !error
		classA.printVargs(10, 45, 34);
		classA.printVargs(true, false);
		classA.printVargs("Hello");
	}

	public VarArgs(int... mmm) {
	}

	void printVargs(int... nnn) {
		for (int i : nnn) {
			System.out.println(i);
		}
	}

	void printVargs(boolean... nnn) {
		for (boolean i : nnn) {
			System.out.println(i);
		}
	}

	void printVargs(String a, int... nnn) {
		System.out.println(a);
		for (int i : nnn) {
			System.out.println(i);
		}
	}

}