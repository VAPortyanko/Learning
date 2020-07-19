package maps.hashMap;

import java.util.HashMap;

public class HashMapOverView {
	public static void main(String[] args) {
		HashMap<Test, String> hm = new HashMap<Test, String>();
		
		HashMapOverView hmclass = new HashMapOverView();
		Test a = hmclass.new Test();
		Test b = hmclass.new Test();
		a.a = 10;
		a.b = 20;
		
		b.a = 20;
		b.b = 10;
		
		// Collision (a.hashcode = b.hashcode and a.equals(b) = false) 
		hm.put(a, "The first value");
		hm.put(b, "The second value");
		
		System.out.println(hm.get(a));
		System.out.println(hm.get(b));
		
		System.out.println(hm);
	
	}
	
	private class Test{
		int a;
		int b;
		@Override
		public int hashCode() {
			return a+b;
		}
		@Override
		public boolean equals(Object obj) {
			return (this.a == ((Test) obj).a) && (this.b == ((Test) obj).b);
		}
		
		@Override
		public String toString(){
			return this.getClass().getSimpleName() + "[" + a + "," + b + "]";
		}
	}	
}