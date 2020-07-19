package initializationOrder;

public class InitializationOrder {

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		TestClass class1 = new TestClass();
	}

}

class TestClass{
	
	static{System.out.println("Statick block");}
	
	{System.out.println("Non static block");}
	
	TestClass(){
		System.out.println("Constructor");
	};
}

// link - http://www.quizful.net/post/java-fields-initialization
// Usage:
//    1) Field initialization of a anonymous class.
//    2) When need handle exception during variable initialization.
//    3) Complex initialization.
