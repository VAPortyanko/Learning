package by.pva.test;

public class Test_0002 { 
    public static void main(String args[]) { 
        Derived1 d1 = new Derived1(); 
        Derived2 d2 = new Derived2();
        Derived3 d3 = new Derived3();
        d1.foo(); 
        d2.foo();
        d3.foo();
    } 
}

class Base { 
    protected void foo() {} 
}

class Derived1 extends Base { 
    // Compile error - [Cannot reduce the visibility of the inherited method from Base]
	// void foo() {} 
}

class Derived2 extends Base {
	// Compile error - [Cannot reduce the visibility of the inherited method from Base]
    // void foo() {}  
}

class Derived3 extends Base { 
    public void foo() {} 
}

// Private -> Default -> Protected -> Public.