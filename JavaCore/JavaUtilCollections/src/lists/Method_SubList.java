package lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Method_SubList {

	public static void main(String[] args) {
		
		ArrayList<String> list = new ArrayList<String>(); 
		list.addAll(Arrays.asList("1", "2", "3", "4", "5", "6"));
		
		List<String> sublist = list.subList(2, 4);
		System.out.println("List: " + list);
		System.out.println("SubList: " + sublist);
		list.remove("2");
		System.out.println("List: " + list);
		
		// System.out.println(sublist); Concurrent modidficationException.
		
		list.subList(2, 4).clear();
		System.out.println("List: " + list);
	}
}
