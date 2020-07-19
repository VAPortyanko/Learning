package maps;

import java.util.HashMap;

public class Collisions {
	public static void main(String[] args) {
		A a1 = new A(1);
		A a2 = new A(2);
		A a3 = new A(7);
		A a4 = new A(2);
		
		HashMap<A, String> map = new HashMap<>();
		map.put(a1, "1");
		map.put(a2, "2");
		map.put(a3, "3");
		map.put(a4, "4");

		System.out.println(map);
		
	}
}

class A {
	
	private int id;
	
	public A(int id){
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		return 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.id == ((A) obj).id;
	}
	
	@Override
	public String toString() {
		return "id:" + id + " hash:" + hashCode() + " value";
	}
}