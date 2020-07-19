package exercises.collections;

import java.util.SortedSet;
import java.util.TreeSet;

// Take the FindDupsexample and modify it to use a SortedSet instead of a Set. 
// Specify a Comparator so that case is ignored when sorting and identifying set elements.
//
//public class FindDups {
//    public static void main(String[] args) {
//        Set<String> s = new HashSet<String>();
//        for (String a : args)
//               s.add(a);
//               System.out.println(s.size() + " distinct words: " + s);
//    }
//}


public class Task_002 {
	public static void main(String[] args) {
		SortedSet<String> s = new TreeSet<String>((e1, e2)-> e1.compareToIgnoreCase(e2)) ;
		for (String a : args)
			s.add(a);
		System.out.println(s.size() + " distinct words: " + s);
	}
}