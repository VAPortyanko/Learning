package maps.treeMap;

import java.util.Map;
import java.util.TreeMap;

public abstract class TreeMap_Ex {
	public static void main(String[] args) {
		
		// create an object of type TreeMap.
		TreeMap<String, String> myTreeMap = new TreeMap<String, String>();
		
		// put four pair in the TreeMap object.
		myTreeMap.put("2", "Vasia");
		myTreeMap.put("1", "Petia");
		myTreeMap.put("4", "Grisha");
		myTreeMap.put("3", "Kolia");
		//myTreeMap.put("w", null); it is ok!
		//myTreeMap.put(null, "a"); exeption!
		
		// keys are outputs in order view.
		for(Map.Entry<String, String> e: myTreeMap.entrySet()){
			System.out.println("Key: " + e.getKey() + " value: " + e.getValue());
		}

		System.out.println(myTreeMap.tailMap("2"));
		System.out.println(myTreeMap.headMap("3"));
	}
}