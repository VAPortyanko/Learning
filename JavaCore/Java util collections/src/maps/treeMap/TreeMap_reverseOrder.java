package maps.treeMap;

import java.util.*;
import java.util.Map.Entry;

public class TreeMap_reverseOrder {
	public static void main(String args[]) {

		Map<String, String> treemap = new TreeMap<String, String>(Collections.reverseOrder());

		// Put elements to the map
		treemap.put("Key1", "Jack");
		treemap.put("Key2", "Rick");
		treemap.put("Key3", "Kate");
		treemap.put("Key4", "Tom");
		treemap.put("Key5", "Steve");

		Set<Entry<String, String>> set = treemap.entrySet();
		Iterator<Entry<String, String>> i = set.iterator();
		// Display elements
		while (i.hasNext()) {
			Map.Entry<String, String> me = i.next();
			System.out.print(me.getKey() + ": ");
			System.out.println(me.getValue());
		}
	}
}
