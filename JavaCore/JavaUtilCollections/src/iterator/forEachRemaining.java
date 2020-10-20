package iterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class forEachRemaining {
	public static void main(String[] args) {
		
		Collection<String> collect = new ArrayList<String>();
		collect.add("1");
		collect.add("2");
		collect.add("3");
		collect.add("4");
		collect.add("5");

		Iterator<String> it = collect.iterator();
		
		it.next();
		it.next();
		
		it.forEachRemaining(t -> System.out.println(t));

	}
}
