package sets.hashSet;

import java.util.HashSet;

public class HashSet_sortOrder {
	public static void main(String[] args) {
		HashSet<Object> set = new HashSet<Object>();
		
		set.add(new someClass("First  added element", 2));
		set.add(new someClass("Second added element", 1));
		set.add(new someClass("Third  added element", 3));
		set.add(new someClass("Fourth added element", 3));
		set.add(new someClass("Fifth  added element", 4));
		
		for (Object o : set)
			System.out.println(o);
	}
}


class someClass extends Object{
	
	private final int hashCode;
	private final String name; 
	
	public someClass(String name, int hashCode) {
		this.hashCode = hashCode;
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		return hashCode;
	}
	
	@Override
	public String toString() {
		return name + " - hashCode(" + hashCode+")";
	}
}