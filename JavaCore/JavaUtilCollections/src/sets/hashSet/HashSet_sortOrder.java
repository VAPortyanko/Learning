package sets.hashSet;

import java.util.HashSet;

public class HashSet_sortOrder {
	public static void main(String[] args) {
		HashSet<Object> set = new HashSet<Object>();
		
		set.add(new B());
		set.add(new A());
		set.add(new D());
		set.add(new E());
		set.add(new C());
		
		for (Object o : set)
			System.out.println(o); //  sorted by hashCode + equals ? or function(hashcode) + equals? Backets ?
	}
}


class A extends Object{
	@Override
	public int hashCode() {
		return 3;
	}
}

class B extends Object{
	@Override
	public int hashCode() {
		return 2;
	}
}

class C extends Object{
	@Override
	public int hashCode() {
		return 4;
	}
}

class D extends Object{
	@Override
	public int hashCode() {
		return 5;
	}
}

class E extends Object{
	@Override
	public int hashCode() {
		return 6;
	}
}