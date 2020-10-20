package collectionsClass;

import java.util.ArrayList;
import java.util.Collections;

public class Swap_method {

	public static void main(String[] args) {
		
		ArrayList<Integer> list = new ArrayList<>();
        
		list.add(1);
		list.add(3);
		list.add(2);
		list.add(4);

		System.out.println(list);
		
		Collections.swap(list, 1, 2);
		
		System.out.println(list);
	}
}
