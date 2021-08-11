package overloadingAndOverriding;

public class MethodVisabilityAndInheritance {
	
	public static void main(String[] args) {
		System.out.println(new SubClass().a());
	}
}

class SuperClass {

    public String a() {
        return b();
    }

    public String b() {
        return c();
    }

    public String c() {         // If both methods (SuperClass.c() and SubClass.c()) are visible in subclass, then SubClass.c() is overridden. 
        return "superclass";    // If SuperClass.c() is private then SubClass.c() is a new method (try change visibility).
    }
}

class SubClass extends SuperClass {

    public String a() {
        return b() + b();
    }
    
    @Override
    public String c() {
        return "subclass";
    }
}

// https://dzone.com/articles/java-method-overriding-and-visibility (See comments !!!).