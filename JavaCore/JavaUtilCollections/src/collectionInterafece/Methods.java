package collectionInterafece;

import java.util.ArrayList;
import java.util.Collection;

public class Methods {
	public static void main(String[] args) {
		ArrayList<String> collect = new ArrayList<String>();
		
		// add(T t)
		collect.add("1");
		collect.add("2");
		collect.add("3");
		collect.add("4");
		collect.add("5");
		
		Collection<String> collect3 = new ArrayList<String>();
		
		// add(T t)
		collect3.add("1");
		collect3.add("2");
		collect3.add("6");

		// contains
		boolean isContain = collect.contains("2");
		System.out.println(isContain);
		
		// forEach
		collect.forEach(t -> System.out.println(t));
		
		// Comparison by elements.
		System.out.println(collect.equals(collect3));
		
		// ContainsAll
		System.out.println(collect.containsAll(collect3));
				
		// Deletion by condition
		collect.removeIf(t -> t.equals("2"));
		System.out.println(collect);

	}
}
