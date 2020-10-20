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

		System.out.println(list);
		System.out.println("Add new elements in the collection");
		Collections.addAll(list, 11,12,11);
		System.out.println(list);
	}
}
