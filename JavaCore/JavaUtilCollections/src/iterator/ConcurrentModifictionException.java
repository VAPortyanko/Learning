package iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ConcurrentModifictionException {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>(); 
		list.addAll(Arrays.asList("1", "2", "3", "4"));
		
		Iterator<String> it = list.iterator();

		it.next();
		
		list.add("5"); // Change the list after getting the iterator.
		
		it.next(); // ConcurrentModificationException.
	}
}
