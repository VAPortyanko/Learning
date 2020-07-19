package collectionsClass;

import java.util.ArrayList;
import java.util.Collections;

public class Frequency_method {

	public static void main(String[] args) {

		ArrayList<Integer> list = new ArrayList<Integer>();
		Collections.addAll(list, 11, 10, 40, 11, 50, 11, 5, 20);
		
		System.out.println("list: " + list);
		
		int listElem = 11;

		System.out.println("Element " + listElem + " contains in the collection \"list\" "
				+ Collections.frequency(list, listElem) + " times");
	}
}
