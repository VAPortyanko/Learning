package concurentCollections.CopyOnWriteCollections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteList {
	public static void main(String[] args) {
		
		CopyOnWriteArrayList<Integer> numbers = new CopyOnWriteArrayList<>(new Integer[]{1, 3, 5, 8});
		
		Iterator<Integer> iterator = numbers.iterator();
		numbers.add(10);
		
		List<Integer> result = new LinkedList<>();
		iterator.forEachRemaining(result::add);

		System.out.println(result);
		
		Iterator<Integer> iterator2 = numbers.iterator();
		List<Integer> result2 = new LinkedList<>();
		iterator2.forEachRemaining(result2::add);

		System.out.println(result2);
		
		// Deletion during iteration is not allowed.
		whenIterateOverItAndTryToRemoveElement__thenShouldThrowException();
		
	}
	
	public static void whenIterateOverItAndTryToRemoveElement__thenShouldThrowException() {

	    CopyOnWriteArrayList<Integer> numbers = new CopyOnWriteArrayList<>(new Integer[]{1, 3, 5, 8});

	    Iterator<Integer> iterator = numbers.iterator();
	    while (iterator.hasNext()) {
	        iterator.remove();
	    }
	}
}

// https://www.codeflow.site/ru/article/java-copy-on-write-arraylist.
