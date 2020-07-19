package collectionInterafece;

import java.util.ArrayList;
import java.util.Collection;

public class Methods {
	public static void main(String[] args) {
		
		Collection<String> collection1 = new ArrayList<String>();
		collection1.add("1");
		collection1.add("2");
		collection1.add("3");
		collection1.add("4");
		collection1.add("5");
		
		System.out.println("Colletion1:" + collection1);
		System.out.println("Does the colletion1 contains \"2\" ? " + collection1.contains("2"));
		System.out.print("collection1.forEach(t -> System.out.print(t)); - ");
		collection1.forEach(t -> System.out.print(t));
		
		System.out.print("\ncollection1.removeIf(t -> t.equals(\"2\")); - ");
		collection1.removeIf(t -> t.equals("2"));
		System.out.println(collection1 + "\n");

		Collection<String> collection2 = new ArrayList<String>();
		collection2.add("1");
		collection2.add("2");
		collection2.add("6");
		System.out.println("Colletion1:" + collection1);
		System.out.println("Colletion2:" + collection2);

		// Comparison by elements.
		System.out.println("collection1.equals(collection2) - " + collection1.equals(collection2));
		
		// ContainsAll
		System.out.println("collection1.containsAll(collection2) - " + collection1.containsAll(collection2));
		
	}
}
