package initializationOrder;

public class StaticMemberAndInheriance {
	public static void main(String[] args) {
		System.out.println(D.my_var);
		System.out.println(E.my_var);
	}
}

class D{
	static public int my_var = 0;
}

class E extends D{
	static{
		my_var = 10;
		System.out.println("This static block doesn't execute because the \"my_var\" variable belong to the parent class!"
				+ "There are no calls to class D.");
	}
}

// https://stackoverflow.com/questions/10698516/behavior-of-static-blocks-with-inheritance