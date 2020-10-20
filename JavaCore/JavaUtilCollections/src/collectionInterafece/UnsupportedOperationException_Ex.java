package collectionInterafece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class UnsupportedOperationException_Ex {
	public static void main(String[] args) {

// Collections.unmodifiableXXX		
		
		ArrayList<A> list = new ArrayList<A>();
		
		list.add(new A(1));
		list.add(new A(2));
		list.add(new A(3));
		list.add(new A(4));
		list.add(new A(5));
		list.add(new A(6));
		list.add(new A(7));
		list.add(new A(8));
		list.add(new A(9));
		
		List<A> c = Collections.unmodifiableList(list);
		
		try {
			c.add(new A(10));	
		}catch (Exception e){
			System.out.println("We try to add a new object to the unmodifiable collection: " + e + "\n");
		}
				
		A element = c.get(0);
		System.out.println("Take the first element of collection with inner state: " + element);
		element.set(-1);
		System.out.println("Change this state from 1 to -1 and show both colletions:");
		
		System.out.println("Unmodifiable collection:" + c);
		System.out.println("Underlying collection:" + list);
		
System.out.println("\n***************************************************************************************\n");
// Map views (entrySet, keySet, values).
		HashMap<A, String> map = new HashMap<A, String>();
		map.put(new A(1), "1");
		map.put(new A(2), "2");
		map.put(new A(3), "3");
		
		Set<A> set = map.keySet();
		try {
			set.add(new A(4));	
		}catch (Exception e){
			System.out.println("Add element to keySet: " + e + "\n");
		}
		
		System.out.println("Removing element from keySet:");
		System.out.println(set);
		set.remove(new A(1)); // If hashCode and equals method is defined, element will be deleted(New object but the same inner state).
		System.out.println(set);
		System.out.println("Map after deletion from keySet: " + map);
		
System.out.println("\n***************************************************************************************\n");
// Arrays.asList
		List<A> arList = Arrays.asList(new A(1), new A(2), new A(3));
		try {
			arList.add(new A(4));	
		}catch (Exception e){
			System.out.println("Add element to Arrays.asList: " + e + "\n");
		}
		
		try {
			arList.remove(0);	
		}catch (Exception e){
			System.out.println("Remove from Arrays.asList: " + e + "\n");
		}
		
System.out.println("\n***************************************************************************************\n");
// Collections.emptyXXX, Collectioms.singeltonXXX - are also marked as "immutable.
// ...

	}
}

class A {
	A(int i){
		this.i = i;
	}
	
	int i;
	
	@Override
	public String toString(){
		return "" + i;
	}
	
	void set(int value){
		this.i = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + i;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		A other = (A) obj;
		if (i != other.i)
			return false;
		return true;
	}
}