package overloadingAndOverriding;

public class MethodOverLoading {
    public static void main(String args[]) {
    	 
        Overload2 ob = new Overload2();
        int i = 10;
        double d = 10.1;
        byte b = 99;
        short s = 10;
        float f = 11.5F;
 
        ob.f(i); // invoke method ob.f(int)
        ob.f(d); // invoke method ob.f(double)
        ob.f(b); // invoke method ob.f(int) with type cast.
        ob.f(s); // invoke method ob.f(int) with type cast.
        ob.f(f); // invoke method ob.f(double) with type cast.
 
    }
    
    static class Overload2 {
        void f(int x) {
            System.out.println("Inside f(int): " + x);
        }
     
        void f(double x) {
            System.out.println("Inside f(double): " + x);
        }
    }
}
