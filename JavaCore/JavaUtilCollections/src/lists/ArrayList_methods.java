package lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class ArrayList_methods {
	public static void main(String[] args) {	
		
		List<String> al1, al2, al3;
		
	    Set<String> hs1 = new HashSet<>();
	    hs1.add("a");
	    hs1.add("b");
	    hs1.add("c");
	    hs1.add("d");
	    
		// Constructors
		al1 = new ArrayList<>();      // Default constructor.
		al2 = new ArrayList<>(hs1);   // Constructor based on other collection implementation(ArrayList, LinkedList, HashSet, ...).
		al3 = new ArrayList<>(30);    // Constructor with initial capacity.
		
		System.out.println("Collection al2 based on HashMap - " + al2);      // Showing content of ArrayList named "al2" that was created from HashSet.
		
		// Metods
			//add-methods
			al1.add("y");                // Add element into the end of the ArrayList "al1".
			al3.add("a");
			al3.add("z");
			al1.add(0, "u");             // Add element into 0 position(it will be first element in ArrayList).
			al1.addAll(hs1);             // Add elements from other collection in the end.
			al1.addAll(0, al2);          // Add elements from other collection in the 0 position.
			System.out.println("Collection al1 after addition - " + al1);
			System.out.println("Collection al3 after addition - " + al3);
			
			// Checking-methods
				// contains
				System.out.println("Do contain collection al1 element 'i'? - " + al1.contains("i"));
				System.out.println("Do contain collection al1 element 'a'? - " + al1.contains("a"));
				System.out.println("Do contain collection al1 all elements of the collection al2 ? - " + al1.containsAll(al2));
				System.out.println("Do contain collection al1 all elements of the collection al3 ? - " + al1.containsAll(al3));
				// equals
				System.out.println("al1 is equals to al2? - " + al1.equals(al2));
				//System.out.println("al2 is equals to hs1? - " + al2.equals(hs1));      // Both collections have the same order and elements, but return false because this is different collections (See the ArrayList.equals method implementation).
				List<String> al4 = new ArrayList<>();
				al4.add("a");
				al4.add("z");
				System.out.println("al4 - " + al4);
				System.out.println("al3 is equals to al4? - " + al3.equals(al4));      // But if change order of the elments return "false".
			    // isEmpty
				System.out.println("Is collection al1 is empty ? - " + al1.isEmpty());
			
			// remove-metods
			al1.remove(0);
			System.out.println("al1 after delete first element - " + al1);
			
			al1.remove("d");  // Only removes the first matching element.
			System.out.println("al1 after delete element 'd'- " + al1);
			
			al1.removeAll(al2); //removes all elements contained in another collection
			System.out.println("al1 after delete all elements contained in al2 collection - " + al1);

			al1.addAll(2, al2);  // Add al2 colection in al1 collection with start position 2.
			System.out.println("New al1 - " + al1);
			
			al1.removeIf(new Predicate<String>() {   // remove by filter
				@Override
				public boolean test(String t) {
					if (t.compareTo("b")>0)
						return false;
					else return true;
				}
			});
			System.out.println("al1 after deleted by filter (deleted all =< 'b')- " + al1);
			
			al4.retainAll(al2);
			System.out.println("al4 after retain al2 - " + al4);
			
			// replace-metod
			al1.replaceAll(new UnaryOperator<String>() { 
				@Override
				public String apply(String s) {
					if (s.equals("c") || s.equals("d")) 
						return "replaced";
					else return s;
				}
			});
			System.out.println("al1 after replace - " + al1);
			al1.set(3, "new");
			System.out.println("al1 after set value 'new' for 4 element - " + al1);
			
			Object[] a = null;
			String[] b = new String[al1.size()];
			a = al1.toArray();
			b = al1.toArray(b);
			System.out.println(Arrays.toString(a));
			System.out.println(Arrays.toString(b));
			
			System.out.println("Sublist from al1 - " + al1.subList(0, 2));
			
			// Iterators.
			//Iterator<String> it = al1.iterator();
			//ListIterator<String> lit = al1.listIterator();
			
			
			//al1.clear();
			//al1.get(0);
			//int firstPosition = al1.indexOf("b");   //Find only first coinsedence.
			//int lastPosition = al1.LastIndexOf("b");   //Find only last coinsedence.			
	}
}
