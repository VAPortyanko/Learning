package collectionsClass;

import java.util.ArrayList;
import java.util.Collections;

public class AddAll_method {
	public static void main(String[] args) {
		
		ArrayList<Integer> list = new ArrayList<>();
        
		list.add(10);
		list.add(-5);
		list.add(14);
		list.add(11);

		System.out.println("Collection: " + list);
		Collections.addAll(list, 11, 12, 11);
		System.out.println("Added new elements (11, 12, 11) in the collection via the Collections.addAll() method: \n" + list);
	}
}
